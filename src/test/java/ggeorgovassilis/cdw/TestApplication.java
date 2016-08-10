package ggeorgovassilis.cdw;

import static org.junit.Assert.*;

import java.util.Date;

import javax.servlet.RequestDispatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import ggeorgovassilis.cdw.services.erp.ListOfCostCenters;
import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;
import ggeorgovassilis.cdw.services.payroll.ExpenseClaim;
import ggeorgovassilis.cdw.services.payroll.ExpenseReceipt;
import ggeorgovassilis.cdw.signature.VerificationException;
import ggeorgovassilis.cdw.time.Clock;

public class TestApplication {

	DispatcherServlet dispatcherServlet;
	Clock clock;
	ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		MockServletConfig config = new MockServletConfig("spring");
		config.addInitParameter("contextConfigLocation",
				"classpath:cdw/application-context.xml, classpath:cdw/cdw-servlet.xml");
		dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.init(config);

		clock = dispatcherServlet.getWebApplicationContext().getBean(Clock.class);
		mapper = new ObjectMapper();
		clock.setNow(new Date(1234567890));
	}

	@After
	public void tearDown() throws Exception {
		dispatcherServlet.destroy();
	}

	@Test
	public void testWorkflow() throws Exception {

		// 1. log user in at HR service and get personnel file back
		MockHttpServletRequest request = new MockHttpServletRequest(dispatcherServlet.getServletContext(), "POST",
				"/api/hr/login");
		request.setParameter("email", "george@acme.com");
		request.setParameter("password", "password123");
		MockHttpServletResponse response = new MockHttpServletResponse();

		dispatcherServlet.service(request, response);
		PersonnelIdentifier pid = mapper.readValue(response.getContentAsString(), PersonnelIdentifier.class);
		assertEquals("george@acme.com", pid.getEmail());
		assertEquals("employed", pid.getEmploymentStatus());
		assertEquals("FfwVOhoRfSwGOGNLN/tQrg==", pid.getSignature().getHashcode());
		assertTrue(pid.getSignature().getValidUntil().after(clock.getNow()));

		// 2. send personnel file to ERP service and get cost centers back
		request = new MockHttpServletRequest(dispatcherServlet.getServletContext(), "POST", "/api/erp/costcenters");
		request.setContent(mapper.writeValueAsBytes(pid));
		request.setContentType("application/json");
		response = new MockHttpServletResponse();
		dispatcherServlet.service(request, response);
		ListOfCostCenters loc = mapper.readValue(response.getContentAsString(), ListOfCostCenters.class);
		assertEquals(2, loc.getCostCenters().size());
		assertEquals("c1", loc.getCostCenters().get(0).getId());
		assertEquals("Travel expenses", loc.getCostCenters().get(0).getLabel());
		assertEquals("c2", loc.getCostCenters().get(1).getId());
		assertEquals("R&D", loc.getCostCenters().get(1).getLabel());
		assertEquals("PLFSWGBCw8gcEWIuit14+A==", loc.getSignature().getHashcode());
		assertTrue(loc.getSignature().getValidUntil().after(clock.getNow()));

		assertEquals("iteTDcY/qpbIxG8H9c+nsQ==", loc.getCostCenters().get(0).getSignature().getHashcode());
		assertTrue(loc.getCostCenters().get(0).getSignature().getValidUntil().after(clock.getNow()));

		assertEquals("3AVTLh5no/83loIfHcPT7g==", loc.getCostCenters().get(1).getSignature().getHashcode());
		assertTrue(loc.getCostCenters().get(1).getSignature().getValidUntil().after(clock.getNow()));

		// 3. send personnel file and cost center to payroll service
		ExpenseClaim claim = new ExpenseClaim();
		claim.setPid(pid);
		claim.setCostCenter(loc.getCostCenters().get(0));
		claim.setAmount(50l);

		request = new MockHttpServletRequest(dispatcherServlet.getServletContext(), "POST",
				"/api/payroll/expenseclaim");
		request.setContent(mapper.writeValueAsBytes(claim));
		request.setContentType("application/json");
		response = new MockHttpServletResponse();
		dispatcherServlet.service(request, response);
		ExpenseReceipt receipt = mapper.readValue(response.getContentAsString(), ExpenseReceipt.class);
		assertEquals(50l, receipt.getAmount().longValue());
		assertEquals(pid.getEmail(), receipt.getPid().getEmail());
		assertTrue(receipt.getSignature().getValidUntil().after(clock.getNow()));
		assertEquals("oBU8O2taefX2Ac5ebHXfIQ==", receipt.getSignature().getHashcode());
	}

	@Test
	public void test_try_to_forge_request() throws Exception {

		// 1. log user in at HR service and get personnel file back
		MockHttpServletRequest request = new MockHttpServletRequest(dispatcherServlet.getServletContext(), "POST",
				"/api/hr/login");
		request.setParameter("email", "george@acme.com");
		request.setParameter("password", "password123");
		MockHttpServletResponse response = new MockHttpServletResponse();

		dispatcherServlet.service(request, response);
		PersonnelIdentifier pid = mapper.readValue(response.getContentAsString(), PersonnelIdentifier.class);

		// 2. forge user; send personnel file to ERP service and get cost
		// centers back
		pid.setEmail("bob@acme.com");
		request = new MockHttpServletRequest(dispatcherServlet.getServletContext(), "POST", "/api/erp/costcenters");
		request.setContent(mapper.writeValueAsBytes(pid));
		request.setContentType("application/json");
		response = new MockHttpServletResponse();
		try {
			dispatcherServlet.service(request, response);
		} catch (Exception e) {
			Throwable t = e;
			while (true){
				if (t instanceof VerificationException)
					break;
				if (t==null || t.getCause()==t){
					e.printStackTrace(System.err);
					fail("Exception "+t+" wasn't a "+VerificationException.class);
				}
				t = t.getCause();
			}
		}

	}

}

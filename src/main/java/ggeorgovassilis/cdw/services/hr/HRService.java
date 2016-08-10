package ggeorgovassilis.cdw.services.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ggeorgovassilis.cdw.time.Clock;

@RestController
@RequestMapping(value = "/api/hr")
public class HRService {

	@Autowired
	Clock clock;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public PersonnelIdentifier login(@RequestParam("email") String email, @RequestParam("password") String password) {
		if ("george@acme.com".equals(email) && "password123".equals(password)) {
			PersonnelIdentifier pid = new PersonnelIdentifier();
			pid.setEmail("george@acme.com");
			pid.setEmploymentStatus("employed");
			pid.setName("George");
			pid.setPersonnelId("pid123");
			//signature will be generated automatically
			return pid;
		} else
			throw new RuntimeException("login failed");
	}
}

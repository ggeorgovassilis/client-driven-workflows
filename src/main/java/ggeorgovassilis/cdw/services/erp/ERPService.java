package ggeorgovassilis.cdw.services.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;
import ggeorgovassilis.cdw.signature.DocumentSigner;
import ggeorgovassilis.cdw.time.Clock;

@RestController
@RequestMapping(value = "/api/erp")
public class ERPService {

	@Autowired
	DocumentSigner signer;

	@RequestMapping(method = RequestMethod.POST, value = "/costcenters")
	public ListOfCostCenters getCostCentersForUser(@RequestBody PersonnelIdentifier pid) {
		if (pid.getEmail().equals("george@acme.com")) {
			ListOfCostCenters loc = new ListOfCostCenters();
			CostCenter c1 = new CostCenter("c1", "Travel expenses");
			CostCenter c2 = new CostCenter("c2", "R&D");
			signer.sign(c1);
			signer.sign(c2);
			loc.getCostCenters().add(c1);
			loc.getCostCenters().add(c2);
			return loc;
		} else
			throw new RuntimeException("I don't know this user");
	}
}

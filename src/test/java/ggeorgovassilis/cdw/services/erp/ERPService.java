/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ggeorgovassilis.cdw.DocumentSigningAspect;
import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;
import ggeorgovassilis.cdw.signature.DocumentSigner;

/**
 * ERP service which exposes cost center related functions over a REST API.
 * The service is assumed decorated by the {@link DocumentSigningAspect}
 * @author george georgovassilis
 *
 */

@RestController
@RequestMapping(value = "/api/erp")
public class ERPService {

	@Autowired
	DocumentSigner signer;

	/**
	 * Get a list of cost centers. 
	 * @param pid
	 * @return
	 */
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

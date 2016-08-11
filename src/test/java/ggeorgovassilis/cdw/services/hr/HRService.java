/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ggeorgovassilis.cdw.DocumentSigningAspect;
import ggeorgovassilis.cdw.time.Clock;

/**
 * HRservice which exposes employee identification over a REST API.
 * The service is assumed decorated by the {@link DocumentSigningAspect}
 * @author george georgovassilis
 *
 */

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

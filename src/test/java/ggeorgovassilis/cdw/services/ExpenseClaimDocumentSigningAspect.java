/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ggeorgovassilis.cdw.DocumentSigningAspect;
import ggeorgovassilis.cdw.signature.SignedDocument;

/**
 * Declare join points for REST services
 * @author george georgovassilis
 *
 */
@RestControllerAdvice
@Aspect
public class ExpenseClaimDocumentSigningAspect extends DocumentSigningAspect{

	@Before("execution(* ggeorgovassilis.cdw.services.*.*Service.*(..))")
	public void anyServiceMethod(JoinPoint o) {
		super.anyServiceMethod(o);
	}

	@AfterReturning(value = "@target(org.springframework.web.bind.annotation.RestController) && execution(* ggeorgovassilis.cdw.services.*.*.*(..))", returning = "document")
	public void signReturnValue(JoinPoint o, SignedDocument document) {
		super.signReturnValue(o, document);
	}

}

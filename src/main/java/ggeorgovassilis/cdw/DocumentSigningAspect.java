/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ggeorgovassilis.cdw.signature.DocumentSigner;
import ggeorgovassilis.cdw.signature.DocumentVerifier;
import ggeorgovassilis.cdw.signature.SignedDocument;
import ggeorgovassilis.cdw.signature.VerificationException;

/**
 * Spring AOP aspect that applies to services annotated with RestController when one of their methods is invoked and:
 * a) verifies all method arguments which extend {@link SignedDocument}
 * b) computes the signature for return values which extend {@link SignedDocument}
 * @author george georgovassilis
 *
 */

@RestControllerAdvice
@Aspect
public class DocumentSigningAspect {

	@Autowired
	DocumentSigner documentSigner;
	@Autowired
	DocumentVerifier verifier;
	
	@Before("execution(* ggeorgovassilis.cdw.services.*.*Service.*(..))")
	public void anyServiceMethod(JoinPoint o) {
		for (Object arg:o.getArgs())
			if (arg instanceof SignedDocument){
				SignedDocument doc = (SignedDocument)arg;
				if (!verifier.verify(doc))
					throw new VerificationException("Document failed to verify");
			}
	}

	@AfterReturning(value = "@target(org.springframework.web.bind.annotation.RestController) && execution(* ggeorgovassilis.cdw.services.*.*.*(..))", returning = "document")
	public void signReturnValue(JoinPoint o, SignedDocument document) {
		if (document!=null){
			documentSigner.sign(document);
		}
	}
}

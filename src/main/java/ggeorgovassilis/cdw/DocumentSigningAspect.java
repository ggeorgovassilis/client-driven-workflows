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
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import ggeorgovassilis.cdw.signature.DocumentSignerJsonImpl;
import ggeorgovassilis.cdw.signature.DocumentVerifierJsonImpl;
import ggeorgovassilis.cdw.signature.DocumentVerifier;
import ggeorgovassilis.cdw.signature.DocumentSigner;
import ggeorgovassilis.cdw.signature.SignedDocument;

/**
 * Base class for a Spring AOP aspect that applies to services annotated with RestController when one of their methods is invoked and:
 * a) verifies all method arguments which extend {@link SignedDocument}
 * b) computes the signature for return values which extend {@link SignedDocument}
 * 
 * @author george georgovassilis
 *
 */

public abstract class DocumentSigningAspect {

	/**
	 * {@link DocumentSignerJsonImpl} to use for signing documents returned by services
	 */
	@Autowired
	DocumentSigner documentSignerJsonImpl;
	
	/**
	 * {@link DocumentVerifierJsonImpl} to use for verifying service method arguments
	 */
	@Autowired
	DocumentVerifier verifier;
	
	public DocumentSigner getDocumentSigner() {
		return documentSignerJsonImpl;
	}

	public DocumentVerifier getVerifier() {
		return verifier;
	}

	public void setDocumentSigner(DocumentSigner documentSigner) {
		this.documentSignerJsonImpl = documentSigner;
	}

	public void setVerifier(DocumentVerifier verifier) {
		this.verifier = verifier;
	}

	@Before("execution(* ggeorgovassilis.cdw.services.*.*Service.*(..))")
	public void anyServiceMethod(JoinPoint o) {
		for (Object arg:o.getArgs())
			if (arg instanceof SignedDocument){
				SignedDocument doc = (SignedDocument)arg;
				verifier.verify(doc);
			}
	}

	@AfterReturning(value = "@target(org.springframework.web.bind.annotation.RestController) && execution(* ggeorgovassilis.cdw.services.*.*.*(..))", returning = "document")
	public void signReturnValue(JoinPoint o, SignedDocument document) {
		if (document!=null){
			documentSignerJsonImpl.sign(document);
		}
	}
}

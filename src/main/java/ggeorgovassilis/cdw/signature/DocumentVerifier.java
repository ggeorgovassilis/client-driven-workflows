/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ggeorgovassilis.cdw.time.Clock;

/**
 * Verifies a {@link SignedDocument}
 * @author george georgovassilis
 *
 */

@Component
public class DocumentVerifier {

	@Autowired
	Clock clock;
	
	@Autowired
	HashcodeComputer hashcodeComputer;

	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Verify document by computing the document's signature and comparing it to the provided signature {@link SignedDocument#signature}
	 * @param document
	 * @return
	 */
	public boolean verify(SignedDocument document) {

		try {
			Signature signature = document.getSignature();
			if (signature == null)
				return false;
			String claimedHashCode = signature.getHashcode();
			signature.setHashcode(null);

			String json = mapper.writeValueAsString(document);
			signature.setHashcode(claimedHashCode);
			
			String actualHashcode = hashcodeComputer.computeHashcode(json);
			if (!actualHashcode.equals(claimedHashCode))
				return false;
			Date now = clock.getNow();
			if (now.after(signature.getValidUntil()))
				return false;
			return true;
		} catch (Exception e) {
			throw new VerificationException(e.getMessage());
		}

	}
}

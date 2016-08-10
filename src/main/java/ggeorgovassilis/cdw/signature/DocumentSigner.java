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
 * Signs a {@link SignedDocument}
 * @author george georgovassilis
 *
 */

@Component
public class DocumentSigner {

	@Autowired
	Clock clock;

	@Autowired
	HashcodeComputer hashcodeComputer;
	
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Sign a {@link SignedDocument}. Documents must be serializable to JSON. Their (first order shallow) {@link SignedDocument#signature}
	 * will be replaced by the computed signature.
	 * @param document
	 */
	public void sign(SignedDocument document) {
		try {
			Date expiration = clock.getNowPlus(60);
			Signature signature = new Signature();
			signature.setHashcode(null);
			signature.setValidUntil(expiration);
			document.setSignature(signature);
			
			String json = mapper.writeValueAsString(document);
			String hashcode = hashcodeComputer.computeHashcode(json);
			document.getSignature().setHashcode(hashcode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

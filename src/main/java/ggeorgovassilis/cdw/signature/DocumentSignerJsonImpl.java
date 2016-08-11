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
 * Implementation that depends on a {@link Clock} and {@link HashcodeComputer} to sign JSON documents
 * @author george georgovassilis
 *
 */

@Component
public class DocumentSignerJsonImpl implements DocumentSigner {

	@Autowired
	protected Clock clock;

	@Autowired
	protected HashcodeComputer hashcodeComputer;
	
	protected ObjectMapper mapper = new ObjectMapper();

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public HashcodeComputer getHashcodeComputer() {
		return hashcodeComputer;
	}

	public void setHashcodeComputer(HashcodeComputer hashcodeComputer) {
		this.hashcodeComputer = hashcodeComputer;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
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

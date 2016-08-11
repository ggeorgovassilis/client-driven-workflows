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
 * {@link DocumentVerifier} implementation that verifies JSON documents signed by {@link DocumentSignerJsonImpl}
 * 
 * @author george georgovassilis
 *
 */

@Component
public class DocumentVerifierJsonImpl implements DocumentVerifier {

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
	public void verify(SignedDocument document) throws VerificationException {

		try {
			Signature signature = document.getSignature();
			if (signature == null)
				throw new VerificationException("Signature is null");
			String claimedHashCode = signature.getHashcode();
			signature.setHashcode(null);

			String json = mapper.writeValueAsString(document);
			signature.setHashcode(claimedHashCode);

			String actualHashcode = hashcodeComputer.computeHashcode(json);
			if (!actualHashcode.equals(claimedHashCode))
				throw new VerificationException("document hashcode differs from expected hashcode");
			Date now = clock.getNow();
			if (now.after(signature.getValidUntil()))
				throw new VerificationException("Signature expired");
		} catch (VerificationException ve) {
			throw ve;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
}

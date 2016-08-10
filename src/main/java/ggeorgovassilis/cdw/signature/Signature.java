/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Signature of a {@link SignedDocument}
 * @author george georgovassilis
 *
 */

@JsonPropertyOrder(alphabetic=true)
public class Signature implements Serializable {

	/**
	 * Hashcode of the document
	 */
	protected String hashcode;
	
	/**
	 * Date after which the signature won't be valid any more
	 */
	protected Date validUntil;

	public String getHashcode() {
		return hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
}

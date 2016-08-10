/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/** 
 * Base class of documents that can be serialized to a textual representation and for which a {@link Signature} can be
 * computed. Classes extending SignedDocument can contain fields which are also SignedDocument instances; each such document
 * contains its own {@link Signature}, though "parent" documents may chose to include signatures of child documents in the
 * computation of their signatures
 * @author george georgovassilis
 *
 */

@JsonPropertyOrder(alphabetic=true)
public abstract class SignedDocument implements Serializable {

	protected Signature signature;

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}
}

/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

/**
 * Verifies a {@link SignedDocument}
 * 
 * @author george georgovassilis
 *
 */

public interface DocumentVerifier {

	/**
	 * Verify document by computing the document's signature and comparing it to
	 * the provided signature {@link SignedDocument#signature}
	 * 
	 * @param document
	 * @return
	 */
	void verify(SignedDocument document) throws VerificationException;

}
/*  Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

/**
 * Signs a {@link SignedDocument}
 * 
 * @author george georgovassilis
 *
 */

public interface DocumentSigner {

	/**
	 * Sign a {@link SignedDocument}. Documents must be serializable to JSON.
	 * Their (first order shallow) {@link SignedDocument#signature} will be
	 * replaced by the computed signature.
	 * 
	 * @param document
	 */
	void sign(SignedDocument document);

}
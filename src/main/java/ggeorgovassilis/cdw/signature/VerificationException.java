/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

import ggeorgovassilis.cdw.DocumentSigningAspect;

/**
 * Thrown by {@link DocumentSigningAspect} when a verification fails
 * @author george georgovassilis
 *
 */
public class VerificationException extends RuntimeException{

	public VerificationException(String msg) {
		super(msg);
	}
}

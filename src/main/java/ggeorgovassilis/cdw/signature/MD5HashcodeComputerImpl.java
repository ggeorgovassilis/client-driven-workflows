/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * {@link HashcodeComputer} implementation with salted MD5.
 * @author george georgovassilis
 *
 */
public class MD5HashcodeComputerImpl implements HashcodeComputer {

	protected String salt="";

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Override
	public String computeHashcode(String text) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(salt.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] result = md5.digest(text.getBytes());
		String base64HashCode = Base64.getEncoder().encodeToString(result);
		return base64HashCode;
	}

}

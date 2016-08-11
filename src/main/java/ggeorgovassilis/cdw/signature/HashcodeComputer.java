/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.signature;

/**
 * Interface for computing hashcodes
 * @author george georgovassilis
 *
 */

public interface HashcodeComputer {

	String computeHashcode(String text);
}

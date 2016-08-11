/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.time;

import java.util.Date;

/**
 * Convenient factory for times which helps stable and predictable test
 * conditions. Returns the current time which must be set via
 * {@link #setNow(Date)}; this is NOT a wrapper around
 * {@link System#currentTimeMillis()}
 * 
 * @author george georgovassilis
 *
 */

public interface Clock {

	/**
	 * Return current time
	 * @return
	 */
	Date getNow();

	/**
	 * Return current time off-set by "second" seconds
	 * @param seconds
	 * @return
	 */
	Date getNowPlus(int seconds);
}

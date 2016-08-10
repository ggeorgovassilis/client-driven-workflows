/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

/**
 * Convenient factory for times which helps stable and predictable test
 * conditions. Returns the current time which must be set via
 * {@link #setNow(Date)}; this is NOT a wrapper around
 * {@link System#currentTimeMillis()}
 * 
 * @author george georgovassilis
 *
 */

@Component
public class Clock {

	Date now = new Date();

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public Date getNowPlus(int seconds) {
		Calendar c = new GregorianCalendar();
		c.setTime(getNow());
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}
}

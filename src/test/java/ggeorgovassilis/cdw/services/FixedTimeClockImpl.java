/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

import ggeorgovassilis.cdw.time.Clock;

/**
 * Returns always a fixed time; used for unit tests 
 * @author george georgovassilis
 *
 */

@Component
public class FixedTimeClockImpl implements Clock{

	Date now;

	@Override
	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	@Override
	public Date getNowPlus(int seconds) {
		Calendar c = new GregorianCalendar();
		c.setTime(getNow());
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}
}

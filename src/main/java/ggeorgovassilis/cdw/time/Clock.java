package ggeorgovassilis.cdw.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class Clock {

	Date now = new Date();

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}
	
	public Date getNowPlus(int seconds){
		Calendar c = new GregorianCalendar();
		c.setTime(getNow());
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}
}

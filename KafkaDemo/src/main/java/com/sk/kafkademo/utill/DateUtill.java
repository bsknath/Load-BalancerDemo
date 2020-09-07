package com.sk.kafkademo.utill;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtill {
	
	public static Timestamp getMidnightTime () {
		LocalDateTime ldt = LocalDateTime.now().plusDays(1);
		LocalDateTime midnight = ldt.toLocalDate().atStartOfDay();
		
		long md = midnight.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		
		return new Timestamp(md);
		
	}
	
	public static Timestamp getTime(String time) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
		Date d = sdf.parse(time);
		
		Timestamp ts = new Timestamp(d.getTime());
		return ts;
		
	}

}

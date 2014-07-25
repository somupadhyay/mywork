/**
 * 
 */
package com.spring.poc.spring.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Somnath.Upadhyay
 *
 */

@EnableScheduling
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat dayFormat = new SimpleDateFormat("MMM");
	
	@Scheduled(fixedRate=5000)
	public void reportCurrentTime(){
		System.out.println("The time is now "+dateFormat.format(new Date()));
	}
	
	@Scheduled(fixedRate=10000)
	public void reportMonth(){
		System.out.println("The day is "+dayFormat.format(new Date()));
	}
	
	@Scheduled(cron="${corn.value:*/10 * * * * *}")
	public void reportCornTime(){
		System.out.println("Corn time is now "+dateFormat.format(new Date()));
	}
}

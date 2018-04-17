package com.vp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BrowserThread implements Runnable {

	private String url;
	private int waitTime;
	private int times;

	public BrowserThread(String url, int waitTime, int times) {
		this.url = url;
		this.waitTime = waitTime;
		this.times = times;
	}

	public void run() {

		Runtime runtime = Runtime.getRuntime();
		Process p;
		try {
			for (int x = 0; x < times; x++) {
				p = runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
				System.out.println(p.isAlive());
				Date date = new Date();
				DateFormat formatter = new SimpleDateFormat("HHmmssSSS");
				String tag = "test" + formatter.format(date);

				System.out.println("opening url");
				Thread.sleep(waitTime);
				killItnow();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void killItnow() {
		System.out.println("attempting to kill");
		try {
			Process pv = Runtime.getRuntime().exec("TASKKILL /f /t /im iexplore.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

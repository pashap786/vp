package com.vp;

import java.net.HttpURLConnection;
import java.net.URL;

public class ViperThread implements Runnable {
	
	private int times;
	private int waitTime;
	private URL url;
	
	public ViperThread() {}
	
	public ViperThread(int times, URL url, int waitTime) {
		this.times = times;
		this.url = url;
		this.waitTime = waitTime;
	}
	
	
	public void run() {

		try {
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			for (int x = 0; x < times; x++) {
				String user = UserAgents.ieAgents();
				System.out.println("User agent: " + user);
				httpCon.addRequestProperty("User-Agent", user);
				httpCon.connect();
				System.out.println(" opened connection. Run #"+x);
				//61700
				Thread.sleep(waitTime);
				httpCon.disconnect();
				System.out.println(" closed connection Run#"+x);
			}
			System.out.println("EXITING");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}

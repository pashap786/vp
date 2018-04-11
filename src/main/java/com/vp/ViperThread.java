package com.vp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class ViperThread implements Runnable {
	
	private static Logger log = Logger.getLogger(ViperThread.class.getName());
	
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
				log.info("User agent: " + user);
				httpCon.addRequestProperty("User-Agent", user);
				httpCon.connect();
				log.info(" opened connection. Run #"+x);
				//61700
				Thread.sleep(waitTime);
				httpCon.disconnect();
				log.info(" closed connection Run#"+x);
			}
			System.out.println("EXITING");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}

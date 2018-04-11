package com.vp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Main {

	private static String testString = "https://www.youtube.com/watch?v=CwpUFZpQHb4";


	
	public static void main(String[] args) {
		URL url = null;
		boolean validUrl = false;
		Scanner in = new Scanner(System.in);

		while (!validUrl) {
			System.out.println("Enter the url:");
			String urls = in.nextLine();
			try {
				url = new URL(urls);
			} catch (MalformedURLException e) {
				validUrl = false;
				continue;
			}
			validUrl = true;
		}

		System.out.println("Do you want to run this in desktop mode? Y/N: ");
		String ans = in.nextLine();
		if (ans.equalsIgnoreCase("y")) {
			DesktopMode dm = new DesktopMode(in);

			dm.runDesktop(url);
		} else {
			System.out.println("Please enter in seconds the length of your video. ");
			int seconds = in.nextInt();
			System.out.println("Enter the amount of times the link should be visited: ");
			int times = in.nextInt();
			System.out.println("Enter the number of threads to use: ");
			int threads = in.nextInt();
			
			threadService(threads, times, url, seconds);

		}
	}

	public static void threadService(int threads, int times, URL url, int seconds) {
		ExecutorService executor = Executors.newFixedThreadPool(threads);

		for (int i = 0; i < threads; i++) {
			ViperThread vt = new ViperThread(times, url, seconds);
			executor.execute(vt);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println("\nFinished all threads");

	}

}

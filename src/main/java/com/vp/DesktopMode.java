package com.vp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class DesktopMode {

	private Scanner in;

	public DesktopMode(Scanner in) {
		this.in = in;
	}

	public void runDesktop(URL url) {
		System.out.println("Warning: Using this mode will open X amount of browsers for your url.");
		System.out.println("Please enter the number of screens to open: ");
		String ans = in.nextLine();

		int times = Integer.valueOf(ans);

		for (int x = 0; x <= times; x++) {
			Desktop d = Desktop.getDesktop();
			try {
				d.browse(url.toURI());
				Thread.sleep(1000L);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

}

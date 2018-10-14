package cnn;



import cnn.Network;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		int counter = 0;
		
		Network net = new Network();
		
		
		net.fillNet();
		net.readConnections();
		
		ImageLoad IL = new ImageLoad();
		IL.loadAllImages();
		
		net.parseCsv();
		while(counter < 5000) {
			
			net.feedForward(counter);
			counter++;
			System.out.println("Era: " + counter);
			
			
		
		}
		
		
		
		
	}
		
		
	

	Main() {
	}

}

package com.vts.listner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private static final int PORT = 1529; 
	
	public static void main(String[] args) {
		ServerSocket listener;
		try {
			listener = new ServerSocket(PORT);
			System.out.print("Getting connection...");
			Socket server = listener.accept();//establishes connection
			new SaveGpsData(server);
		} catch (IOException e) {
			System.out.print("connection Exception : "+e);
		}
		
	}
	
	@Override
	public void run() {
		
	}

}

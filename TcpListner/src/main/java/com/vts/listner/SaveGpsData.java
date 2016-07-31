package com.vts.listner;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SaveGpsData implements Runnable {
	private Socket rawData;
	public SaveGpsData(Socket rawData) {
		this.rawData=rawData;
	}
	
	@Override
	public void run()
	  {
	    try
	    {
	    	System.out.println("run method in doComms....\n");
	      DataInputStream in = new DataInputStream(rawData.getInputStream());
	      OutputStreamWriter outgoing = new OutputStreamWriter(rawData.getOutputStream());
	      System.out.println("........."+in.readChar()+"\n"); 
	      System.out.println("input String========="+in.read());
	    
	      System.out.println("server_port:"+rawData.getPort());
	      System.out.println("Outgoing:"+outgoing);
	      
	    /*
	    	   Scanner newline = new Scanner(in);
	    	         if(newline.useDelimiter("\\)")!=null){
	    	        	 newline=newline.useDelimiter("\\)");
	    	        	 while(newline.hasNext()){
	    	     	    	System.out.println(newline.next());
	    	     	    	
	    	     	    	System.out.println("server_port:"+rawData.getPort());
	    	     	    	System.out.println("Outgoing:"+outgoing);
	    	     	    } 
	    	         }*/
	    	   
	     
	    }
	    catch (IOException ioe) {
	      System.out.println("IOException on socket listen: " + ioe);
	      ioe.printStackTrace();
	    }finally{
	    	System.out.println("Sucessfully Done");
	    }
	  }
}

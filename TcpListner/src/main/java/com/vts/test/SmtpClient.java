package com.vts.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vts.common.Attachment;

public class SmtpClient {
	public static void main(String[] args) {
		Socket client = null;  
	    DataOutputStream os = null;
	    DataInputStream is = null;	
	    StringBuilder rawdata=new StringBuilder("");
        
        try {
        	client = new Socket("127.0.0.1", 9001);
            os = new DataOutputStream(client.getOutputStream());
            is = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            System.err.println("Don't know about host: hostname"+e);
        } 
        
        if (client != null && os != null && is != null) {
        	try {
        		
        		 Attachment attach = new Attachment();
        		    attach.buffer = ByteBuffer.allocate(2048000);
        		    attach.isRead = false;
        		    attach.mainThread = Thread.currentThread();
        		    Charset cs = Charset.forName("UTF-8");
        		//while(true){
        				Date time = new Date();
        			    DateFormat timeFormat = new SimpleDateFormat("HHmmss");
        		        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        		        rawdata.append(dateFormat.format(time).toString());
        		        rawdata.append(":");
        		        rawdata.append(timeFormat.format(time).toString());
        		        rawdata.append("\n");
        		        String msg="hello";//rawdata.toString();
        		        byte[] data = msg.getBytes(cs);
             		    attach.buffer.put(data);
             		    attach.buffer.flip();
					os.writeBytes(msg);
					msg="";
					Thread.sleep(1000L);
        	//	}
			} catch (IOException | InterruptedException e) {
				 System.err.println("Don't know about host: hostname"+e);
			}finally {
				try {
					os.close();
					is.close();
	                client.close();
				} catch (IOException e) {
					
				}
                
			}
        }
	}
	
}

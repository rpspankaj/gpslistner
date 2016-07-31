package com.vts.testclient;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import com.vts.common.Attachment;
import com.vts.common.ReadWriteHandler;

public class Client {
  public static void main(String[] args) throws Exception {
    AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
    SocketAddress serverAddr = new InetSocketAddress("localhost", 5000);
    Future<Void> result = channel.connect(serverAddr);
    result.get();
    System.out.println("Connected");
   // while(true){
    	Attachment attach = new Attachment();
        attach.channel = channel;
        attach.buffer = ByteBuffer.allocate(2048);
        attach.isRead = false;
        attach.mainThread = Thread.currentThread();
	    Charset cs = Charset.forName("UTF-8");
	    Date time = new Date();
	    StringBuilder msg = new StringBuilder();
	    DateFormat timeFormat = new SimpleDateFormat("HHmmss");
        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        msg.append(dateFormat.format(time).toString());
        msg.append(":");
        msg.append(timeFormat.format(time).toString());
        msg.append("\n");
        
	    byte[] data = msg.toString().getBytes(cs);
	    attach.buffer.put(data);
	    attach.buffer.flip();
	    ReadWriteHandler readWriteHandler = new ReadWriteHandler();
	    channel.write(attach.buffer, attach, readWriteHandler);
   // attach.mainThread.join();
  //  }
  }
}
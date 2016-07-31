package com.vts.common;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
	@Override
	  public void completed(Integer result, Attachment attach) {
	    if (result == -1) {
	      try {
	        attach.client.close();
	        System.out.format("Stopped   listening to the   client %s%n",
	            attach.clientAddr);
	      } catch (IOException ex) {
	        ex.printStackTrace();
	      }
	      return;
	    }

	    if (attach.isRead) {
	    System.out.println("---------------------"+attach.buffer);
	      attach.buffer.flip();
	      int limits = attach.buffer.limit();
	      byte bytes[] = new byte[limits];
	      attach.buffer.get(bytes, 0, limits);
	      Charset cs = Charset.forName("UTF-8");
	      String msg = new String(bytes, cs);
	      System.out.format("Client at  %s  says: %s%n", attach.clientAddr,
	          msg);
	      attach.isRead = false; // It is a write
	      attach.buffer.rewind();

	    } else {
	      // Write to the client
	      attach.client.write(attach.buffer, attach, this);
	      attach.isRead = true;
	      attach.buffer.clear();
	      attach.client.read(attach.buffer, attach, this);
	    }
	  }

	  @Override
	  public void failed(Throwable e, Attachment attach) {
	    e.printStackTrace();
	  }
   }
 

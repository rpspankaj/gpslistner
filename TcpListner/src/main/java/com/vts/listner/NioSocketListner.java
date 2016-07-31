package com.vts.listner;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.vts.common.Attachment;
import com.vts.common.ReadWriteHandler;

public class NioSocketListner
{
    public NioSocketListner()
    {
        try
        {
            // Create an AsynchronousServerSocketChannel that will listen on port 5000
           /* final AsynchronousServerSocketChannel listener =
                    AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("",5000));
            */
           final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open();
                String host = "localhost";
                int port = 5000;
                InetSocketAddress sAddr = new InetSocketAddress(host, port);
                listener.bind(sAddr);
                System.out.format("Server is listening at %s%n", sAddr);
                Attachment attach = new Attachment();
                attach.listener = listener;
             // Listen for a new request
                listener.accept(attach, new ConnectionHandler());
                Thread.currentThread().join();
            
        }  
        catch ( InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        NioSocketListner listener = new NioSocketListner();
       /* try
        {
            Thread.sleep( 60000 );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }*/
    }
}

    class ConnectionHandler implements
    CompletionHandler<AsynchronousSocketChannel, Attachment> {
  @Override
  public void completed(AsynchronousSocketChannel client, Attachment attach) {
    try {
      SocketAddress clientAddr = client.getRemoteAddress();
      System.out.format("Accepted a  connection from  %s%n", clientAddr);
      attach.listener.accept(attach, this);
      ReadWriteHandler rwHandler = new ReadWriteHandler();
      Attachment newAttach = new Attachment();
      newAttach.listener = attach.listener;
      newAttach.client = client;
      newAttach.buffer = ByteBuffer.allocate(2048000);
      newAttach.isRead = true;
      newAttach.clientAddr = clientAddr;
      client.read(newAttach.buffer, newAttach, rwHandler);
    } catch (IOException e) {
      System.out.println("exception" +e);
    }
  }

  @Override
  public void failed(Throwable e, Attachment attach) {
    System.out.println("Failed to accept a  connection.");
    e.printStackTrace();
  }
}
    
   
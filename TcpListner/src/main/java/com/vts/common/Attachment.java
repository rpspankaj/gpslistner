package com.vts.common;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

public class Attachment {
	public AsynchronousServerSocketChannel listener;
	public AsynchronousSocketChannel client;
	public ByteBuffer buffer;
	public SocketAddress clientAddr;
	public boolean isRead;
	
	public AsynchronousSocketChannel channel;
	public Thread mainThread;
}
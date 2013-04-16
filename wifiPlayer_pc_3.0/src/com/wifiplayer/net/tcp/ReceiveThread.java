package com.wifiplayer.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


/**
 * 接收线程
 * @author Administrator
 *
 */
public class ReceiveThread implements Runnable {

	public static final int CLIENT_PORT = 1719;
	public static final int EQUIPMENT_PORT = 1718;
	
	Socket s;
	public boolean isReceive = true;
	
	public ReceiveThread(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public void run() {
		receive();
	}

	private void receive() {
		int len;
		while(isReceive){
			try {
				InputStream is = s.getInputStream();
				byte[] buff = new byte[1024];
				while ((len = is.read(buff)) != -1) {
					System.out.println("服务器接收的内容：" + new String(buff));
					new SendThread(s, "我收到了你发来的信息".getBytes()).start();
				}
			} catch (IOException e) {
				
			}
		}
		
	}

}

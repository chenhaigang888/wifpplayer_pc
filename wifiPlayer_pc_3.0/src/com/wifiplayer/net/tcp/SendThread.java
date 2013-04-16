package com.wifiplayer.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


/**
 * 发送信息的线程
 * 
 * @author Administrator
 * 
 */
public class SendThread extends Thread {

	private Socket socket;
	private byte[] sendData;

	public SendThread(Socket socket, byte[] sendData) {
		super();
		this.socket = socket;
		this.sendData = sendData;
	}

	@Override
	public void run() {
		send(sendData);
	}

	/**
	 * 发送数据
	 * 
	 * @param packages
	 */
	private void send(byte[] sendData) {

		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			os.write(sendData);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

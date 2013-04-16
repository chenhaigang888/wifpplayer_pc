package com.wifiplayer.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.wifiplayer.bean.packages.Head;
import com.wifiplayer.utils.ReadDirectoryFile;


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
		while(isReceive){
			try {
				InputStream is = s.getInputStream();
				
				while (true) {
					byte[] headArray = new byte[12];
					int len = is.read(headArray);
					if (len == 12) {
						Head head = Head.resolveHead(headArray);
						switch (head.getCmd()) {
						case Head.CONN_SERVER://客户端连接服务器
							connServer();
							break;

						default:
							break;
						}
					}
				}
			} catch (IOException e) {
				
			}
		}
		
	}

	/**
	 * 客户端连接服务器
	 */
	private void connServer() {
		ReadDirectoryFile.systemRoots().toString();
		
	}

}

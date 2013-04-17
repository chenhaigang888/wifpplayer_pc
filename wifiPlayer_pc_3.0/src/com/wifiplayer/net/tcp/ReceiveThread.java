package com.wifiplayer.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.wifiplayer.bean.packages.Head;
import com.wifiplayer.bean.packages.Packages;
import com.wifiplayer.bean.packages.send.ConnServerReplyBody;
import com.wifiplayer.utils.ReadDirectoryFile;

/**
 * 接收线程
 * 
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
		while (isReceive) {
			try {
				InputStream is = s.getInputStream();

				byte[] headArray = new byte[12];
				int len = is.read(headArray);
				if (len == 12) {
					Head head = Head.resolveHead(headArray);
					switch (head.getCmd()) {
					case Head.CONN_SERVER:// 客户端连接服务器
						connServer();
						break;
					case Head.OPEN_DIR:
						byte[] bodyArray = new byte[head.getPackBodyLenth()];
						len = is.read(bodyArray);
						openDir(new String(bodyArray));
						break;
					default:
						break;
					}
				}

			} catch (IOException e) {

			}
		}

	}

	/**
	 * 获取文件夹
	 */
	private void openDir(String path) {
		String str = ReadDirectoryFile.listFile(path).toString();
		System.out.println("str:" + str);
		byte[] connServerArray = str.getBytes();
		Head head = new Head(Head.OPEN_DIR_REPLY,
				(short) connServerArray.length, 0, 0);
		ConnServerReplyBody csrb = new ConnServerReplyBody(connServerArray);
		Packages p = new Packages(head, csrb);
		new SendThread(s, p.getPackage()).start();
	}

	/**
	 * 客户端连接服务器
	 */
	private void connServer() {
		String str = ReadDirectoryFile.systemRoots().toString();
		byte[] connServerArray = str.getBytes();
		Head head = new Head(Head.CONN_SERVER_REPLY,
				(short) connServerArray.length, 0, 0);
		ConnServerReplyBody csrb = new ConnServerReplyBody(connServerArray);
		Packages p = new Packages(head, csrb);
		new SendThread(s, p.getPackage()).start();
	}

}

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

	Socket s;
	public boolean isReceive = true;

	public ReceiveThread(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public void run() {
		receive();
		System.out.println("监听结束");
	}

	private void receive() {
		while (isReceive) {
			System.out.println("isReceive:" + isReceive);
			byte[] headArray = new byte[14];
			int len = readData(s, 0, headArray);
			if (len == -1) {
				isReceive = false;
				break;
			}
			Head head = Head.resolveHead(headArray);
			byte[] bodyArray = new byte[head.getPackBodyLenth()];
			len = readData(s, 0, bodyArray);
			if (len == -1) {
				isReceive = false;
				break;
			}
			switch (head.getCmd()) {
			case Head.CONN_SERVER:// 客户端连接服务器
				connServer();
				break;
			case Head.OPEN_DIR:
				openDir(new String(bodyArray));
				break;
			default:
				break;
			}

		}
		
	}

	/**
	 * 读取数据
	 * @param s socket
	 * @param readPosition 从什么地方开始读
	 * @param array 需要读入的数组
	 * @return
	 */
	public int readData(Socket s, int readPosition, byte[] array) {
		int len = 0;
		InputStream is = null;
		try {
			is = s.getInputStream();
			len = is.read(array, readPosition, array.length - readPosition);
			if (len == -1) {
				close(s, is);
				return len;
			}
			if ((len + readPosition) < array.length) {
				readData(s, len + readPosition, array);
			}
		} catch (IOException e) {
			e.printStackTrace();
			close(s, is);
		}

		return len + readPosition;
	}

	/**
	 * 关闭资源
	 * 
	 * @param s2
	 * @param is
	 */
	private void close(Socket s2, InputStream is) {
		try {
			isReceive = false;
			s.close();
			is.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * 获取文件夹
	 */
	private void openDir(String path) {
		try {
			String str = ReadDirectoryFile.listFile(path).toString();
			System.out.println("str:" + str);
			byte[] connServerArray = str.getBytes();
			System.err.println("本次发送字符串的长度:" + connServerArray.length);
			Head head = new Head(Head.OPEN_DIR_REPLY, connServerArray.length,
					0, 0);
			ConnServerReplyBody csrb = new ConnServerReplyBody(connServerArray);
			Packages p = new Packages(head, csrb);
			new SendThread(s, p.getPackage()).start();
		} catch (Exception e) {
			System.out.println("没有找到相关内容");
		}

	}

	/**
	 * 客户端连接服务器
	 */
	private void connServer() {
		String str = ReadDirectoryFile.systemRoots().toString();
		System.out.println("根目录：" + str);
		byte[] connServerArray = str.getBytes();
		Head head = new Head(Head.CONN_SERVER_REPLY, connServerArray.length, 0,
				0);
		ConnServerReplyBody csrb = new ConnServerReplyBody(connServerArray);
		Packages p = new Packages(head, csrb);
		new SendThread(s, p.getPackage()).start();
	}

}

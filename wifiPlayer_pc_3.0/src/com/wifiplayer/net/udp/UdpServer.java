package com.wifiplayer.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * UDP服务器 用户客户端寻找Server使用
 * @author Administrator
 *
 */
public class UdpServer extends Thread{
	
	public static final int PORT = 9527;

	public void startUdpServer () throws IOException {
		byte[] receBuf = new byte[100];
		DatagramSocket ds = new DatagramSocket(PORT);//UDPsocket
		DatagramPacket recevDp = new DatagramPacket(receBuf, receBuf.length);
		ds.receive(recevDp);//启动监听
		String recvStr = new String(recevDp.getData(), 0, recevDp.getLength());
		System.out.println("接收到的UDP内容：" + recvStr);
		/*发送回复信息*/
		int port = recevDp.getPort();//发送者的端口
		InetAddress addr = recevDp.getAddress();//发送者的ip地址
		String sendStr = "hello ! I'm Server";
		byte[] sendBuf = sendStr.getBytes();
		DatagramPacket sendDp = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
		ds.send(sendDp);
		ds.close();
		startUdpServer();
	}

	@Override
	public void run() {
		try {
			startUdpServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

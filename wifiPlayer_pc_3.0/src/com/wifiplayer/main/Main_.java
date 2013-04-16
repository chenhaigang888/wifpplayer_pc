package com.wifiplayer.main;

import com.wifiplayer.net.tcp.ServerManager;
import com.wifiplayer.net.udp.UdpServer;



/**
 * 软件启动类
 * @author chenkaigang
 *
 */
public class Main_ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 new UdpServer().start();//启动UDP服务器
		 ServerManager sm = new ServerManager();
		 sm.startServer();
	}

}

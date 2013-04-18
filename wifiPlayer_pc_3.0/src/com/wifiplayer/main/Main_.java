package com.wifiplayer.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
//		 test();
		
	}
	
	public static void test() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\场景");
		 long time = file.lastModified();//返回文件最后修改时间，是以个long型毫秒数
		 String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
		 System.out.println(ctime);
	}

}

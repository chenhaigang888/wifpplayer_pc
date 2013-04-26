package com.wifiplayer.main;

import java.util.Properties;

import com.wifiplayer.net.tcp.ServerManager;
import com.wifiplayer.net.udp.UdpServer;

/**
 * 软件启动类
 * 
 * @author chenkaigang
 * 
 */
public class Main_ {

	/**
	 * @param args
	 */
	public static String osName = null;//操作系统名称
	public static String userName = null;//当前登陆用户名
	public static String version = null;//操作系统版本
	public static String separator = null;//文件分隔符
	
	public static void main(String[] args) {

		new UdpServer().start();// 启动UDP服务器
		ServerManager sm = new ServerManager();
		sm.startServer();
		
		Properties properties = System.getProperties();//获取系统属性集合
		osName = properties.getProperty("os.name");
		userName = properties.getProperty("user.name");
		version = properties.getProperty("os.version");
		separator = properties.getProperty("file.separator");
		
		System.out.println("os:" + osName);
		System.out.println("userName:" + userName);
		System.out.println("version:" + version);
		System.out.println("separator:" + separator);
	}

}

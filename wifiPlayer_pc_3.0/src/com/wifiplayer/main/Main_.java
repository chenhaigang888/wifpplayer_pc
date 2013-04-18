package com.wifiplayer.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

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
	
	static String[] strKB = {"B","KB","MB","GB","TB","PB"};
	static int second = 0;//计算单位换算了多少次
	public static double js(double fileLenth) {
		if (fileLenth >= 1024) {
			fileLenth = fileLenth/ 1024;
			second ++;
			js(fileLenth);
		}
		
		return fileLenth;
		
	}
	
	public static void test() {
		File uploadFile = new File(new String("/Users/chenkaigang/Desktop/p2p.rar"));//需要上传的文件
		long upLoadFileLenth = uploadFile.length();//需要上传的长度
		long alreadyUpload = 0;//已经上传的长度
		int len = 0; //当前读取的长度
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(uploadFile, "r");
			raf.seek(0);//设置从文件的什么位子开始读取
			
			byte[] buffer = new byte[1024];
			while ((len = raf.read(buffer, 0, 1024)) != -1) {
				alreadyUpload += len;
//				OutputStream os = s.getOutputStream();
//				os.write(buffer, 0, len);
//				os.flush();
				System.err.println(new String(buffer));
				System.out.println("已经上传的长度:"+ alreadyUpload);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package com.wifiplayer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hyperic.sigar.win32.RegistryKey;

/**
 * 获取音乐和视频播放器
 * @author Administrator
 *
 */
public class GetVideoAndMusicPlayer {
	
	private static HashMap<String, Object> map = new HashMap<String, Object>();
	
	
	static{
		try {
			getDisplayNames();
		} catch (Exception e) {
			
		}
		
	}
	
	/**
	 * 获取视频播放器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getMoviePlayer(int i){
		List<HashMap<String,String>> list = (List<HashMap<String, String>>) map.get("movePlayerList");
		System.out.println("MoviePlayer:"+list);
		String moviePlayer = list.get(i).get("movePlayer");
		return moviePlayer.substring(0, moviePlayer.length()-1);
	}
	/**
	 * 获得音乐播放器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getMusicPlayer(int i){
		List<HashMap<String,String>> list = (List<HashMap<String, String>>) map.get("musicPlayerList");
		String musicPlayer = list.get(i).get("musicPlayer");
		System.out.println("musicList:"+list);
		return musicPlayer.substring(0, musicPlayer.length()-1);
	}

	/**
	 * 获取音乐和视频播放器
	 */
	private static void getDisplayNames(){
        String[] namesList=null;
        
        List<HashMap<String,String>> MoveList = new ArrayList<HashMap<String, String>>();
        List<HashMap<String,String>> MusicList = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> MovSoftwarePathMap = null;
        HashMap<String,String> MusSoftwarePathMap = null;
        
        
        try{
        	RegistryKey rk = RegistryKey.LocalMachine.openSubKey("Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall");//用于打开注册表目录。
        	namesList = rk.getSubKeyNames();//获取注册表中的所有子键名。
            for(int i=0;i<namesList.length;i++) {
            	MovSoftwarePathMap = new HashMap<String, String>();
                 MusSoftwarePathMap = new HashMap<String, String>();
            	RegistryKey subrk = null;
            	if(namesList[i].equals("XMP")){
            		subrk = rk.openSubKey(namesList[i]);
            		MovSoftwarePathMap.put("movePlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
            		MoveList.add(MovSoftwarePathMap);
            	}else if(namesList[i].equals("迅雷看看播放器")){
            		subrk = rk.openSubKey(namesList[i]);
            		MovSoftwarePathMap.put("movePlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
            		MoveList.add(MovSoftwarePathMap);
            	}else if(namesList[i].equals("QQPlayer")){
            		subrk = rk.openSubKey(namesList[i]);
	        		MovSoftwarePathMap.put("movePlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
	        		MoveList.add(MovSoftwarePathMap);
            	}else if(namesList[i].equals("StormPlayer")){
            		subrk = rk.openSubKey(namesList[i]);
            		MovSoftwarePathMap.put("movePlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
            		MoveList.add(MovSoftwarePathMap);
            	}else if(namesList[i].equals("Storm")){
            		subrk = rk.openSubKey(namesList[i]);
            		MovSoftwarePathMap.put("movePlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
            		MoveList.add(MovSoftwarePathMap);
            	}else if(namesList[i].equals("TTPlayer")){
            		subrk = rk.openSubKey(namesList[i]);
            		MusSoftwarePathMap.put("musicPlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
            		MusicList.add(MusSoftwarePathMap);
            	}else if(namesList[i].equals("KwMusic6")){
            		subrk = rk.openSubKey(namesList[i]);
            		MusSoftwarePathMap.put("musicPlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
            		MusicList.add(MusSoftwarePathMap);
            	}else if(namesList[i].equals("QQMusic")){
            		subrk = rk.openSubKey(namesList[i]);
	        		MusSoftwarePathMap.put("musicPlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
	        		MusicList.add(MusSoftwarePathMap);
            	}else if(namesList[i].equals("酷狗音乐2012_is1")){
            		subrk = rk.openSubKey(namesList[i]);
	        		MusSoftwarePathMap.put("musicPlayer", subrk.getStringValue("DisplayIcon", ""));// 获得软件路径
	        		MusicList.add(MusSoftwarePathMap);
            	}
            }
        }catch(Exception err){
        	err.printStackTrace();
        }
        map.put("musicPlayerList", MusicList);
        map.put("movePlayerList", MoveList);
    }
	
}

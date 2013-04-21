package com.wifiplayer.utils;

import java.io.IOException;

public class OpenFile {

	/**
	 * 播放视频
	 * @param videoPlayPath
	 * @param filePath
	 */
	public static boolean playMovie(String filePath, int videoPlayer) {
		Runtime rt = Runtime.getRuntime();
		String videoPlayPath = GetVideoAndMusicPlayer.getMoviePlayer(videoPlayer);
		String command = "\""+videoPlayPath+"\""+" "+"\""+filePath+"\"";
		try {
			rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 播放音乐
	 * @param videoPlayPath
	 * @param filePath
	 */
	public static boolean playMusic(String filePath, int musicPlayer) {
		Runtime rt = Runtime.getRuntime();
		String musicPlayPath = GetVideoAndMusicPlayer.getMusicPlayer(musicPlayer);
		String command = "\""+musicPlayPath+"\""+" "+"\""+filePath+"\"";
		try {
			rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static boolean macPlayer(String path) {
		Runtime rt = Runtime.getRuntime();
		String command = "open -a /Applications/MPlayerX.app " + path;
		try {
			rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return true;
	}
}

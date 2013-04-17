package com.wifiplayer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wifiplayer.bean.PcFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReadDirectoryFile {
//	ArrayList<HashMap<String,Object>> lstPaths = new ArrayList<HashMap<String,Object>>();
	/**
	 * 传一个文件夹路径返回当前文件夹下所有的文件
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unused")
	public static JSONArray listFile(String path){
		List<PcFile> list = new ArrayList<PcFile>();
		List<PcFile> dirs = new ArrayList<PcFile>();
		List<PcFile> files = new ArrayList<PcFile>();
		File[] fs = null;
		System.err.println("查看目录:"+path);
		File file = new File(path);
		if(file==null){
			return null;
		}else if(file.isDirectory()){
			fs = file.listFiles();
			if(fs==null){
				return null;
			}else{
				for(File f:fs){
					PcFile pf = new PcFile();
					pf.setName(f.getName());
					pf.setPath(f.getAbsolutePath());
					
					pf.setSys(false);
					if(f.isDirectory()){//设置是否为文件夹
						pf.setSize("");
						pf.setDir(true);
						dirs.add(pf);
					}else{
						pf.setSize((f.length() /1024)+ "KB");
						pf.setDir(false);
						files.add(pf);
					}
					
					
				}
			}
		}
		PcFile pf = new PcFile();
		pf.setName("\\上一页...");
		list.add(pf);
		/*将文件排序*/
		for(int i=0; i<dirs.size(); i++){
			list.add(dirs.get(i));
		}
		for(int i=0; i<files.size(); i++){
			list.add(files.get(i));
		}
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		
		return jsonArray;
	}
	
	/**
	 * 读取系统分区
	 * @return
	 */
	public static JSONArray systemRoots() {
		List<PcFile> list = new ArrayList<PcFile>();
		PcFile pf = null;
		File[] roots = File.listRoots();
		for (int i=0; i<roots.length; i++) {
			pf = new PcFile();
			pf.setName(roots[i].toString());
			pf.setSize(((roots[i].getTotalSpace() / 1024) / 1024) / 1024 + "GB");
			pf.setSys(true);
			list.add(pf);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		return jsonArray;
		
	}
	
	public static void main(String[] args) {
//		String str = listFile("/Volumes/音影/").toString();
		String str = systemRoots().toString();
		JSONArray jsonArr = JSONArray.fromObject(str);
		
		
		for (int i=0; i<jsonArr.size(); i++) {
			PcFile pf = (PcFile) JSONObject.toBean(jsonArr.getJSONObject(i), PcFile.class);
			System.out.println("name:" + pf.getName() + " 文件大小：" + pf.getSize());
		}
		
//		File[] roots = File.listRoots();
//		for (int i=0; i<roots.length; i++) {
//			System.out.println(roots[i]);
//			System.out.println("总共大小:" + ((roots[i].getTotalSpace() / 1024) / 1024) / 1024 + "GB");
//		}
	}
	
}

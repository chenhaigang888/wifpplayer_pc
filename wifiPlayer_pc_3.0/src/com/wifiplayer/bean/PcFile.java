package com.wifiplayer.bean;

import java.util.Date;
/**
 * 电脑上文件和目录
 * @author chenkaigang
 *
 */
public class PcFile {

	private String name;//文件（目录）的名称
	private String path;//当前文件（目录）的路径
	private boolean dir;//当前文件是否为文件夹
	private String size;//文件大小
	private Date createDate;//文件创建日期
	private boolean isSys;//是否为系统
	
	

	public boolean isSys() {
		return isSys;
	}



	public void setSys(boolean isSys) {
		this.isSys = isSys;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isDir() {
		return dir;
	}
	public void setDir(boolean dir) {
		this.dir = dir;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

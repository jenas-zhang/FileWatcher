package com.citi.interview.vo.impl;

import java.io.File;

import com.citi.interview.vo.StatisticsObjInterface;

/**
 * My customized file object.
 * 
 * @author jzhang
 */
public class MyFileObj implements StatisticsObjInterface {

	private File myFile;

	public MyFileObj() {
	}

	public MyFileObj(File file) {
		this.myFile = file;
	}

	public long getCampareValue() {
		return myFile != null ? myFile.length() : 0l;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof MyFileObj) {
			MyFileObj test = (MyFileObj) obj;
			ret = test.getMyFile().equals(this.getMyFile());
		} 
		
		return ret;
	}

	public int hashCode() {
		return myFile != null ? myFile.hashCode() : 0;
	}

	public String toString() {
		return this.myFile.getName() + " length: " + myFile.length();
	}
}

package com.citi.interview.service;

import java.io.File;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.citi.interview.vo.impl.MyFileObj;

/**
 * Singleton StatisticsManager
 * 
 * @author jzhang
 */
public class StatisticsManager {
	private static StatisticsManager _managerInstance = new StatisticsManager();

	private Set<MyFileObj> topTen = new TreeSet<MyFileObj>(
			new Comparator<MyFileObj>() {

				public int compare(MyFileObj arg0, MyFileObj arg1) {
					if (arg0.getCampareValue() - arg1.getCampareValue() > 0) {
						return -1;
					} else {
						return 1;
					}
				}

			});

	private StatisticsManager() {
		// private constructor for singleton.
	}

	public static StatisticsManager getInstance() {
		return _managerInstance;
	}

	public MyFileObj[] getTopFileArray() {
		return null;
	}
	
	public void addFile(File file) {
		topTen.add(new MyFileObj(file));
	}
	
	public void deleteFile(File file) {
		topTen.remove(new MyFileObj(file));
	}

	public MyFileObj[] getTopTen() {
		MyFileObj ret[] = new MyFileObj[10];
		int size = 10;

		if (topTen.size() < 10) {
			size = topTen.size();
		}

		System.arraycopy(topTen.toArray(new MyFileObj[11]), 0, ret, 0, size);

		return ret;
	}
}

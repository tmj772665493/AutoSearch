package org.jimmy.autosearch20180821.test;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap20180830 {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Vector<String>> map = new ConcurrentHashMap<String, Vector<String>>();
		Vector<String> list1 = new Vector<String>();
		list1.add("1");
		list1.add("2");
		map.put("0", list1);
		list1 = new Vector<String>();
		list1.add("3");
		list1.add("4");
		map.put("1", list1);
		
	}

}

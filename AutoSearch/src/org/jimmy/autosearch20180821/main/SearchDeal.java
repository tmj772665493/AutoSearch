package org.jimmy.autosearch20180821.main;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class SearchDeal {

	private String urlStr;
	private String text;
	private String keyWords;
	private int count;
	
	public static ConcurrentHashMap<String, Vector<String>> urlKeyWordsMap = new ConcurrentHashMap<String, Vector<String>>();
	public static HashMap<String, String> urlStrMap = new HashMap<String, String>();
	public static Vector<String> urlStrList = new Vector<String>(); 
	
	public SearchDeal(String urlStr, String text, String keyWords, int count){
		this.urlStr = urlStr;
		this.text = text;
		this.keyWords = keyWords;
		this.count = count;
	}
	
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}

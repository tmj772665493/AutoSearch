package org.jimmy.autosearch20180821.main;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class SearchThread implements Runnable {

	private String url;
	private String text;
	private String keyWords;
	private ArrayList<String> keyWordsList;
	
	public static ConcurrentHashMap<String, String> keyWordsMap = new ConcurrentHashMap<String, String>();
	
	public SearchThread(String url, String text, ArrayList<String> keyWordsList){
		this.url = url;
		this.text = text;
		this.keyWordsList = keyWordsList;
	}
	
	public SearchThread(String url, String text, String keyWords){
		this.url = url;
		this.text = text;
		this.keyWords = keyWords;
	}
	
	@Override
	public void run() {
		Vector<String> keyWordsResultList = new Vector<String>();
		if(keyWordsList != null && keyWordsList.size() > 0){
			for(String keyWordsSub : keyWordsList){
				if(keyWordsSub != null && text.indexOf(keyWordsSub) >= 0){
					keyWordsResultList.add(keyWordsSub);
				}
			}
			SearchDeal.urlKeyWordsMap.put(url, keyWordsResultList);
		}else{
			if(keyWords != null && text.indexOf(keyWords) >= 0){
				keyWordsResultList.add(keyWords);
				SearchDeal.urlKeyWordsMap.put(url, keyWordsResultList);
			}
		}
		
	}

}

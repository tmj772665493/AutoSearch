package org.jimmy.autosearch20180821.main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Start {

	public static void main(String[] args){
		try{
			Main.maxCount = 4;
			LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
			ThreadPoolExecutor executor = null;
	        //url匹配规则
//	        String regex = "http://[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+";
//	        String regex2 = "https://[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+";
	        FileInputStream fis = new FileInputStream("G:/Document/HongDaXingYe/AutoSearch/备选url.txt");
	        StringBuffer sb = new StringBuffer();
	        byte[] bytes = new byte[1024 * 100];
	        int len = bytes.length;
	        while((len = fis.read(bytes, 0, len)) != -1){
	        	sb.append(new String(bytes, 0, len, "utf-8"));
	        }
	        sb.delete(sb.length() - 1, sb.length());
	        fis.close();
	        String[] urlStrArr = sb.toString().replaceAll(" ", "").split(",");
	        ArrayList<String> urlStrList = new ArrayList<String>();
	        for(int i = 0; i < urlStrArr.length; i++){
	        	String currentUrlStr = urlStrArr[i].substring(1);
	        	if(currentUrlStr != null && !currentUrlStr.isEmpty()){
	        		urlStrList.add(currentUrlStr);
	        	}
	        }
	        fis = new FileInputStream("G:/Document/HongDaXingYe/AutoSearch/备选关键字.txt");
	        sb = new StringBuffer();
	        bytes = new byte[1024 * 100];
	        len = bytes.length;
	        while((len = fis.read(bytes, 0, len)) != -1){
	        	sb.append(new String(bytes, 0, len, "utf-8"));
	        }
	        sb.delete(sb.length() - 1, sb.length());
	        fis.close();
	        String[] keyWordsArr = sb.toString().replaceAll(" ", "").split(",");
	        ArrayList<String> keyWordsList = new ArrayList<String>();
	        for(int i = 0; i < keyWordsArr.length; i++){
	        	String currentKeyWords = keyWordsArr[i].substring(1);
	        	if(currentKeyWords != null && !currentKeyWords.isEmpty()){
	        		keyWordsList.add(currentKeyWords);
	        	}
	        }
	        Main.keyWordsList = keyWordsList;
	        int threadSize = urlStrList.size();
	        executor = new ThreadPoolExecutor(threadSize, 10000, 3600, TimeUnit.SECONDS, workQueue);
	        String id = null;
			for(int i = 0; i < urlStrList.size(); i++){
				id = i + "";
				String url = urlStrList.get(i);
				String path = "G:/Document/HongDaXingYe/AutoSearch/url" + id + ".txt";
				ThreadMain threadMain = new ThreadMain(id, url, path, threadSize);
				executor.execute(threadMain);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

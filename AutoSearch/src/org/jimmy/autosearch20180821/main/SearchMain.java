package org.jimmy.autosearch20180821.main;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SearchMain {

	private String urlStr;
	private String path;
	private String text;
	private int threadSize;
	private String keyWords;
	private ArrayList<String> keyWordsList;
	private BufferedReader br;
	
	public static HashMap<String, String> urlStrMap = new HashMap<String, String>();
	public static final String urlRegex = "https?://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)/(((\\w|-)+\\.(\\w|-)+)|(\\w|-)+(\\?\\w+=(\\w|-|%|[\u4e00-\u9fa5])+(\\&\\w+=(\\w|-|%|[\u4e00-\u9fa5])+)*)?)";
	public static final String urlRegex2 = "https?://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)"; 
	public static final int SIZE1 = 10;
	public static final int SIZE2 = 100;
	public static final int SIZE3 = 1000;
	public static final int SIZE4 = 1024;
	
	public SearchMain(String urlStr, String path, String text, int threadSize, String keyWords, BufferedReader br){
		this.urlStr = urlStr;
		this.path = path;
		this.text = text;
		this.threadSize = threadSize;
		this.keyWords = keyWords;
	}
	
	public SearchMain(String urlStr, String path, String text, int threadSize, ArrayList<String> keyWordsList, BufferedReader br){
		this.urlStr = urlStr;
		this.path = path;
		this.text = text;
		this.threadSize = threadSize;
		this.keyWordsList = keyWordsList;
	}
	
	public static void main(String[] args){
		int id = 0;
		String urlStr = "http://www.google.com";
		String path = "G:/Document/HongDaXingYe/AutoSearch/url" + id + ".txt";
		String text = "";
		for(int i = 0; i < 102; i++){
			text += 0;
		}
		int threadSize = 10;
		ArrayList<String> keyWordsList = new ArrayList<String>();
		keyWordsList.add("0");
		keyWordsList.add("0");
		keyWordsList.add("00");
		SearchMain test = new SearchMain(urlStr, path, text, threadSize, keyWordsList, null);
		test.subsectionSearch();
	}
	
	public synchronized Vector<String> subsectionSearchUrl(int limit){
		Vector<String> urlList = new Vector<String>();
		try {
			LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
			int size = text.length();
			threadSize = size % limit == 0 ? size / limit : size / limit + 1;
			ThreadPoolExecutor executor = new ThreadPoolExecutor(threadSize, 10000, 3600, TimeUnit.SECONDS, workQueue);
			for(int i = 0; i < threadSize; i++){
				int beginIndex = i * limit;
				int endIndex = beginIndex + limit;
				String subText = null;
				if(endIndex >= size){
					subText = text.substring(beginIndex);
				}else{
					subText = text.substring(beginIndex, endIndex);
				}				
				SearchUrlThread searchUrlThread = new SearchUrlThread(subText, urlRegex, urlRegex2);
				executor.execute(searchUrlThread);
			}
			while(true){
				if(executor.getCompletedTaskCount() >= threadSize){
					executor.shutdown();
					executor.shutdownNow();
					break;
				}
				Thread.sleep(1000);
			}
			if(executor.isTerminated()){
				System.out.println("搜索完成!");
				/*for(String url : SearchDeal.urlStrList){
					System.out.println("url:" + url);
				}*/
				urlList = SearchDeal.urlStrList;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return urlList;
	}
	
	public synchronized StringBuffer subsectionSearch(){
		StringBuffer sbNewText = new StringBuffer();
		try {
			LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
			ThreadPoolExecutor executor = new ThreadPoolExecutor(threadSize, 10000, 3600, TimeUnit.SECONDS, workQueue);
			int size = text.length();
			int limit = size % threadSize == 0 ? size / threadSize : size / threadSize + 1;
			for(int i = 0; i < threadSize; i++){
				int beginIndex = i * limit;
				int endIndex = beginIndex + limit;
				String subText = null;
				if(endIndex >= size){
					subText = text.substring(beginIndex);
				}else{
					subText = text.substring(beginIndex, endIndex);
				}
				SearchThread sh = null;
				if(keyWordsList != null && keyWordsList.size() > 0){
					sh = new SearchThread(urlStr, subText, keyWordsList);
				}else{
					sh = new SearchThread(urlStr, subText, keyWords);
				}
				executor.execute(sh);
			}
			while(true){
				if(executor.getCompletedTaskCount() >= threadSize){
					executor.shutdown();
					executor.shutdownNow();
					break;
				}
				Thread.sleep(1000 * 3);
			}
			if(executor.isTerminated()){
				System.out.println("搜索完成!");
				StringBuffer sb = new StringBuffer();
				Vector<String> keyWordsList = SearchDeal.urlKeyWordsMap.get(urlStr);
				for(String keyWordsSub : keyWordsList){
					sb.append(keyWordsSub);
					sb.append(",");
				}
				if(sb.length() > 0){
					sb.delete(sb.length() - 1, sb.length());
				}
				sbNewText.append("网址:");
				sbNewText.append(urlStr);
				sbNewText.append(System.lineSeparator());
				sbNewText.append("关键字:");
				if(sb != null && sb.length() > 0){
					sbNewText.append(sb);
					sbNewText.append(System.lineSeparator());
				}
				FileOutputStream fos = new FileOutputStream(path);
            	fos.write(sbNewText.toString().getBytes());
    			fos.flush();
    			fos.close();
	            if(br != null){
	            	br.close();
	            }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(sbNewText.toString());
		return sbNewText;
	}
	
}

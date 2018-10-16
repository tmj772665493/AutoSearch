package org.jimmy.autosearch20180821.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jimmy.autosearch20180821.util.HtmlTextUtil;

public class TestThreadMain {

	private int id;
	private int threadSize;
	private String path;
	public int count = 0;
	public int maxCount = 5;
	public static HashMap<String, String> urlStrMap = new HashMap<String, String>();
	public static HashMap<URL, URL> urlMap = new HashMap<URL, URL>();
	public static URLConnection urlConn = null;
	public static HttpURLConnection httpUrlConn = null;
	
	public TestThreadMain(int id, int threadSize) {
		this.id = id;
		this.threadSize = threadSize;
	}
	
	public void subsection(){
		try{
			FileInputStream fis = new FileInputStream("G:/Document/HongDaXingYe/AutoSearch/备选url.txt");
	        StringBuffer sb = new StringBuffer();
	        byte[] bytes = new byte[1024];
	        int len = 0;
	        while((len = fis.read(bytes)) != -1){
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
	        bytes = new byte[1024];
	        len = 0;
	        while((len = fis.read(bytes)) != -1){
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
			String path = "G:/Document/HongDaXingYe/AutoSearch/url" + id + ".txt";
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getUrl(String urlStr){
		System.out.println("count:" + count);
		ArrayList<URL> urls = new ArrayList<URL>();
		ArrayList<String> urlStrList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			int timeout = 60 * 60;
			URL url = new URL(urlStr);
            urlConn = url.openConnection();
            urlConn.setReadTimeout(timeout);
            httpUrlConn = (HttpURLConnection) urlConn;
            if(httpUrlConn.getResponseCode() != 200){
            	System.out.println("连接失败!");
            	count++;
            	return new ArrayList<String>();
            }
            br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String text = null;
            StringBuffer sb = new StringBuffer();
            char[] chars = new char[1024 * 100];
            int len = 0;
            while ((len = br.read(chars, 0, len)) != -1) {
            	text = new String(chars);
            	text = new String(text.getBytes(), "utf-8");
            	sb.append(text);
//            	System.out.println(text);
            }
            System.out.println(sb);
//            ArrayList<String> htmlTextList = HtmlTextUtil.getHtmlTextList(sb.toString(), htmlReg);
//            text = HtmlTextUtil.getText(sb.toString(), htmlTextList);
            StringBuffer sb2 = new StringBuffer();
            sb2.append(text);
            String urlRegex = "http://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)/(((\\w|-)+\\.(\\w|-)+)|(\\w|-)+(\\?\\w+=(\\w|-|%|[\u4e00-\u9fa5])+(\\&\\w+=(\\w|-|%|[\u4e00-\u9fa5])+)*)?)";
            String urlRegex2 = "http://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)";
            Pattern urlPattern = Pattern.compile(urlRegex);
            Pattern urlPattern2 = Pattern.compile(urlRegex2);
           
            Matcher urlMatcher = urlPattern.matcher(sb2.toString());
            Matcher urlMatcher2 = urlPattern2.matcher(sb2.toString());
            boolean flag = false;
            String urlMatcherGroup = null;
            while (urlMatcher.find()) {
            	flag = true;
            	urlMatcherGroup = urlMatcher.group();
            	if(urlStrMap.get(urlMatcherGroup) != null){
            		System.out.println("url1:" + urlMatcherGroup);
            		urlStrList.add(urlMatcherGroup);
            		url = new URL(urlMatcherGroup);
            		urls.add(url);
            		urlStrMap.put(urlMatcherGroup, urlMatcherGroup);
            		urlMap.put(url, url);
            	}
            }
            if(!flag){
            	while (urlMatcher2.find()) {
            		flag = true;
                	urlMatcherGroup = urlMatcher2.group();
                	if(urlStrMap.get(urlMatcherGroup) != null){
                		System.out.println("url2:" + urlMatcherGroup);
                		urlStrList.add(urlMatcherGroup);
                		url = new URL(urlMatcherGroup);
                		urls.add(url);
                		urlStrMap.put(urlMatcherGroup, urlMatcherGroup);
                		urlMap.put(url, url);
                	}
                }
            }
            FileOutputStream fos = new FileOutputStream(path);
            int count = 0;
            String currentKeyWords = "";
            /*for(String keyWords : keyWordsList){
            	if(sb2.toString().indexOf(keyWords) >= 0){
            		currentKeyWords += keyWords + ",";
            		count++;
            	}
            }*/
            currentKeyWords = currentKeyWords.substring(0, currentKeyWords.length() - 1);
            if(count > 0){
            	fos.write(("网址:" + urlStr + System.lineSeparator()).getBytes());
            	fos.write(("关键字:" + currentKeyWords + System.lineSeparator()).getBytes());
    			fos.flush(); 
            }
            fos.close();
            br.close();
            System.out.println("爬取成功!");
            count++;
        }catch(Exception e){
        	System.out.println("连接失败!");
        	count++;
        	return new ArrayList<String>();
        }
		return urlStrList;
	}
	
	public void getUrl(ArrayList<String> urlStrList){
		if(count < maxCount){
			for(String urlStr : urlStrList){
				if(urlStrMap.get(urlStr) == null){
					ArrayList<String> currentUrlStrList = getUrl(urlStr);
					getUrl(currentUrlStrList);
				}else{
					continue;
				}
			}
		}else{
			System.out.println("爬取完成!");
//			System.exit(0);
		}
	}
	
}

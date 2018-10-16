package org.jimmy.autosearch20180821.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Main{

	private String urlStr;
	private String path;
	private int threadSize;
	
	public AtomicInteger count = new AtomicInteger(0);
	public static ArrayList<String> keyWordsList;
	public static HashMap<String, String> urlStrMap = new HashMap<String, String>();
	public static HashMap<URL, URL> urlMap = new HashMap<URL, URL>();
	public static URLConnection urlConn = null;
	public static HttpURLConnection httpUrlConn = null;
	public static int maxCount = 0;
	public static Vector<String> usedUrlList = new Vector<String>(); 
	
	public Main(String urlStr, String path, int threadSize){
		this.urlStr = urlStr;
		this.path = path;
		this.threadSize = threadSize;
	}
	
	public synchronized void search() {
		try {
			Vector<String> urlList = getUrl(urlStr);
	        getUrl(urlList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public synchronized Vector<String> getUrl(String urlStr){
		System.out.println("count:" + count);
		ArrayList<URL> urls = new ArrayList<URL>();
		Vector<String> urlStrList = new Vector<String>();
		BufferedReader br = null;
		StringBuffer sbNewText = null;
		try {
			int timeout = 60 * 60;
			URL url = new URL(urlStr);
            urlConn = url.openConnection();
            urlConn.setReadTimeout(timeout);
            httpUrlConn = (HttpURLConnection) urlConn;
            if(httpUrlConn.getResponseCode() != 200){
            	System.out.println("连接失败!");
            	count.incrementAndGet();
            	return new Vector<String>();
            }
            br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String text = null;
            StringBuffer sb = new StringBuffer();
            char[] chars = new char[1024 * 100];
			int len = chars.length;
			while((len = br.read(chars, 0, chars.length)) != -1){
				text = new String(chars);
            	text = new String(text.getBytes(), "utf-8");
            	sb.append(text);
//            	System.out.println(text);
			}
			SearchMain searchMain = new SearchMain(urlStr, path, sb.toString(), threadSize, keyWordsList, br);
            sbNewText = searchMain.subsectionSearch();
            Vector<String> searchedUrlList = searchMain.subsectionSearchUrl(SearchMain.SIZE3);
            while(true){
            	if(searchedUrlList != null && searchedUrlList.size() > 0){
            		System.out.println("查询到的url数量:" + searchedUrlList.size());
            		urlStrList.addAll(searchedUrlList);
            		break;
            	}
            	Thread.sleep(1000);
            }
//            urlStrList = searchMain.subsectionSearchUrl(SearchMain.SIZE3);
//            String htmlReg = "<[a-zA-Z0-9]+>|<\\s*/\\s*[a-zA-Z0-9]+>|<[a-zA-Z0-9]+\\s*/\\s*>";
            /*String urlRegex = "http://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)/(((\\w|-)+\\.(\\w|-)+)|(\\w|-)+(\\?\\w+=(\\w|-|%|[\u4e00-\u9fa5])+(\\&\\w+=(\\w|-|%|[\u4e00-\u9fa5])+)*)?)";
            String urlRegex2 = "http://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)";
            Pattern urlPattern = Pattern.compile(urlRegex);
            Pattern urlPattern2 = Pattern.compile(urlRegex2);
            Matcher urlMatcher = urlPattern.matcher(sb.toString());
            Matcher urlMatcher2 = urlPattern2.matcher(sb.toString());
            boolean flag = false;
            String urlMatcherGroup = null;
            while (urlMatcher.find()) {
            	flag = true;
            	urlMatcherGroup = urlMatcher.group();
            	if(urlStrMap.get(urlMatcherGroup) == null){
//            		System.out.println("url1:" + urlMatcherGroup);
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
                	if(urlStrMap.get(urlMatcherGroup) == null){
//                		System.out.println("url2:" + urlMatcherGroup);
                		urlStrList.add(urlMatcherGroup);
                		url = new URL(urlMatcherGroup);
                		urls.add(url);
                		urlStrMap.put(urlMatcherGroup, urlMatcherGroup);
                		urlMap.put(url, url);
                	}
                }
            }*/
//            path = "G:/Document/HongDaXingYe/AutoSearch/url" + count + ".txt";
            FileInputStream fis = new FileInputStream(path);
            byte[] bytes = new byte[1024 * 1024];
            len = bytes.length;
            StringBuffer sbOldText = new StringBuffer();
            while((len = fis.read(bytes, 0, len)) != -1){
            	text = new String(bytes, 0, len, "utf-8");
            	sbOldText.append(text);
            }
            sbOldText.append(System.lineSeparator());
            FileOutputStream fos = new FileOutputStream(path);
            StringBuffer dealedSb = new StringBuffer(); 
            if(sbOldText != null && sbOldText.length() > 0){
            	dealedSb.append(sbOldText);
            }
            if(sbNewText != null && sbNewText.length() > 0){
            	dealedSb.append(sbNewText);
            }
        	fos.write(dealedSb.toString().getBytes());
			fos.flush();
			fos.close();
			fis.close();
			br.close();
            System.out.println("爬取成功!");
            count.getAndIncrement();
        }catch(Exception e){
        	System.out.println(e.getMessage());
        	count.getAndIncrement();
        	return new Vector<String>();
        }
		return urlStrList;
	}
	
	public synchronized void getUrl(Vector<String> urlStrList){
		System.out.println("count2:" + count);
		if(count.intValue() < maxCount){
			for(String urlStr : urlStrList){
				System.out.println("当前url:" + urlStr);
				if(urlStrMap.get(urlStr) == null){
					urlStrMap.put(urlStr, urlStr);
					Vector<String> currentUrlStrList = getUrl(urlStr);
					getUrl(currentUrlStrList);
				}else{
					continue;
				}
			}
		}else{
			System.out.println("爬取完成!");
			System.exit(0);
		}
	}
	
}

package org.jimmy.autosearch20180821.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchUrlThread implements Runnable {

	private String text;
	private String urlRegex;
	private String urlRegex2;
	
	public SearchUrlThread(String text, String urlRegex, String urlRegex2) {
		this.text = text;
		this.urlRegex = urlRegex;
		this.urlRegex2 = urlRegex2;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrlRegex() {
		return urlRegex;
	}
	public void setUrlRegex(String urlRegex) {
		this.urlRegex = urlRegex;
	}
	public String getUrlRegex2() {
		return urlRegex2;
	}
	public void setUrlRegex2(String urlRegex2) {
		this.urlRegex2 = urlRegex2;
	} 
	
	@Override
	public void run() {
		Pattern urlPattern = Pattern.compile(urlRegex);
        Pattern urlPattern2 = Pattern.compile(urlRegex2);
        Matcher urlMatcher = urlPattern.matcher(text);
        Matcher urlMatcher2 = urlPattern2.matcher(text);
        boolean flag = false;
        String urlMatcherGroup = null;
		while (urlMatcher.find()) {
        	flag = true;
        	urlMatcherGroup = urlMatcher.group();
        	if(SearchDeal.urlStrMap.get(urlMatcherGroup) == null){
//        		System.out.println("url1:" + urlMatcherGroup);
        		SearchDeal.urlStrList.add(urlMatcherGroup);
        		SearchDeal.urlStrMap.put(urlMatcherGroup, urlMatcherGroup);
        	}
        }
        if(!flag){
        	while (urlMatcher2.find()) {
        		flag = true;
            	urlMatcherGroup = urlMatcher2.group();
            	if(SearchDeal.urlStrMap.get(urlMatcherGroup) == null){
//            		System.out.println("url2:" + urlMatcherGroup);
            		SearchDeal.urlStrList.add(urlMatcherGroup);
            		SearchDeal.urlStrMap.put(urlMatcherGroup, urlMatcherGroup);
            	}
            }
        }
	}

}

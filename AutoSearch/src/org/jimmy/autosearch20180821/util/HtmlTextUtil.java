package org.jimmy.autosearch20180821.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

public class HtmlTextUtil {
	
	public static String getText(String htmlText, ArrayList<String> htmlTextList){
		String text = htmlText;
		for(String htmlTextSub : htmlTextList){
			text = text.replaceAll(htmlTextSub, "");
		}
		return text;
	}
	
	public static ArrayList<String> getHtmlTextList(String htmlText, String reg){
		ArrayList<String> htmlTextList = new ArrayList<String>();
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(htmlText);
		while(matcher.find()){
			int start = matcher.start();
			int end = matcher.end();
			htmlTextList.add(htmlText.substring(start, end));
//			System.out.println("start:" + start);
//			System.out.println("end:" + end);
//			System.out.println(htmlText.substring(start, end));
//			System.out.println(matcher.group());
		}
		return htmlTextList;
	}
	
}

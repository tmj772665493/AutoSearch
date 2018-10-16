package org.jimmy.autosearch20180821.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jimmy.autosearch20180821.pojo.HtmlTextPojo;
import org.jimmy.autosearch20180821.util.HtmlTextUtil;

public class Test {

	public static void main(String[] args) {
		/*StringBuffer sb = new StringBuffer(74444450);
		for(int i = 0; i < 7444_4450; i++){
			sb.append(1);
		}*/
//		System.out.println(sb.length());
//		System.out.println("StringBuffer的最大长度是:" + 7444_4450);
//		String text = "http://www.baidu.com";
		String text2 = "/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=java%20Pattern怎么贪婪匹配&oq=java%25E6%25AD%25A3%25E5%2588%2599%25E8%25A1%25A8%25E8%25BE%25BE%25E5%25BC%258F%25E5%2585%2588%25E5%258C%25B9%25E9%2585%258D%25E5%25A4%25A7%25E8%258C%2583%25E5%259B%25B4%25E7%259A%2584&rsv_pq=c23bb6bb00000774&rsv_t=5fe4DhEMtg3UKyxMXPyXgu0hwKTpFSXnEymza7dBk3qfonnRFX1br%2BBlWTI&rqlang=cn&rsv_enter=1&inputT=7604&rsv_sug3=94&rsv_sug1=43&rsv_sug7=100&rsv_sug2=0&rsv_sug4=7605&rsv_sug=2";
		String text3 = "/s?ie=utf-8&f=8&rsv_bp=1";
		String text4 = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=java%20Pattern%E6%80%8E%E4%B9%88%E8%B4%AA%E5%A9%AA%E5%8C%B9%E9%85%8D&oq=java%25E6%25AD%25A3%25E5%2588%2599%25E8%25A1%25A8%25E8%25BE%25BE%25E5%25BC%258F%25E5%2585%2588%25E5%258C%25B9%25E9%2585%258D%25E5%25A4%25A7%25E8%258C%2583%25E5%259B%25B4%25E7%259A%2584&rsv_pq=c23bb6bb00000774&rsv_t=5fe4DhEMtg3UKyxMXPyXgu0hwKTpFSXnEymza7dBk3qfonnRFX1br%2BBlWTI&rqlang=cn&rsv_enter=1&inputT=7604&rsv_sug3=94&rsv_sug1=43&rsv_sug7=100&rsv_sug2=0&rsv_sug4=7605&rsv_sug=2";
//		String text5 = text + text2;
		String text6 = "http://www.imslr.com/thread-52754-1-1.html";
		String text7 = "..</a></li>         </ul>         </div></td><td valign='top' class='category_l3'><div class='hottiebox'><h4><span class='tit_hottie'></span>热帖</h4>        <ul class='category_newlist'>                	<li><a href='http://www.imslr.com/thread-33435-1-1.html' tip='标题: <strong>《魔法的基础  原理篇》</strong><br/>作者: ??????? (2016-1-31)<br/>查看/回复: 7443/362' onmouseover='showTip(this)'>《魔法的基础  原理篇》 ...</a></li>        	<li><a href='http://www.imslr.com/thread-12737-1-1.html' tip='标题: <strong>《占星术宫位》</strong><br/>作者: qingfeilantian (2010-1-4)<br/>查看/回复: 7645/96' onmouseover='showTip(this)'>《占星术宫位》</a></li>        	<li><a href='http://www.imslr.com/thread-5129-1-1.html' tip='标题: <strong>《变态心理学 附带光盘》</strong><br/>作者: 安魂 (2009-1-11)<br/>查看/回复: 1126/7' onmouseover='showTip(this)'>《变态心理学 附带光盘》 ...</a></li>        	<li><a href='http://www.imslr.com/thread-26578-1-1.html'  style='color: #2897C5' tip='标题: <strong>《占星全书》</strong><br/>作者: 流苏树 (2013-4-21)<br/>查看/回复: 4629/108' onmouseover='showTip(this)'>《占星全书》</a></li>        	<li><a href='http://www.imslr.com/thread-52762-1-1.html' tip='标题: <strong>《覺悟勇士：香巴拉的智慧傳承》</strong><br/>作者: epeeian (<span title='2018-8-23'>昨天&nbsp;18:12</span>)<br/>查看/回复: 23/2' onmouseover='showTip(this)'>《覺悟勇士：香巴拉的智慧傳承》 ...</a></li>        	<li><a href='http://www.imslr.com/thread-52761-1-1.html' tip='标题: <strong>《塔罗冥想-基督信仰內在隱修之旅》中英文</strong><br/>作者: epeeian (<span title='2018-8-23'>昨天&nbsp;10:17</span>)<br/>查看/回复: 31/6' onmouseover='showTip(this)'>《塔罗冥想-基督信仰內在隱修之 ...</a></li>        	<li><a href='http://www.imslr.com/thread-13890-1-1.html'  style='font-weight: bold;color: #8F2A90' tip='标题: <strong>镜像与邪眼</strong><br/>作者: 北夜玄武 (2010-4-6)<br/>查看/回复: 31348/885' onmouseover='showTip(this)'>镜像与邪眼</a></li>        	<li><a href='http://www.imslr.com/thread-33787-1-1.html' tip='标题: <strong>《卡巴拉——道路（历史、现在、未来）》</strong><br/>作者: 999 (2016-6-6)<br/>查看/回复: 2465/83' onmouseover='showTip(this)'>《卡巴拉——道路（历史、现在、 ...</a></li>        	<li><a href='http://www.imslr.com/thread-30794-1-1.html' tip='标题: <strong>《当神秘学来敲门（完整版）》</strong><br/>作者: 流苏树 (2014-3-23)<br/>查看/回复: 25024/1032' onmouseover='showTip(this)'>《当神秘学来敲门（完整版）》 ...</a></li>        	<li><a href='http://www.imslr.com/thread-52764-1-1.html' tip='标题: <strong>跪求能降低存在感的方法</strong><br/>作者: 公子玄卿 (<span title='2018-8-23'>昨天&nbsp;19:11</span>)<br/>查看/回复: 37/6' onmouseover='showTip(this)'>跪求能降低存在感的方法 ...</a></li>         </ul>         </div></td></table></div></div></div><!-- index four grid end --><div class='fl bm'><div class='bm bmw  flg cl' style='background: none; margin-bottom: 20px;'><div class='bm_h cl'><h2 style='font-weight: normal; padding: 0 10px; font-size: 16px; color: #b59363;'>圖書館</h2></div><div id='category_23' class='bm_c' style=''><table cess='bm_c' style=''><table cellspacing='0' cellpadding='0' class='fl_tb'><tr><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-15-1.html'><img src='static/image/common/forum_new.gif' alt='占星基礎' /></a></div><dl><dt><a href='http://www.imslr.com/forum-15-1.html'>占星基礎</a></dt><dd><em>主题: 411</em>, <em>帖数: 4004</em></dd></dl></td><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-209-1.html'><img src='static/image/common/forum_new.gif' alt='現代占星' /></a></div><dl><dt><a href='http://www.imslr.com/forum-209-1.html'>現代占星</a></dt><dd><em>主题: 205</em>, <em>帖数: 2092</em></dd></dl></td><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-210-1.html'><img src='static/image/common/forum_new.gif' alt='古典占星' /></a></div><dl><dt><a href='http://www.imslr.com/forum-210-1.html'>古典占星</a></dt><dd><em>主题: 89</em>, <em>帖数: 563</em></dd></dl></td></tr><tr class='fl_row'><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-124-1.html'><img src='static/image/common/forum.gif' alt='解盤實習' /></a></div><dl><dt><a href='http://www.imslr.com/forum-124-1.html'>解盤實習</a></dt><dd><em>主题: 401</em>, <em>帖数: 2723</em></dd></dl></td><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-123-1.html'><img src='static/image/common/forum_new.gif' alt='實例收集' /></a></div><dl><dt><a href='http://www.imslr.com/forum-123-1.html'>實例收集</a></dt><dd><em>主题: 43</em>, <em>帖数: 833</em></dd></dl></td><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-162-1.html'><img src='static/image/common/forum_new.gif' alt='12星座專區' /></a></div><dl><dt><a href='http://www.imslr.com/forum-162-1.html'>12星座專區</a></dt><dd><em>主题: 305</em>, <em>帖数: 3050</em></dd></dl></td></tr></table></div></div><div class='bm bmw  flg cl' style='background: none; margin-bottom: 20px;'><div class='bm_h cl'><h2 style='font-weight: normal; padding: 0 10px; font-size: 16px; color: #b59363;'>塔羅學院</h2></div><div id='category_92' class='bm_c' style=''><table cellspacing='0' cellpadding='0' class='fl_tb'><tr><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-16-1.html'><img src='static/image/common/forum_new.gif' alt='塔羅基礎' /></a></div><dl><dt><a href='http://www.imslr.com/forum-16-1.html'>塔羅基礎</a></dt><dd><em>主题: 327</em>, <em>帖数: 4561</em></dd></dl></td><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-220-1.html'><img src='static/image/common/forum_new.gif' alt='塔羅符號學' /></a></div><dl><dt><a href='http://www.imslr.com/forum-220-1.html'>塔羅符號學</a></dt><dd><em>主题: 64</em>, <em>帖数: 1685</em></dd></dl></td><td class='fl_g' width='32.9%'><div class='fl_icn_g'><a href='http://www.imslr.com/forum-129-1.html'><img src='static/image/common/forum_new.gif' alt='解牌練習' /></a></div><dl><dt><a href='http://www.imslr.com/forum-129-1.html'>解牌練習</a></dt><dd><em>主题: 146</em>, <em>帖数: 1008</em></dd></dl></td></tr><tr class='fl_row'><td c";
		String urlRegex4 = "(/[a-zA-Z0-9]+\\.[a-zA-Z0-9]+){1}";
		String urlRegex3 = "/(([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)|[a-zA-Z0-9]+(\\?\\w+=(\\w|-|%|[\u4e00-\u9fa5])+(\\&\\w+=(\\w|-|%|[\u4e00-\u9fa5])+)*)?)";
		String urlRegex2 = "http://[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+(/[a-zA-Z0-9]+(/([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)|/[a-zA-Z0-9]*)|(/([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)|/[a-zA-Z0-9]+)|/?)";
		String urlRegex = "http://[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+(/[a-zA-Z0-9]+(/([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)|/[a-zA-Z0-9]*)|(/([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)|/[a-zA-Z0-9]+)|/?)/(([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)|[a-zA-Z0-9]+(\\?\\w+=(\\w|-|%|[\u4e00-\u9fa5])+(\\&\\w+=(\\w|-|%|[\u4e00-\u9fa5])+)*)?)";
		String urlRegex5 = "http://(\\w|-)+(\\.(\\w|-)+)+(/(\\w|-)+(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)*)|(/((\\w|-)+\\.(\\w|-)+)|/(\\w|-)+)|/?)";
		String text8 = ".?";
		String text9 = "\"";
		String regex = ".*";
		String regex2 = ".*學.*";
		/*System.out.println(Pattern.compile(regex2).matcher(text7).matches());
		System.out.println(Pattern.compile(regex).matcher(text8).matches());
		System.out.println(Pattern.compile(regex).matcher(text9).matches());
		Pattern pattern = Pattern.compile(urlRegex5);
		Matcher matcher = pattern.matcher(text6);
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		matcher = pattern.matcher(text6);
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		pattern = Pattern.compile(urlRegex2);
		matcher = pattern.matcher(text6);
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		matcher = pattern.matcher(text6);
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		System.out.println(Pattern.compile(".*gdg.*").matcher("ygfgdggh55").matches());
		StringBuffer sb = new StringBuffer();
		sb.append("aba");
		sb.delete(sb.length() - 1, sb.length());
		System.out.println(sb.toString());*/
		
		String htmlReg = "<[a-zA-Z0-9]+>|<\\s*/\\s*[a-zA-Z0-9]+>|<[a-zA-Z0-9]+\\s*/\\s*>";
//		String htmlReg2 = "<\\s*/\\s*[a-zA-Z0-9]+>";
//		String htmlReg3 = "<[a-zA-Z0-9]+\\s*/\\s*>";
		String htmlText = "<body><h1>中文<g>gj</h1>gdg5dg65汉字gdf<test>ggghhhl</test></body>";
		ArrayList<String> htmlTextList = HtmlTextUtil.getHtmlTextList(htmlText, htmlReg);
		String text = HtmlTextUtil.getText(htmlText, htmlTextList);
//		System.out.println("text:" + text);
//		System.out.println("---------------------------------------");
		htmlText = "中文<g>gj</h1>gdg5dg65汉字gdf<test>ggghhhl</test></body><img  / >";
		htmlTextList = HtmlTextUtil.getHtmlTextList(htmlText, htmlReg);
		text = HtmlTextUtil.getText(htmlText, htmlTextList);
//		System.out.println("text:" + text);
		HashMap<String, String> testMap = new HashMap<String, String>();
		Vector<String> strList = new Vector<String>();
		
		try{
			/*Thread testThread1 = new Thread(new TestThread(1));
			testThread1.start();
			System.out.println(Thread.currentThread().getId());
			Thread testThread2 = new Thread(new TestThread(2));
			testThread2.start();
			System.out.println(Thread.currentThread().getId());*/
			
			/*String testPath = "G:/Document/HongDaXingYe/AutoSearch/备选url.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(testPath)));
			char[] chars = new char[1024 * 100];
			int len = chars.length;
			while((len = br.read(chars, 0, chars.length)) != -1){
				System.out.println(new String(chars));
			}
			br.close();*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}

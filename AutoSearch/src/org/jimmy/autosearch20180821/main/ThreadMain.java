package org.jimmy.autosearch20180821.main;

public class ThreadMain implements Runnable{

	private String id;
	private String urlStr;
	private String path;
	private int threadSize;
	
	public ThreadMain(String id, String urlStr, String path, int threadSize){
		this.id = id;
		this.urlStr = urlStr;
		this.path = path;
		this.threadSize = threadSize;
	}
	
	@Override
    public void run() {
		System.out.println("任务" + id + "开始执行!");
		Main main = new Main(urlStr, path, threadSize);
		main.search();
    }
	
}

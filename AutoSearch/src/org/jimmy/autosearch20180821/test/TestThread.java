package org.jimmy.autosearch20180821.test;

public class TestThread implements Runnable {

	private int id;
	
	public TestThread(int id){
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("线程" + id + "启动!");
		TestThreadMain testThreadMain = new TestThreadMain(id, 2);
		
	}
	
}

package org.jimmy.autosearch20180821.main;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TextPool {

	private ConcurrentHashMap<String, FutureTask<String>> textMap = new ConcurrentHashMap<String, FutureTask<String>>();  
	
	public String getText(String text) throws InterruptedException, ExecutionException {
		FutureTask<String> textTask = textMap.get(text);
		if(textTask != null){
			return textTask.get();
		}else{
			Callable<String> callable = new Callable<String>() {

				@Override
				public String call() throws Exception {
					return createText();
				}
				
			};
			FutureTask<String> newTask = new FutureTask<String>(callable);
			textTask = textMap.putIfAbsent(text, newTask);
			if(textTask == null){
				textTask = newTask;
				textTask.run();
			}
			return textTask.get();
		}
	}
	
	public String createText() {
        return new String();
    }
	
}

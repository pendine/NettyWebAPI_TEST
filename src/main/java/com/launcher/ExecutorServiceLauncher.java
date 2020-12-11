package com.launcher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class ExecutorServiceLauncher {

	private ExecutorService executorService;
	private List<Callable<Object>> launchTasks; // 2018.11.16 dsnoh : 가공


	public void Launch() throws InterruptedException {
		if (this.executorService != null && launchTasks != null) {
			this.executorService.invokeAll((List<? extends Callable<Object>>)launchTasks); // 2018.11.16 dsnoh : 인천가공
		}
	}
	
	public ExecutorService getExecutorService() {
		return executorService;
	}
	
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	public List<Callable<Object>> getLaunchTasks() { // 2018.11.16 dsnoh : 인천가공
		return launchTasks;
	}
	
	public void setLaunchTasks(List<Callable<Object>> launchTasks) { // 2018.11.16 dsnoh : 인천가공
		this.launchTasks = launchTasks;
	}
	
	public void setLaunchTasks(Callable<Object> launchTasks) { // 2018.11.16 dsnoh : 인천가공
		this.launchTasks.add(launchTasks);
	}
	
	public void setLaunchTasks(Object launchTasks) { // 2018.11.16 dsnoh : 인천가공
		this.launchTasks.add((Callable<Object>) launchTasks);
	}
	
}
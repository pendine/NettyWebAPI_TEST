package com.Main;

import java.io.File;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.launcher.ExecutorServiceLauncher;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;

public class Main {
	private static AbstractApplicationContext applicationContext;
	
	public static void main(String[] args) {
		
		System.out.println("Logging System Loading.....");
		
		LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();

		JoranConfigurator joranConfigurator = new JoranConfigurator();
		joranConfigurator.setContext(loggerContext);
		loggerContext.reset();
		
		String loggingConfigPath = String.format("%s"+File.separator+"conf"+File.separator+"logback.xml", System.getProperty("user.dir")); // all-round

		try {
			joranConfigurator.doConfigure(loggingConfigPath);
    	} catch (Exception e) {
//    		System.out.println(e.getMessage());
    	}
    	
    	StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    	
//    	System.setProperty("spring.profiles.active", "default");  // default : 오라클 ,   tibero : 티베로	
    	System.setProperty("spring.profiles.active", "tibero");  // default : 오라클 ,   tibero : 티베로	

    	applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/**/context-*.xml"); // 윈도우 - 이부분은 상관 없었음.
		applicationContext.registerShutdownHook();
		
		try {
			applicationContext.getBean(ExecutorServiceLauncher.class).Launch();
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

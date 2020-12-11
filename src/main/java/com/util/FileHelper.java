package com.util;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public abstract class FileHelper {
	public static String extractFilePath(String fullPath) {
		String resultPath = fullPath;

		int lastPathIndex = (resultPath.lastIndexOf("/") != -1) ? resultPath.lastIndexOf("/") : resultPath.lastIndexOf("\\");

		return (lastPathIndex != -1) ? resultPath.substring(0, lastPathIndex + 1) : resultPath;
	}

	public static String extractFileName(String fullPath) {
		String resultPath = fullPath;
		int lastPathIndex = (resultPath.lastIndexOf("/") != -1) ? resultPath.lastIndexOf("/") : resultPath.lastIndexOf("\\");

		return (lastPathIndex != -1) ? resultPath.substring(lastPathIndex + 1) : resultPath;
	}
	
	public synchronized static void createImage(BufferedImage image ,String extension, String fullPath) throws IOException {
		OutputStream out2 = new FileOutputStream(fullPath); 
        ImageIO.write(image, extension,out2);  
        out2.close();  //출력스트림 닫기  
	}
}

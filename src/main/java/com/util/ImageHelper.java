package com.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.sf.image4j.util.ConvertUtil;

public abstract class ImageHelper {
	
	public static void floodFill(BufferedImage image, int x, int y, int oldColor, int newColor) {
		if(x<0) return;
		if(y<0) return;
		if(x>=image.getWidth()) return;
		if(y>=image.getHeight()) return;
		
		if(image.getRGB(x, y) != oldColor) return;
		
		image.setRGB(x, y, newColor);
		
		floodFill(image, x-1, y, oldColor, newColor);
		floodFill(image, x+1, y, oldColor, newColor);
		floodFill(image, x, y-1, oldColor, newColor);
		floodFill(image, x, y+1, oldColor, newColor);
	}
		
//	public static void Convert8Bit(BufferedImage bfi) {
//		ConvertUtil.convert8(bfi);
//	}
	
	public static Color getRgbColor(int value) {
		int v = value;
		int b = (v & 0xFF0000) >> 16;
		int g = (v & 0xFF00) >> 8;
		int r = (v & 0xFF);
		

		return new Color(r,g,b);
	}
}

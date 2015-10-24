package com.seamcarving;

import processing.core.PImage;

public class SeamCarving {
	
	
	public static PImage carveSeam(PImage image){
		
		
		return image;
		
	}
	
	public static int getIdx(int x, int y, int width) {
		return x + (y * width);
	}
	
	public static int getX(int idx, int width) {
		return idx % width;
	}
	
	public static int getY(int idx, int width) {
		return idx / width;
	}

}

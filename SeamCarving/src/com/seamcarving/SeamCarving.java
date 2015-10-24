package com.seamcarving;

import processing.core.PImage;

public class SeamCarving {
	
	
	public static PImage carveSeam(PImage image){
		return carveSeam(new int[image.height], image);
	}
	
	public static int getRed(int val) {
		return val & PImage.RED_MASK >> 16;
	}
	
	public static int getGreen(int val) {
		return val & PImage.GREEN_MASK >> 8;
	}
	
	public static int getBlue(int val) {
		return val & PImage.BLUE_MASK;
	}
	
	public static int getMag(int val) {
		return getRed(val) + getGreen(val) + getBlue(val);
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
	
	public static PImage carveSeam(int[] seam, PImage image) {
		PImage img = new PImage();
		img.init(image.width-1, image.height, PImage.RGB);
		for (int j = 0; j < image.height; j++) {
			for (int i = 0; i < image.width-1; i++) {
				if (i >= seam[j]) {
					img.set(i, j, image.get(i+1, j));
				} else {
					img.set(i, j, image.get(i, j));
				}
			}
		}
		return img;
	}

}

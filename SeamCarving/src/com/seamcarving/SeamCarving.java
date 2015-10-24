package com.seamcarving;

import processing.core.PImage;

public class SeamCarving {
	
	public static int[] calculateEnergy(PImage image){
		int[] energyArray = new int[image.pixels.length];
		
		for(int i = 0; i < image.width; i++){
			for(int j = 0; j < image.height; j++){
				int energy = (image.get(i, j));
				int top = 0, bottom = 0, left = 0, right = 0;
				if(i > 0)
					left = getDiff(image.get(i-1, j), energy);
				if(i < image.width - 1)
					right = getDiff(image.get(i+1, j), energy);
				if(j > 0)
					top = getDiff(image.get(i, j-1), energy);
				if(j < image.height - 1)
					bottom = getDiff(image.get(i, j+1), energy);
				energyArray[getIdx(i, j, image.width)] = top+bottom+left+right;
				
			}
		}
		
		return energyArray;
	}
	
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
	
	public static int getDiff(int a, int b) {
		return Math.abs(getRed(a) - getRed(b)) +
				Math.abs(getGreen(a) - getGreen(b)) +
				Math.abs(getBlue(a) - getBlue(b));
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

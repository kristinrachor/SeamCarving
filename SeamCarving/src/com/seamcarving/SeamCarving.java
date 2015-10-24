package com.seamcarving;

import processing.core.PImage;

public class SeamCarving {
	
	public static int[] calculateEnergy(PImage image){
		int[] energyArray = new int[image.pixels.length];
		
		for(int i = 0; i < image.width; i++){
			for(int j = 0; j < image.height; j++){
				int energy = (image.get(i, j));
				int top = 1, bottom = 1, left = 1, right = 1;
				if(i > 0)
					left = getDiff(image.get(i-1, j), energy);
				if(i < image.width - 1)
					right = getDiff(image.get(i+1, j), energy);
				if(j > 0)
					top = getDiff(image.get(i, j-1), energy);
				if(j < image.height - 1)
					bottom = getDiff(image.get(i, j+1), energy);
				energyArray[getIdx(i, j, image.width)] = (int) Math.sqrt(top+bottom+left+right);
				
			}
		}
		
		return energyArray;
	}
	
	public static int[] calculateSeamEnergySum(int[] energyArray, int width, int height){
		int[] seamEnergy = new int[energyArray.length];
		System.arraycopy(energyArray, 0, seamEnergy, 0, width);
		for(int i = 1; i < height; i++){
			for(int j = 0; j < width; j++){
				int left = Integer.MAX_VALUE;
				int right = Integer.MAX_VALUE;
				if(j != 0)
					left = seamEnergy[getIdx(j-1, i-1, width)];
				int center = seamEnergy[getIdx(j, i-1, width)];
				if(j != width -1)
					right = seamEnergy[getIdx(j+1, i-1, width)];
				
				int lowestSeam = Math.min(left, Math.min(center, right));
				seamEnergy[getIdx(j, i, width)] = lowestSeam + energyArray[getIdx(j, i, width)];
			}
		}
		return seamEnergy;
	}
	
	public static int[] calcuateSeam(int[] seamEnergy, int width, int height){
		int minSeam = Integer.MAX_VALUE;
		int Xindex = 0;
		for(int i = 0; i < width; i++){
			if(seamEnergy[getIdx(i, height -1 , width)] < minSeam){
				minSeam = seamEnergy[getIdx(i, height -1 , width)];
				Xindex = i;
			}
		}
		System.out.println(minSeam);
		int[] seam = new int[height];
		seam[height-1] = Xindex;
		for(int j = height-2; j >= 0; j--){
			int left = Integer.MAX_VALUE;
			int right = Integer.MAX_VALUE;
			if(Xindex != 0)
				left = seamEnergy[getIdx(Xindex -1, j, width)];
			int center = seamEnergy[getIdx(Xindex, j, width)];
			if(Xindex != width -1)
				right = seamEnergy[getIdx(Xindex + 1, j, width)];
			if(left < center && left <= right){
				Xindex--;
			}else if(right < center && right <= left){
				Xindex++;
			}
			seam[j] = Xindex;
		}
		return seam;
	}
	
	public static int[] lastSeam;
	
	public static PImage carveSeam(PImage image){
		int[] energy = calculateEnergy(image);
		int[] sumEnergy = calculateSeamEnergySum(energy, image.width, image.height);
		int[] seam = calcuateSeam(sumEnergy, image.width, image.height);
		lastSeam = seam;
		return carveSeam(seam, image);
	}
	
	public static int getRed(int val) {
		return (val & PImage.RED_MASK) >> 16;
	}
	
	public static int getGreen(int val) {
		return (val & PImage.GREEN_MASK) >> 8;
	}
	
	public static int getBlue(int val) {
		return val & PImage.BLUE_MASK;
	}
	
	public static int getMag(int val) {
		return getRed(val) + getGreen(val) + getBlue(val);
	}
	
	public static int getDiff(int a, int b) {
		int dr = getRed(a) - getRed(b);
		int dg = getGreen(a) - getGreen(b);
		int db = getBlue(a) - getBlue(b);
		return Math.abs(dr) + Math.abs(dg) + Math.abs(db);
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

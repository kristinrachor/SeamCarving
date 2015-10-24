package com.seamcarving;

import processing.core.PApplet;

public class SeamCarvingDemo extends PApplet {

	private static final long serialVersionUID = 8310742690921415484L;


	public void setup() {
		size(800, 600);
		ellipseMode(CENTER);
		rectMode(CENTER);
		colorMode(RGB, 255, 255, 255, 255);
		noStroke();
		imageMode(CENTER);
		
		
	}
	
	public void draw() {
		background(0);

		
		
	}
	
	
	public static void main(String[] args) {
		PApplet.main(new String[] {"com.seamcarving.SeamCarvingDemo"});
	}
}

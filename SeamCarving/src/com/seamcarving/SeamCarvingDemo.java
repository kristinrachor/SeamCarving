package com.seamcarving;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

public class SeamCarvingDemo extends PApplet {

	private static final long serialVersionUID = 8310742690921415484L;

	public static PImage image;

	public void setup() {
		size(800, 600);
		ellipseMode(CENTER);
		rectMode(CENTER);
		colorMode(RGB, 255, 255, 255, 255);
		noStroke();
		imageMode(CENTER);
		
		image = this.loadImage("img/mountain.png");
	}
	
	public void draw() {
		background(0);

		g.image(image, 400, 300);
	}
	
	
	public void keyPressed(KeyEvent e) {
		if (e.getKey() == ' ') {
			image = SeamCarving.carveSeam(image);
		}
	}
	
	
	public static void main(String[] args) {
		PApplet.main(new String[] {"com.seamcarving.SeamCarvingDemo"});
	}
	
}

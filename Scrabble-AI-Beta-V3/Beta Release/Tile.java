package edu.mccc.cos210.fp.said;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	private int value;
	private String face;
	private BufferedImage img = null;
	
	public Tile(int v, String s) {
		value = v;
		face = s;
		initImage();
	}
	public int getVal() {
		return value;
	}
	public void setVal(int v) {
		value = v;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String f) {
		face = f;
	}
	public BufferedImage getImage() {
		return img;
	}
	private void initImage() {
		try {
			this.img = ImageIO.read(new File("images/said/" + face + ".png"));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}		
	public String toString() {
		return ("The Value is of " + face + " is: " + value);	
	}
}

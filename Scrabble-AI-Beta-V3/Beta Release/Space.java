package edu.mccc.cos210.fp.said;

import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class Space {
	private String multiplier = null;
	private Tile tile = null;
	private int x;
	private int y;
	private boolean isNewTile = false;
	public Space (String m, Tile tile, int x, int y) {
		multiplier = m;
		this.tile = tile;
		this.x = x;
		this.y = y;
	}
	public void setMult(String m) {
		multiplier = m;
	}
	public String getMult() {
		return multiplier;		
	}
	public void setTile(Tile t) {
		tile = t;
	}
	public Tile getTile() {
		return tile;		
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;		
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;		
	}
	public void setHasNewTile(boolean newTile) { //NEW
		isNewTile = newTile;
	}
	public boolean hasNewTile() { //NEW
		return isNewTile;
	}
	public int getRow(Board board) {
		return (this.y - board.getY()) / 40;
	}
	public int getCol(Board board) {
		return (this.x - board.getX()) / 40;
	}
	public void drawTile(Graphics2D g2d) {
		g2d.drawImage(tile.getImage(), x, y, null);
	}
	public String toString() {
		return ("Location: (" + x + "," + y + ") With multiplier of: " + multiplier);
	}
}

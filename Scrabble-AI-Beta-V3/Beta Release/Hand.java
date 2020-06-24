package edu.mccc.cos210.fp.said;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;

public class Hand {
	int x;
	int y;
	IArray<Space> hand = new Array<Space>(7);
	public Hand(int x, int y, Bag bag) {
		this.x = x;
		this.y = y;
		initHand(bag);
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
	public Space getSpace(int index) {
		return hand.get(index);
	}
	public void addTile(Tile tile) {
		for(int i = 0; i < hand.getSize(); i++) {
			if (hand.get(i).getTile() == null) {
				hand.get(i).setTile(tile);
				break;
			}
		}
	}
	public Tile getTile(int index) {
		Tile tile = hand.get(index).getTile();
		hand.get(index).setTile(null);
		return tile;
	}
	public void fillHand(Bag bag) {
		for (int i = 0; i < hand.getSize(); i++) {
			if (hand.get(i).getTile() == null && !bag.isEmpty()) {
				hand.get(i).setTile(bag.nextTile());
			}
		}		
	}
	public void emptyHand() {
		for (int i = 0; i < hand.getSize(); i++) {
			getSpace(i).setTile(null);
		}	
	}
	public boolean isNotFull() {
		for (int i = 0; i < hand.getSize(); i++) {
			if (hand.get(i).getTile() == null) {
				return true;
			}
		}
		return false;
	}
	public void initHand(Bag bag) {
		for (int i = 0; i < hand.getSize(); i++) {
			int X = this.x + (i * 50);
			X += 10;
			if (hand.get(i) == null) {
				hand.set(i, new Space(null, bag.nextTile(), X, y));
			}
		}		
	}
	public void drawHand(Graphics2D g2d) {
		Rectangle r = new Rectangle(x, y, 360, 40);
		g2d.setColor(Color.BLACK);
		g2d.fill(r);
		for(int i = 0; i < 7; i++) {
			if(hand.get(i).getTile() != null) {
				g2d.drawImage(hand.get(i).getTile().getImage(),
							  hand.get(i).getX(),
						      hand.get(i).getY(),
						      null);
			}
		}
	}
	public int getSize() {
		return hand.getSize();
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			sb.append(hand.get(i) + "\n");
		}
		return sb.toString();
	}
}

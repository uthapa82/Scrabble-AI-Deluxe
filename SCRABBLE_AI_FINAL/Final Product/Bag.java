package edu.mccc.cos210.fp.said;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;

public class Bag {
	IArray<Tile> bag = new Array<Tile>(100);
	int tLeft = 100;
	public Bag() {
		initBag();
	}
	public void initBag() {
		int random;
		for (int i = 0; i < 100; i ++) {
			random = (int)(Math.random() * 100);
			int n = 0;
			while (bag.get(random) != null) {
				if (bag.get(n) == null) {
					random = n;
				}
				n++;	
			}
			if (i < 1) {
				bag.set(random, new Tile(10, "Z"));
			} else if (i < 2) {
				bag.set(random, new Tile(8, "X"));
			} else if (i < 3) {
				bag.set(random, new Tile(10, "Q"));
			} else if (i < 4) {
				bag.set(random, new Tile(5, "K"));
			} else if (i < 5) {
				bag.set(random, new Tile(8, "J"));;
			} else if (i < 9) {
				bag.set(random, new Tile(4, "Y"));
			} else if (i < 11) {
				bag.set(random, new Tile(4, "W"));
			} else if (i < 13) {
				bag.set(random, new Tile(4, "V"));
			} else if (i < 15) {
				bag.set(random, new Tile(3, "P"));
			} else if (i < 17) {
				bag.set(random, new Tile(3, "M"));
			} else if (i < 19) {
				bag.set(random, new Tile(4, "H"));
			} else if (i < 21) {
				bag.set(random, new Tile(4, "F"));
			} else if (i < 23) {
				bag.set(random, new Tile(3, "C"));
			} else if (i < 25) {
				bag.set(random, new Tile(3, "B"));
			} else if (i < 28) {
				bag.set(random, new Tile(2, "G"));
			} else if (i < 32) {
				bag.set(random, new Tile(1, "U"));
			} else if (i < 36) {
				bag.set(random, new Tile(1, "L"));
			} else if (i < 40) {
				bag.set(random, new Tile(2, "D"));
			} else if (i < 44) {
				bag.set(random, new Tile(1, "S"));
			} else if (i < 50) {
				bag.set(random, new Tile(1, "T"));
			} else if (i < 56) {
				bag.set(random, new Tile(1, "R"));
			} else if (i < 62) {
				bag.set(random, new Tile(1, "N"));
			} else if (i < 70) {
				bag.set(random, new Tile(1, "O"));
			} else if (i < 79) {
				bag.set(random, new Tile(1, "I"));
			} else if (i < 88) {
				bag.set(random, new Tile(1, "A"));
			} else if (i < 100) {
				bag.set(random, new Tile(1, "E"));
			}
		}
	}
	public void reInitBag() {
		tLeft = 100;
		for(int i = 0; i < bag.getSize(); i++) {
			bag.set(i, null);
		}
		initBag();
	}
	public Tile nextTile() {
		Tile next;
		int random = (int)(Math.random() * 100);
		int n = 0;
		while (bag.get(random) == null) {
			random = n;
			n++;
		}
		next = bag.get(random);
		bag.set(random, null);
		tLeft --;
		return next;
	}
	public void addTile(Tile tile) {
		int random = (int)(Math.random() * 100);
		int n = 0;
		while (bag.get(random) != null) {
			random = n;
			n++;
		}
		bag.set(random, tile);
		tLeft ++;
	}
	public boolean isEmpty() {
		if (tLeft == 0) {
			return true;
		} else {
			return false;
		}
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			sb.append(bag.get(i) + "\n");
	    }
		return sb.toString();
	}
}

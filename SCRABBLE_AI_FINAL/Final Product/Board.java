package edu.mccc.cos210.fp.said;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;

public class Board {
	private int x;
	private int y;
	private BufferedImage image = null;
	private IArray<Array<Space>> board = new Array<Array<Space>>(15);
	public Board (int x, int y) {
		this.x = x;
		this.y = y;
		initBoard();
	}
	public BufferedImage getImage() {
		return image;
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
	public Space getSpace(int row, int col) {
		return board.get(row).get(col);
	}
	public boolean spaceIsClear(Space space) {
		if (space.getTile() != null) {
			if(spaceIsClearHor(space) || spaceIsClearVert(space)) {
				return true;
			}
		}
		return false;
	}
	public boolean spaceIsClearHor(Space space) {
		int row = space.getRow(this);
		int col = space.getCol(this);
		if (col != 0 && col != 14) {
			if (board.get(row).get(col - 1).getTile() == null 
				&& board.get(row).get(col + 1).getTile() == null) {
				return true; 
			}else{
				return false;
			}
		}else {
			return false;
		}
	}
	public boolean spaceIsClearVert(Space space) {
		int row = space.getRow(this);
		int col = space.getCol(this);
		if (row != 0 && row != 14) {
			if (board.get(row - 1).get(col).getTile() == null 
					&& board.get(row + 1).get(col).getTile() == null) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	public BufferedImage getTileImage(int row, int col) {
		return board.get(row).get(col).getTile().getImage();
	}
	public void drawBoard(Graphics2D g2d) {
		for(int row = 0; row < board.getSize(); row++) {
			for(int col = 0; col < board.getSize(); col++) {
				int X = getSpace(row, col).getX();
				int Y = getSpace(row, col).getY();
				if(board.get(row).get(col).getTile() != null) {
					g2d.drawImage(getTileImage(row, col), X, Y, null);
				}
			}
		}	
	}
	public void initBoard() {
		for (int i = 0; i < 15; i++) {
			board.set(i, new Array<Space>(15));
		}
		for (int row = 0; row < board.getSize(); row++) {
			int Y = this.y + (row * 40); 
			for (int col = 0; col < board.getSize(); col++) {
				int X = this.x + (col * 40);
				board.get(row).set(col, new Space(null, null, X, Y));
			}
		}
		initMult();
		initImage();
	}
	public void resetHasNewTile() {
		for (int row = 0; row < 15; row++) {
			for (int col = 0; col < 15; col++) {
				this.getSpace(row, col).setHasNewTile(false);
			}
		}
	}
	private void initImage() {
		try {
			image = ImageIO.read(new File("images/said/board.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	private void initMult()
	{
		this.getSpace(0, 0).setMult("TW");
		this.getSpace(0, 7).setMult("TW");
		this.getSpace(0, 14).setMult("TW");
		this.getSpace(7, 0).setMult("TW");
		this.getSpace(7, 14).setMult("TW");
		this.getSpace(14, 0).setMult("TW");
		this.getSpace(14, 7).setMult("TW");
		this.getSpace(14, 14).setMult("TW");
		this.getSpace(7,7).setMult("DW");
		this.getSpace(1, 1).setMult("DW");
		this.getSpace(1, 13).setMult("DW");
		this.getSpace(2, 2).setMult("DW");
		this.getSpace(2, 12).setMult("DW");
		this.getSpace(3, 3).setMult("DW");
		this.getSpace(3,11).setMult("DW");
		this.getSpace(4, 4).setMult("DW");
		this.getSpace(4, 10).setMult("DW");
		this.getSpace(10, 4).setMult("DW");
		this.getSpace(10, 10).setMult("DW");
		this.getSpace(11, 3).setMult("DW");
		this.getSpace(11, 11).setMult("DW");
		this.getSpace(12, 2).setMult("DW");
		this.getSpace(12, 12).setMult("DW");
		this.getSpace(13, 1).setMult("DW");
		this.getSpace(13, 13).setMult("DW");
		this.getSpace(1, 5).setMult("TL");
		this.getSpace(1, 9).setMult("TL");
		this.getSpace(5, 1).setMult("TL");
		this.getSpace(5, 5).setMult("TL");
		this.getSpace(5, 9).setMult("TL");
		this.getSpace(5, 13).setMult("TL");
		this.getSpace(9, 1).setMult("TL");
		this.getSpace(9, 5).setMult("TL");
		this.getSpace(9, 9).setMult("TL");
		this.getSpace(9, 13).setMult("TL");
		this.getSpace(13, 9).setMult("TL");
		this.getSpace(13, 5).setMult("TL");
		this.getSpace(0, 3).setMult("DL");
		this.getSpace(0, 11).setMult("DL");
		this.getSpace(2, 6).setMult("DL");
		this.getSpace(2, 8).setMult("DL");
		this.getSpace(3, 0).setMult("DL");
		this.getSpace(3, 7).setMult("DL");
		this.getSpace(3, 14).setMult("DL");
		this.getSpace(6, 2).setMult("DL");
		this.getSpace(6, 6).setMult("DL");
		this.getSpace(6, 8).setMult("DL");
		this.getSpace(6, 12).setMult("DL");
		this.getSpace(7, 3).setMult("DL");
		this.getSpace(7, 11).setMult("DL");
		this.getSpace(8, 2).setMult("DL");
		this.getSpace(8, 6).setMult("DL");
		this.getSpace(8, 8).setMult("DL");
		this.getSpace(8, 12).setMult("DL");
		this.getSpace(11,0).setMult("DL");
		this.getSpace(11, 7).setMult("DL");
		this.getSpace(11, 14).setMult("DL");
		this.getSpace(12, 6).setMult("DL");
		this.getSpace(12,8 ).setMult("DL");
		this.getSpace(14, 3).setMult("DL");
		this.getSpace(14, 11).setMult("DL");
	}
}

package edu.mccc.cos210.fp.said;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IVector;
import edu.mccc.cos210.ds.Vector;

public class ScrabbleMouseListener extends MouseAdapter {
	private int row;
	private int col;
	private boolean grabbed = false;
	private Board board;
	private Hand hand;
	private ScrabbleView panel;
	private JFrame jf;
	private Tile heldTile;
	private IArray<Space> placedSpaces;
	public ScrabbleMouseListener(Board board, Hand hand, JFrame jf, ScrabbleView panel, IArray<Space> placedSpaces) {
		this.board = board;
		this.hand = hand;
		this.jf = jf;
		this.panel = panel;
		this.placedSpaces = placedSpaces;
	}
	public void mouseClicked(MouseEvent e) {
    	if (grabbed) {
    		place(e, heldTile);
    		jf.repaint();
    	} else {
    		grab(e);
    		jf.repaint();
    	}
    }
    private void place(MouseEvent e, Tile tile) {
    	row = (e.getY() - 100) / 40;
    	col = (e.getX() - 100) / 40;
    	if((row >= 0 && row <= 14) && (col >= 0 && col <= 14) && board.getSpace(row, col).getTile() == null) {
    		board.getSpace(row, col).setTile(tile);
    		placeInArray(board.getSpace(row, col));
    		board.getSpace(row, col).setHasNewTile(true);
    		panel.repaint();
    		grabbed = false;
    	}
    }
    private void grab(MouseEvent e) {
    	row = ((e.getY() - hand.getY()) - 10) / 50;
		col = ((e.getX() - hand.getX()) - 10) / 50;
		if((row == 0) && (col >= 0 && col < 7)) {
			heldTile = hand.getSpace(col).getTile();
			hand.getSpace(col).setTile(null);
			panel.repaint();
			grabbed = true;
		}
    }
    private void placeInArray(Space space) {
    	for (int i = 0; i < placedSpaces.getSize(); i++) {
    		if (placedSpaces.get(i) == null) {
    			placedSpaces.set(i, space);
    			break;
    		}
    	}
    }
	public void setPlacedSpaces(IArray<Space> placedSpaces) {
		this.placedSpaces = placedSpaces;	
	}
}

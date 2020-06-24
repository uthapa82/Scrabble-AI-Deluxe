package edu.mccc.cos210.fp.said;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IVector;
import edu.mccc.cos210.ds.Vector;

public class Scrabble {
	Scorer scorer = new Scorer();
	Board board = new Board(100, 100);
	Bag bag = new Bag();
	Hand hand = new Hand(870, 360, bag);
	AI ai =  new AI(board, bag);
	IArray<Space> placedSpaces = new Array<Space>(7);
	final int MAX_SCORE = 75;
	int hScore = 0;
	int aiScore = 0;
	boolean pTurn = true;
	public Scrabble() {
		initSwing();
	}
	public static void main(String... args) {
		try {
			EventQueue.invokeAndWait(Scrabble::new);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	private void initSwing() {
		JFrame jf = new JFrame("Scrabble");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScrabbleView jp = new ScrabbleView(board, bag, hand);
		JButton submit = new JButton("Submit");
		submit.setBounds(900,420,100,30);
		jf.add(submit);
		JButton redraw = new JButton("Redraw");
		redraw.setBounds(1005, 420, 100, 30);
		jf.add(redraw);
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(1110, 420, 100, 30);
		jf.add(cancel);
		JButton forfeit = new JButton("Forfeit");
		forfeit.setBounds(1005, 470, 100, 30);
		jf.add(forfeit);
		jf.add(jp, BorderLayout.CENTER);
		ScrabbleMouseListener mouse = new ScrabbleMouseListener(board, hand, jf, jp, placedSpaces);
		jp.addMouseListener(mouse);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
		jp.requestFocus();
		//GAMEPLAY SCREEN
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		    	if (pTurn) {
		    		int bScore = scorer.score(board);
		    		if (bScore != 0) {
		    			playerTurn(jp, bScore, mouse);
		    			jp.repaint();
			    		jf.repaint();
		    		} else {
		    			jp.setMessage("Invalid Move");
		    			cancel();
		    			scorer.reset();
		    			nullifyPSpaces(mouse);
		    			jp.repaint();
			    		jf.repaint();
		    		}
		    		if(!pTurn) {
		    			aiTurn(jp);
		    			jp.repaint();
			    		jf.repaint();
		    		}
		    	}	
		    } 
		});
		forfeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetGame(jp, mouse);
				jf.repaint();
	    	} 
		});
		redraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (pTurn) {
					redraw();
					pTurn = false;
					jp.repaint();
					jf.repaint();
	    			ai.makeMove();
	    			jp.addAIScore(ai.getScore());
	    			ai.reset();
	    			board.resetHasNewTile();
	    			pTurn = true;
	    			jp.repaint();
					jf.repaint();
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				if (pTurn) {
					cancel();
					nullifyPSpaces(mouse);
					jp.repaint();
					jf.repaint();
				}
			}
		});			
	}
	public void nullifyPSpaces(ScrabbleMouseListener mouse) {
		placedSpaces = new Array<Space>(7);
		mouse.setPlacedSpaces(placedSpaces);
	}
	public void cancel() {
		for(int i = 0; i < placedSpaces.getSize(); i++) {
			if (placedSpaces.get(i) != null) {
				hand.addTile(placedSpaces.get(i).getTile());
				placedSpaces.get(i).setTile(null);
				placedSpaces.get(i).setHasNewTile(false);
			}
		}
	}
	public void redraw() {
		for(int i = 0; i < hand.getSize(); i++) {
			bag.addTile(hand.getTile(i));
		}
		hand.fillHand(bag);
	}
	public void playerTurn(ScrabbleView jp, int bScore, ScrabbleMouseListener mouse) {
		hand.fillHand(bag);
		jp.addHScore(bScore);
		jp.setMessage(scorer.getMessage());
		board.resetHasNewTile();
		scorer.reset();
		board.resetHasNewTile();
		nullifyPSpaces(mouse);
		pTurn = false;
	}
	public void aiTurn(ScrabbleView jp) {
		ai.makeMove();
		jp.addAIScore(ai.getScore());
		ai.reset();
		board.resetHasNewTile();
		pTurn = true;
	}
	public void resetGame(ScrabbleView jp, ScrabbleMouseListener mouse) {
		board.initBoard();
		bag.reInitBag();
		hand.emptyHand();
		hand.fillHand(bag);
		ai = new AI(board, bag);
		pTurn = true;
		jp.resetHScore();
		jp.resetAIScore();
		nullifyPSpaces(mouse);
		jp.repaint();	
	}
}
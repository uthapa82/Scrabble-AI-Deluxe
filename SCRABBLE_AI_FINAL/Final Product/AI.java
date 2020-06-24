package edu.mccc.cos210.fp.said;

import java.io.BufferedReader;
import java.io.FileReader;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IOrderedList;
import edu.mccc.cos210.ds.OrderedList;
import edu.mccc.cos210.ds.ISortedList;
import edu.mccc.cos210.ds.SortedList;

public class AI  {
	private IOrderedList<String> dictionary = new OrderedList<>();
	protected ISortedList<String> theList;
	private IOrderedList<Character> boardLetters = new OrderedList<>();
	IArray<Space> placedSpaces = new Array<Space>(8);
	private Scorer scorer = new Scorer();
	private Board board;
	private Bag bag;
	private Hand hand;
	private int score = 0;
	private int turnScore = 0;
	public AI(Board board, Bag bag) {
		initDictionary();
		theList = new SortedList<String>((s1, s2) -> {
			if(s1.length() == s2.length()) {
				return s2.compareTo(s1);
			}
			return s2.length() - s1.length();
		});
		this.board = board;
		this.bag = bag;
		this.hand = new Hand(0, 0, bag);
	}
	public void makeMove() {
		getLetters();
		findWords(hand);
		place();
		hand.fillHand(bag);
		if (turnScore == 0) {
			redraw();
		} else {
			score += turnScore;
		}
		reset();
	}
	private void getLetters() {
		Space newLetter;
		for(int row = 0; row < 15; row ++) {
			for(int col = 0; col < 15; col ++) {
				if(board.getSpace(row, col).getTile() != null ) {
					newLetter = board.getSpace(row, col);
				    boardLetters.addLast(newLetter.getTile().getFace().charAt(0));
				}
			}
		}
	}
	private void findWords(Hand hand) {
		IOrderedList<String> alphabetized = new OrderedList<String>();
		ISortedList<Character> tileLetters = new SortedList<>();
		StringBuilder word = new StringBuilder();
		while (!boardLetters.isEmpty()) {
			for (int i = 0; i < hand.getSize(); i++) {
				tileLetters.add(hand.getSpace(i).getTile().getFace().charAt(0));
			}
				tileLetters.add(boardLetters.getFirst());
				boardLetters.removeFirst();
			StringBuilder tsb = new StringBuilder(); 
			for (Character c : tileLetters) {
				tsb.append(c);
			}
			alphabetized.add(tsb.toString());
			tileLetters = new SortedList<>();
		}
		while (!alphabetized.isEmpty()) {
			word.append(alphabetized.getFirst());
			while (word.length() != 1) {
				dictionary.stream()
				.filter(x -> x.length() == word.length())
				.forEach(x -> {
						ISortedList<Character> dletters = new SortedList<>();
						for (int i = 0; i < x.length(); i++) {
						dletters.add(x.charAt(i));
						}
						StringBuilder dsb = new StringBuilder();
						for (Character c : dletters) {
							dsb.append(c);
						}
						if (word.toString().toLowerCase().contains(dsb.toString())) {
								theList.add(x);
						}
			
					});
				word.deleteCharAt(word.length() - 1);
			}
			alphabetized.removeFirst();
			word.delete(0, word.length());
		}
	}
	public void place() {
		IOrderedList<Space> spaces = findAvailableSpaces();
		boolean placed = false;
		int p;
		for (String word : theList) {
			for (Space s : spaces) {
				if(!placed) {
					p = getFacePosition(s.getTile().getFace().charAt(0), word);
					if(p != -1) {
						if (board.spaceIsClearHor(s)) {
							placeHor(s, word, p);
						} else if (board.spaceIsClearVert(s)) {
							placeVert(s, word, p);
						}
					}
					turnScore = scorer.score(board);
					if (turnScore == 0) {
						scorer.reset();
						cancel();
					} else {
						placed = true;
					}
				} else {
					break;
				}
			}
		}
		scorer.reset();
	}
	public IOrderedList<Space> findAvailableSpaces() {
		IOrderedList<Space> clear = new OrderedList<Space>();
		for (int r = 0; r < 15; r++) {
			for (int c = 0; c < 15; c++) {
				if (board.spaceIsClear(board.getSpace(r, c))) {
					clear.add(board.getSpace(r, c));
				}
			}
		}
		return clear;
	}
	private int getFacePosition(char face, String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.toUpperCase().charAt(i) == face) {
				return i;
			}
		}
		return -1;
	}
	private void placeHor(Space s, String word, int p) {
		Tile tile;
		int col = s.getCol(board);
		for (int i = p - 1; i >= 0; i--) {
			col -= 1;
			if (col >= 0 && board.getSpace(s.getRow(board), col).getTile() == null) {
				tile = getFromHand(word.charAt(i));
				board.getSpace(s.getRow(board), col).setTile(tile);
				board.getSpace(s.getRow(board), col).setHasNewTile(true);
				placeInPSpaces(board.getSpace(s.getRow(board), col));
			}
		}
		col = s.getCol(board);
		for (int i = p + 1; i < word.length(); i++) {
			col += 1;
			if (col < 15 && board.getSpace(s.getRow(board), col).getTile() == null) {
				tile = getFromHand(word.charAt(i));
				board.getSpace(s.getRow(board), col).setTile(tile);
				board.getSpace(s.getRow(board), col).setHasNewTile(true);
				placeInPSpaces(board.getSpace(s.getRow(board), col));
			}
		}
	}
	private void placeVert(Space s, String word, int p) {
		Tile tile;
		int row = s.getRow(board);
		for (int i = p - 1; i >= 0; i--) {
			row -= 1;
			if (row >= 0 && board.getSpace(row, s.getCol(board)).getTile() == null) {
				tile = getFromHand(word.charAt(i));
				board.getSpace(row, s.getCol(board)).setTile(tile);
				board.getSpace(row, s.getCol(board)).setHasNewTile(true);
				placeInPSpaces(board.getSpace(row, s.getCol(board)));
			}
		}
		row = s.getRow(board);
		for (int i = p + 1; i < word.length(); i++) {
			row += 1;
			if (row < 15 && board.getSpace(row, s.getCol(board)).getTile() == null) {
				tile = getFromHand(word.charAt(i));
				board.getSpace(row, s.getCol(board)).setTile(tile);
				board.getSpace(row, s.getCol(board)).setHasNewTile(true);
				placeInPSpaces(board.getSpace(row, s.getCol(board)));
			}
		}
	}
	private Tile getFromHand(char face) {
		for (int i = 0; i < hand.getSize(); i++) {
			if (hand.getSpace(i).getTile() != null && hand.getSpace(i).getTile().getFace().toLowerCase().charAt(0) == face) {
				return hand.getTile(i);
			}
		}
		return null;
	}
	private void placeInPSpaces(Space space) {
	    for (int i = 0; i < placedSpaces.getSize(); i++) {
	    	if (placedSpaces.get(i) == null) {
	    		placedSpaces.set(i, space);
	   			break;
	   		}
	   	}
    }
	public void cancel() {
		for(int i = 0; i < placedSpaces.getSize(); i++) {
			if (placedSpaces.get(i) != null && placedSpaces.get(i).getTile() != null) {
				hand.addTile(placedSpaces.get(i).getTile());
				placedSpaces.get(i).setTile(null);
				placedSpaces.get(i).setHasNewTile(false);
			}
		}
		placedSpaces = new Array<Space>(8);
	}
	public void redraw() {
		for(int i = 0; i < hand.getSize(); i++) {
			bag.addTile(hand.getTile(i));
		}
		hand.fillHand(bag);
	}
	public void reset() {
		theList = new SortedList<String>((s1, s2) -> {
			if(s1.length() == s2.length()) {
				return s2.compareTo(s1);
			}
			return s2.length() - s1.length();
		});
		boardLetters = new OrderedList<Character>();
		placedSpaces = new Array<Space>(8);
		turnScore = 0;
		scorer.reset();
	}
	public int getScore() {
		return score;
	}
	private void initDictionary() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/pocket.dic"))) {
			String s = "";
			while ((s = br.readLine()) != null) {
				dictionary.add(s);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
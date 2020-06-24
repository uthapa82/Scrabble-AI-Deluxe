package edu.mccc.cos210.fp.said;

import java.io.BufferedReader;
import java.io.FileReader;

import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IOrderedList;
import edu.mccc.cos210.ds.IVector;
import edu.mccc.cos210.ds.OrderedList;
import edu.mccc.cos210.ds.Vector;

public class Scorer {
	private IOrderedList<String> dictionary = new OrderedList<>();
	private IVector<Vector<Space>> wordSpaces = new Vector<Vector<Space>>();
	private IVector<String> words = new Vector<String>();
	private IVector<String> matchedWords = new Vector<String>();
	private Board board;
	private int score = 0;
	public Scorer() {
		initDictionary();
	}
	public int score(Board board) {
		setBoard(board);
		getWordHor();
		getWordVert();
		if(isValid()) {
			scoreWords(wordSpaces);
		}
		return score;
	}	
	public String getMessage() {
		return matchedWords.get(0);
	}
	private void setBoard(Board board) {
		this.board = board;
	}
	private void getWordHor() {
		//Finds first space horizontally that has newly placed tile
		Space newLetter;
		for(int row = 0; row < 15; row ++) {
			for(int col = 0; col < 15; col ++) {
				if(board.getSpace(row, col).hasNewTile()) {
					newLetter = board.getSpace(row, col);
					addWordHor(newLetter);
					break;
				}
			}
		}
	}
	private void addWordHor(Space newLetter) {
		StringBuilder sb = new StringBuilder();
		int row = newLetter.getRow(board);
		int col = newLetter.getCol(board);
		int wIndex = wordSpaces.getSize();
		wordSpaces.pushBack(new Vector());
		//Finds empty spot (col) left of new tile 
		while (col != 0 && board.getSpace(row, col).getTile() != null) {
			col--;
		}
		if (col != 0) {
			col ++;
		}
		//Adds spaces with tiles from the first to last letters present
		while (col != 15 && board.getSpace(row, col).getTile() != null) {
			wordSpaces.get(wIndex).pushBack(board.getSpace(row, col));
			sb.append(board.getSpace(row, col).getTile().getFace());
			col++;
		}	
		if (sb.toString().length() > 1) {
			words.pushBack(sb.toString());
		}
	}
	private void getWordVert() {
		//Finds first space vertically that has newly placed tile
		Space newLetter;
		for(int col = 0; col < 15; col ++) {
			for(int row = 0; row < 15; row ++) {
				if(board.getSpace(row, col).hasNewTile()) {
					newLetter = board.getSpace(row, col);
					addWordVert(newLetter);
					break;
				}
			}
		}
	}
	private void addWordVert(Space newLetter) {
		StringBuilder sb = new StringBuilder();
		int row = newLetter.getRow(board);
		int col = newLetter.getCol(board);
		int wIndex = wordSpaces.getSize();
		wordSpaces.pushBack(new Vector());
		//Finds empty spot (col) left of new tile 
		while (row != 0 && board.getSpace(row, col).getTile() != null) {
			row--;
		}
		if (row != 0) {
			row ++;
		}
		//Adds spaces with tiles from the first to last letters present
		while (row != 15 && board.getSpace(row, col).getTile() != null) {
			wordSpaces.get(wIndex).pushBack(board.getSpace(row, col));
			sb.append(board.getSpace(row, col).getTile().getFace());
			row++;
		}
		//Adds found word to list of words if word is not one letter long
		if (sb.toString().length() > 1) {
			words.pushBack(sb.toString());
		}
	}
	private boolean isValid() {
		if (wordsAreConnected() && centerHasTile()) {
			for (int i = 0; i < words.getSize(); i++) {
				String word = words.get(i);
				dictionary.stream()
				.filter(x -> x.length() == word.length())
				.forEach( x -> {
						if (x.toUpperCase().contains(word.toUpperCase())) {
							matchedWords.pushBack(word);
						}
					}
				);
			}
			if (matchedWords.getSize() == words.getSize()) {
				return true;
			}
		}
		return false;
	}
	private boolean wordsAreConnected() {
		IOrderedList<Space> newlyPlaced = new OrderedList<Space>();
		for (int row = 0; row < 15; row++) {
			for(int col = 0; col < 15; col++) {
				if (board.getSpace(row, col).hasNewTile()) {
					newlyPlaced.addLast(board.getSpace(row, col));
				}
			}
		}
		if (!newlyPlaced.isEmpty()) {
			Space placedFirst = newlyPlaced.getFirst();
			Space placedLast = newlyPlaced.getLast();
			if(tileIsAnchored(placedFirst) || tileIsAnchored(placedLast)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	private boolean centerHasTile() {
		if (board.getSpace(7, 7).getTile() != null) {
			return true;
		} else {
			return false;
		}
	}
	private boolean tileIsAnchored(Space space) {
		int row = space.getRow(board);
		int col = space.getCol(board);
		if (board.getSpace(row + 1, col).getTile() != null
			|| board.getSpace(row - 1, col).getTile() != null
			|| board.getSpace(row, col + 1).getTile() != null
			|| board.getSpace(row, col - 1).getTile() != null) {
			return true;
		} else {
			return false;
		}
	}
	private void scoreWords(IVector<Vector<Space>> wordSpaces) {
		int wordScore = 0;
		int multiplier = 1;
		Space current;
		for (int s = 0; s < wordSpaces.getSize(); s++) {
			if(wordSpaces.get(s).getSize() > 1) {
				for (int t = 0; t < wordSpaces.get(s).getSize(); t++ ) {
					current = wordSpaces.get(s).get(t);
					if(current.getMult() == "DL") {
						wordScore += (current.getTile().getVal() * 2);
					} else if (current.getMult() == "TL") {
						wordScore += (current.getTile().getVal() * 3);
					} else if (current.getMult() == "DW") {
						wordScore += current.getTile().getVal();
						multiplier = 2;
					} else if (current.getMult() == "TW") {
						wordScore += current.getTile().getVal();
						multiplier = 3;
					} else {
						wordScore += current.getTile().getVal();
					}
					current.setMult(null);
				}
			}
			score += (wordScore * multiplier);
			wordScore = 0;
			multiplier = 1;
		}
	}
	public void reset() {
		wordSpaces = new Vector<Vector<Space>>();
		words = new Vector();
		matchedWords = new Vector();
		score = 0;
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

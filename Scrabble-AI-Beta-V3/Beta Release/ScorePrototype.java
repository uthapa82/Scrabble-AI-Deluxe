package edu.mccc.cos210.fp.said;

import java.util.Scanner;

import edu.mccc.cos210.ds.Deque;
import edu.mccc.cos210.ds.IDeque;

public class ScorePrototype {

	public static void main(String[] args) {
		Bag bag = new Bag();
		Hand hand = new Hand(0, 0, bag);
		System.out.print(hand.getTile(0));
	}
		/*int row;
		int col;
		String face;
		Board board = new Board(100, 100);
		Space placed;
		IDeque<Space> builtWord = new Deque<Space>();
		Scorer scorer = new Scorer();
		Scanner scan = new Scanner(System.in);
		
		//Spell APPLE left to right from center
		board.getSpace(7, 7).setTile(new Tile(1, "A"));
		board.getSpace(7, 8).setTile(new Tile(3, "P"));
		board.getSpace(7, 9).setTile(new Tile(3, "P"));
		board.getSpace(7, 10).setTile(new Tile(1, "L"));
		//board.getSpace(7, 11).setTile(new Tile(1, "E"));
		//board.getSpace(8, 11).setTile(new Tile(1, "T"));
		//board.getSpace(8, 11).setIsNewTile(true);
		
		//Spell AGE top down from center
		board.getSpace(8, 7).setTile(new Tile(2, "G"));
		//board.getSpace(8, 7).setIsNewTile(true);
		board.getSpace(9, 7).setTile(new Tile(1, "E")); 
		
		//Request Information
		System.out.println("Enter ROW");
		row = scan.nextInt();
		System.out.println("Enter COLUMN");
		col = scan.nextInt();
		System.out.println("Blank tile: Enter tile Face");
		face = scan.next();
		
		board.getSpace(row, col).setTile(new Tile(1, face));
		board.getSpace(row, col).setIsNewTile(true);
		scorer.score(board, "HI");
	} */
	/*public static Space findFirstSpaceAbove(Board board, int row, int col) {
		while (row != 0 && board.getSpace(row, col).getTile() != null) {
			row--;
		}
		if(row == 0) {
			return board.getSpace(row, col);
		} else {
			return board.getSpace(row + 1, col);
		}
	}
	static boolean tileAboveOrBelow(Board board, Space space) {
		int row = (space.getY() - board.getY()) / 40; 
		int col = (space.getX() - board.getX()) / 40; 
		if(board.getSpace(row - 1, col) != null || board.getSpace(row + 1, col) != null) {
			return true;
		} else {
			return false;
		}
	}
	public static Space findFirstSpaceLeft(Board board, int row, int col) {
		while (col != 0 && board.getSpace(row, col).getTile() != null) {
			col--;
		}
		if(col == 0) {
			return board.getSpace(row, col);
		} else {
			return board.getSpace(row, col + 1);
		}	
	}
	public static boolean tileLeftOrRight(Board board, Space space) {
		int row = space.getRow(board); 
		int col = space.getCol(board); 
		if(board.getSpace(row, col - 1).getTile() != null || board.getSpace(row, col + 1).getTile() != null) {
			return true;
		} else {
			return false;
		}
	}
	public static void addWordLeft(Board board, Space space, IDeque<Space> builtWord) {
		int row = space.getRow(board);
		int col = space.getCol(board);
		while (board.getSpace(row, col).getTile() != null) {
			builtWord.enqueueLast(board.getSpace(row, col));
			col ++;
		}
	}
	public static void addWordAbove(Board board, Space space, IDeque<Space> builtWord) {
		int row = space.getRow(board);
		int col = space.getCol(board);
		while (board.getSpace(row, col).getTile() != null) {
			builtWord.enqueueLast(board.getSpace(row, col));
			row ++;
		}
	}
	public static void createWord(Board board, Space space, IDeque<Space> builtWord) {
			Space firstSpace;
			if (tileLeftOrRight(board, space)) {
				firstSpace = findFirstSpaceLeft(board, space.getRow(board), space.getCol(board));
				System.out.println(firstSpace);
				addWordLeft(board, firstSpace, builtWord);
			} else if (tileAboveOrBelow(board, space)){
				firstSpace = findFirstSpaceAbove(board, space.getRow(board), space.getCol(board));
				addWordAbove(board, firstSpace, builtWord);
				System.out.println(firstSpace);
			}
	}
	public static String wordAndScore(IDeque<Space> builtWord) {
		StringBuilder sb = new StringBuilder();
		int score = 0;
		int size = builtWord.getSize();
		for (int i = 0; i < size; i++) {
			sb.append(builtWord.peekFirst().getTile().getFace());
			score += builtWord.dequeueFirst().getTile().getVal();
		}
		return (sb.toString() + " " + score);
	}*/
}

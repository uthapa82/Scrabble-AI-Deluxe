package edu.mccc.cos210.fp.said;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;

import edu.mccc.cos210.demo.anagrams.Anagrams;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IOrderedList;
import edu.mccc.cos210.ds.IVector;
import edu.mccc.cos210.ds.OrderedList;
import edu.mccc.cos210.ds.Vector;
import edu.mccc.cos210.ds.ISortedList;
import edu.mccc.cos210.ds.SortedList;

public class Test  {
	IOrderedList<String> dictionary = new OrderedList<>();
    IOrderedList<String> theList = new OrderedList<>();
	boolean found = false;
	IArray<Character> hand = new Array<Character>(7);
	public Test() {
		initDictionary();
		getWordB();
	}
	public static void main(String... args) {
		Test test = new Test();
	}
	public void getWordB() {
		initDictionary();
		//dictionary.stream().forEach(x -> System.out.println(x));
		hand.set(0, 'H');
		hand.set(1, 'T');
		hand.set(2, 'A');
		hand.set(3, 'M');
		hand.set(4, 'D');
		hand.set(5, 'G');
		hand.set(6, 'W');
		ISortedList<Character> tileLetters = new SortedList<>();
		for (int i = 0; i < hand.getSize(); i++) {
			tileLetters.add(hand.get(i));
		}
		StringBuilder tsb = new StringBuilder();
		for (Character c : tileLetters) {
			tsb.append(c);
		}
		while (found != true && tsb.length() != 0) {
		dictionary.stream()
		.filter(x -> x.length() == tsb.length())
		.forEach(
			x -> 
			{
				ISortedList<Character> dletters = new SortedList<>();
				for (int i = 0; i < x.length(); i++) {
					dletters.add(x.charAt(i));
				}
				StringBuilder dsb = new StringBuilder();
				for (Character c : dletters) {
					dsb.append(c);
				}
				if(tsb.toString().toLowerCase().contentEquals(dsb.toString())) {
					theList.add(x);
				}
			}
				);
			if (!theList.isEmpty()) {
				found = true;
			}else {
				tsb.deleteCharAt(tsb.length() - 1);
			}
		}
		theList.stream().distinct().forEach(x -> System.out.println(x));
  }
	public void initDictionary() {
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

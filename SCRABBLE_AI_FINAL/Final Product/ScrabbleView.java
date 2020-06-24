package edu.mccc.cos210.fp.said;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class ScrabbleView extends JPanel {
	private static final long serialVersionUID = 1L;
	private Board board;
	private Hand hand;
	private String message = "";
	private int hScore = 0;
	private int aiScore = 0;
	private boolean gOver = false;
	public ScrabbleView(Board board, Hand hand) {
		setBackground(Color.RED);
		setPreferredSize(new Dimension(1400, 800));
		this.board = board;
		this.hand = hand;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform gat = new AffineTransform();
		gat.translate(getWidth() / 2.0, getHeight() / 2.0);
		gat.scale(1.0, -1.0);
		Font f = new Font(Font.SANS_SERIF,Font.BOLD, 25);
		g.setFont(f);
		g.setColor(Color.black);
		g.drawString("You Spelled: " + message, 100, 755);
		g.drawString("Human: " + hScore, 880, 580);
		g.drawString("Computer: " + aiScore, 880, 650);
		if (gOver) {
			g.drawString("GAME OVER", 975, 320);
		}
		Path2D p2d = new Path2D.Double();
		for (int v = -600; v <= 0; v += 40) {
			p2d.moveTo(v, 300);
			p2d.lineTo(v, -300);
			p2d.closePath();
		}
		for (int h = -300; h <= 300; h += 40) {
			p2d.moveTo(-600, h);
			p2d.lineTo(0, h);
			p2d.closePath();
		}
		g2d.drawImage(board.getImage(), board.getX(), board.getY(), null);
		drawLogo(g2d);
		hand.drawHand(g2d);
		board.drawBoard(g2d);
		g2d.transform(gat);
		g2d.setPaint(Color.BLACK);
		BasicStroke bs = new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL); 
		g2d.setStroke(bs);
		g2d.draw(p2d);
		g2d.dispose();
	}
	public void resetHScore() {
		hScore = 0;
	}
	public void resetAIScore() {
		aiScore = 0;
	}
	public void addHScore(int score) {
		hScore += score;
	}
	public void setAIScore(int score) {
		aiScore = score;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setGOver(boolean gOver) {
		this.gOver = gOver;
	}
	public void drawLogo (Graphics2D g2d) {
	    Tile S = new Tile(1, "S");
		Tile C = new Tile(1, "C");
		Tile R = new Tile(1, "R");
		Tile A = new Tile(1, "A");
		Tile B = new Tile(1, "B");
		Tile L = new Tile(1, "L");
		Tile E = new Tile(1, "E");
		Tile I = new Tile(1, "I");
		Tile D = new Tile(2, "D");
		Tile U = new Tile(1, "U");
		Tile X = new Tile(8, "X");
		g2d.drawImage(S.getImage(), 800, 100, null);
		g2d.drawImage(C.getImage(), 840, 100, null);
		g2d.drawImage(R.getImage(), 880, 100, null);
		g2d.drawImage(A.getImage(), 920, 100, null);
		g2d.drawImage(B.getImage(), 960, 100, null);
		g2d.drawImage(B.getImage(), 1000, 100, null);
		g2d.drawImage(L.getImage(), 1040, 100, null);
		g2d.drawImage(E.getImage(), 1080, 100, null);
		g2d.drawImage(A.getImage(), 1160, 100, null);
		g2d.drawImage(I.getImage(), 1200, 100, null);		
		g2d.drawImage(D.getImage(), 920, 150, null);
		g2d.drawImage(E.getImage(), 960, 150, null);
		g2d.drawImage(L.getImage(), 1000, 150, null);
		g2d.drawImage(U.getImage(), 1040, 150, null);
		g2d.drawImage(X.getImage(), 1080, 150, null);
		g2d.drawImage(E.getImage(), 1120, 150, null);
	}
}

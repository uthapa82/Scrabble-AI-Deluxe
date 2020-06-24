
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class Draw2 {
	public Draw2() {
		initSwing();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(Draw2::new);
	}
	private void initSwing() {
		//Frame
		JFrame jf = new JFrame("Draw 2");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button1 = new JButton("P1");
		button1.setBounds(850,300,50,30);
	    jf.add(button1);
		JButton button2 = new JButton("P2");
		button2.setBounds(850,340,50,30);
	    jf.add(button2);
		JButton button3 = new JButton("P3");
		button3.setBounds(850,380,50,30);
	    jf.add(button3);
		JButton button4 = new JButton("P4");
		button4.setBounds(850,420,50,30);
	    jf.add(button4);
		JButton button5 = new JButton("Start");
		button5.setBounds(1000,480,100,30);
		button5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Scrabble jp = new Scrabble();
			}
		});
		jf.add(button5);
	    //BUTTONS
	    
		JPanel jp = new DrawPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	
		
		
	 
	}
	     
	static class DrawPanel extends JPanel {
		private Board board = new Board(100, 100);
		private Tile S = new Tile(1, "S");
		private Tile C = new Tile(1, "C");
		private Tile R = new Tile(1, "R");
		private Tile A = new Tile(1, "A");
		private Tile B = new Tile(1, "B");
		private Tile L = new Tile(1, "L");
		private Tile E = new Tile(1, "E");
		private static final long serialVersionUID = 1L;
		public DrawPanel() {
			setBackground(Color.RED);
			setPreferredSize(new Dimension(1400, 800));
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform gat = new AffineTransform();
			gat.translate(getWidth() / 2.0, getHeight() / 2.0);
			gat.scale(1.0, -1.0);

			//Scrabble Board
			Path2D p2d = new Path2D.Double();
			//Vertical Lines
			for (int v = -600; v <= 0; v += 40) {
				p2d.moveTo(v, 300);
				p2d.lineTo(v, -300);
				p2d.closePath();
			}
			//Horizontal Lines
			for (int h = -300; h <= 300; h += 40) {
				p2d.moveTo(-600, h);
				p2d.lineTo(0, h);
				p2d.closePath();
			}
			
			
			g2d.drawImage(board.getImage(), board.getX(), board.getY(), null);
			g2d.drawImage(S.getImage(), 100, 100, null);
			g2d.drawImage(C.getImage(), 140, 100, null);
			g2d.drawImage(R.getImage(), 180, 100, null);
			g2d.drawImage(A.getImage(), 220, 100, null);
			g2d.drawImage(B.getImage(), 260, 100, null);
			g2d.drawImage(B.getImage(), 300, 100, null);
			g2d.drawImage(L.getImage(), 340, 100, null);
			g2d.drawImage(E.getImage(), 380, 100, null);
			g2d.transform(gat);
			g2d.setPaint(Color.BLACK);
			BasicStroke bs = new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
			g2d.setStroke(bs);
			g2d.draw(p2d);
			
			Bag bag = new Bag();
			System.out.print(bag);
			
			
		}
		
	}
}

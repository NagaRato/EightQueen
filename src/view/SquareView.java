package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import model.Board;


public class SquareView extends JPanel implements Viewer {

	private Point point;
	private Board board;
	private int border;

	public SquareView(Point point, Board board) {
		super();
		this.point = point;
		this.board = board;
		board.getViewers().add(this);
		this.border = 15;
		if ((point.getX() + point.getY()) % 2 == 0) {
			this.setBackground(Color.black);
		}
		else {
			this.setBackground(Color.white);
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    	if (board.hasQueen((int) point.getX(), (int) point.getY())) {
			g.setColor(Color.RED);
			g.fillRect(border, border, getWidth()-border*2, getHeight()-border*2);
		}
    }
	
	public void refresh() {
		repaint();
	}
}
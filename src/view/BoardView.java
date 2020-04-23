package view;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;

import model.Board;

public class BoardView extends JPanel implements Viewer {
		
	
	public BoardView(Board board) {
		this.setLayout(new GridLayout(board.getWidth(), board.getHeight()));
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				add(new SquareView(new Point(x, y), board));
			}
		}
	}

	public void refresh() {
	}
}
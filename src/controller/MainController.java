package controller;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import model.Board;

import view.BoardView;
import view.DashboardView;

public class MainController {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public MainController() {
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("eightQueen");
		mainFrame.setSize(623, 400);
		mainFrame.setLayout(new BorderLayout());

		Board board = new Board(8, 8);

		BoardView boardView = new BoardView(board);
		board.getViewers().add(boardView);
		mainFrame.add(boardView, BorderLayout.CENTER);

		DashboardView dashboardView = new DashboardView(board);
		board.getViewers().add(dashboardView);
		board.NotifyViewers();
		mainFrame.add(dashboardView, BorderLayout.WEST);

		mainFrame.setVisible(true);
	}
}
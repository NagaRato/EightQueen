package view;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Board;
import model.Queen;

public class DashboardView extends JPanel implements Viewer, ActionListener {
	
	private Board model;
	
	private JButton butAddQueenByBacktracking;
	private JButton butClear;
	private JLabel lblContainsThreating;
	private JLabel lblAddQueenIsNotPossible;
	private JButton butMoveLastQueen;
	private JLabel lblBoardHasSolution;
	private JLabel lblCounter;
	private JButton butPlay;
	private JButton butCreateRandom;
	private JButton butCreateRandomUntilSolution;

	public DashboardView(Board board) {
		super();
		model = board;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		butAddQueenByBacktracking = new JButton("Plaats koningin door backtracking");
		butAddQueenByBacktracking.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(butAddQueenByBacktracking);
		butAddQueenByBacktracking.addActionListener(this);
		
		butClear = new JButton("Begin opnieuw");
		butClear.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(butClear);
		butClear.addActionListener(this);

		butMoveLastQueen = new JButton("Schuif laatste koningin eentje op");
		butMoveLastQueen.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(butMoveLastQueen);
		butMoveLastQueen.addActionListener(this);
		
		lblContainsThreating = new JLabel("");
		lblContainsThreating.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblContainsThreating);
		
		lblAddQueenIsNotPossible = new JLabel("");
		lblAddQueenIsNotPossible.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblAddQueenIsNotPossible);
		
		lblBoardHasSolution = new JLabel("");
		lblBoardHasSolution.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblBoardHasSolution);
		
		lblCounter = new JLabel("");
		lblCounter.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblCounter);
		
		butPlay = new JButton("Toon een oplossing");
		butPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(butPlay);
		butPlay.addActionListener(this);
		
		butCreateRandom = new JButton("Probeer willekeurige opstelling");
		butCreateRandom.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(butCreateRandom);
		butCreateRandom.addActionListener(this);
		
		butCreateRandomUntilSolution = new JButton("Probeer willekeurig tot oplossing");
		butCreateRandomUntilSolution.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(butCreateRandomUntilSolution);
		butCreateRandomUntilSolution.addActionListener(this);
	}
	
	public void refresh() {
		if (model.containsThreating()) {
			lblContainsThreating.setText("koningin(en) bedreigd");
			butAddQueenByBacktracking.setEnabled(false);
			butMoveLastQueen.setEnabled(true);
		}
		else {
			lblContainsThreating.setText("");
			butAddQueenByBacktracking.setEnabled(true);
			butMoveLastQueen.setEnabled(false);
		}
		if (model.containsSolution()) {
			lblBoardHasSolution.setText("Dit is een oplossing");
			butAddQueenByBacktracking.setEnabled(false);
			butMoveLastQueen.setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butAddQueenByBacktracking) {
			
			if (!model.addQueenByBacktracking()) {
				lblAddQueenIsNotPossible.setText("geen plaats voor nog een koningin");
				butAddQueenByBacktracking.setEnabled(false);
				butMoveLastQueen.setEnabled(true);			
			}
			lblCounter.setText(model.getTries() + "");
		}
		else if (e.getSource() == butMoveLastQueen) {
			model.moveLastQueen();
			lblAddQueenIsNotPossible.setText("");
			lblCounter.setText(model.getTries() + "");
		}
		else if (e.getSource() == butClear) {
			model.removeAllQueens();
			model.resetTries();
			lblAddQueenIsNotPossible.setText("");
			lblBoardHasSolution.setText("");
			lblCounter.setText(model.getTries() + "");
		}
		else if (e.getSource() == butPlay) {
			while (!model.containsSolution()) {
				if (!model.addQueenByBacktracking()) {
					model.moveLastQueen();	
				}				
			}
			lblCounter.setText(model.getTries() + "");
		}
		else if (e.getSource() == butCreateRandom) {
			model.createRandom();
			lblCounter.setText(model.getTries() + "");
		}
		else if (e.getSource() == butCreateRandomUntilSolution) {
			while(!model.containsSolution()) {
				model.createRandom();
			}
			lblCounter.setText(model.getTries() + "");
		}
		
	}
}
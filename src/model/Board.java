package model;

import java.awt.Point;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.Random;

public class Board extends Model {

	public Board(int height, int width) {
		super();
		this.queens = new ArrayList<Queen>();
		this.height = height;
		this.width = width;
		this.tries = 0;
	}

	private ArrayList<Queen> queens;
	private int height;
	private int width;
	private int tries;

	public boolean hasQueen(int x, int y) {
		boolean hasQueen = false;
		for (Queen queen : queens) {
			if (queen.getX() == x && queen.getY() == y) {
				hasQueen = true;
			}
		}
		return hasQueen;
	}

	public void removeAllQueens() {
		queens.clear();
		NotifyViewers();
	}

	public void createRandom() {
		removeAllQueens();
		Random rand = new Random();
		for (int i = 0; i < width; i++) {
			addQueen(new Queen(i, rand.nextInt(height))	);
		}
		tries++;
	}

	public boolean containsThreating() {
		boolean anyQueenIsThreatable = false;
		for (Queen threatingQueen : queens) {
			for (Queen threatableQueen : queens) {
				if (threatingQueen != threatableQueen) {
					if (threatingQueen.threatens(threatableQueen)) {
						anyQueenIsThreatable = true;
					}
				}
			}
		}
		return anyQueenIsThreatable;
	}

	public void addQueen(Queen queen) {
		queens.add(queen);
		NotifyViewers();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Point getNextFreeSquare() {
		Point nextFreeSquare = null;
		try {
			int column = getFirstFreeColumn();
			int row = getFirstFreeSquareInColumn(column);
			nextFreeSquare = new Point(column, row);
		} catch (IllegalStateException e) {
			// TODO: handle exception
		}
		return nextFreeSquare;
	}

	private int getFirstFreeSquareInColumn(int column) {
		Queen queenToAdd = new Queen(column, 0);
		for (int y = 0; y < height; y++) {
			if (isQueenThreaten(queenToAdd) && queenToAdd.getY() < height - 1) {
				queenToAdd.move();
			} else if (isQueenThreaten(queenToAdd)) {
				throw new IllegalStateException("Geen plaats in de rij voor een nieuwe koningin");
			}
		}
		return queenToAdd.getY();
	}

	private boolean isQueenThreaten(Queen queenToAdd) {
		boolean isThreaten = false;
		if (queens.size() > 0) {
			for (Queen queen : queens) {
				if (queen.threatens(queenToAdd) && !isThreaten) {
					isThreaten = true;
				}
			}
		}
		return isThreaten;
	}

	private int getFirstFreeColumn() throws IllegalStateException {
		int firstFreeColumn;
		if (queens.size() == 0) {
			firstFreeColumn = 0;
		} else if (queens.size() < width) {
			firstFreeColumn = queens.size();
		} else {
			throw new IllegalStateException("Geen plaats op het bord voor een nieuwe koningin");
		}
		return firstFreeColumn;
	}

	public void moveLastQueen() {
		if (queens.get(queens.size() - 1).getY() + 1 < height) {
			queens.get(queens.size() - 1).move();
			if (containsThreating()) {
				moveLastQueen();
			}
		} else {
			removeLastQueen();
			moveLastQueen();
		}
		NotifyViewers();
	}

	private void removeLastQueen() {
		queens.remove(queens.size() - 1);
	}

	public boolean containsSolution() {
		return queens.size() == width && !containsThreating(); // 1-5-8-6-3-7-2-4 is bijvoorbeeld een oplossing
	}

	public boolean addQueenByBacktracking() {
		Point nextFreeSquare = getNextFreeSquare();
		if (nextFreeSquare != null) {
			addQueen(new Queen((int) nextFreeSquare.getX(), (int) nextFreeSquare.getY()));
		} else {
			tries++;
		}
		return nextFreeSquare != null;
	}

	public int getTries() {
		return tries;
	}

	public void resetTries() {
		tries = 0;
	}
}
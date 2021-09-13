package com.vhais;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		char[][] board = new char[][] {
				{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
				{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};
		solveSudoku(board);
		for (int row = 0; row < NUMBER_SIZE; row++) {
			for (int col = 0; col < NUMBER_SIZE; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private static final int NUMBER_SIZE = 9;
	private static final Set<Character> NUMBERS = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
	private static List<Set<Character>> rows, cols, boxes;

	public static void solveSudoku(char[][] board) {
		rows = new ArrayList<>();
		cols = new ArrayList<>();
		boxes = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			rows.add(new HashSet<>());
			cols.add(new HashSet<>());
			boxes.add(new HashSet<>());
		}
		for (int row = 0; row < NUMBER_SIZE; row++) {
			for (int col = 0; col < NUMBER_SIZE; col++) {
				if (board[row][col] != '.') addNumber(board[row][col], row, col);
			}
		}
		solve(board, 0, 0);
	}

	public static boolean solve(char[][] board, int rowStart, int colStart) {
		if (colStart >= NUMBER_SIZE) {
			colStart = 0;
			rowStart++;
		}
		int row = rowStart, col = colStart;
		while (row < NUMBER_SIZE && board[row][col] != '.') {
			col++;
			if (col >= NUMBER_SIZE) {
				col = 0;
				row++;
			}
		}
		if (row >= NUMBER_SIZE) {
			return true;
		}
		for (char num : getValidNumbers(row, col)) {
			board[row][col] = num;
			addNumber(num, row, col);
			if (solve(board, row, col + 1)) return true;
			removeNumber(num, row, col);
		}
		board[row][col] = '.';
		return false;
	}

	public static List<Character> getValidNumbers(int row, int col) {
		List<Character> result = new ArrayList<>();
		for (char number : NUMBERS) {
			if (isValidSudoku(number, row, col)) {
				result.add(number);
			}
		}
		return result;
	}

	public static boolean isValidSudoku(char number, int row, int col) {
		return !rows.get(row).contains(number) &&
				!cols.get(col).contains(number) &&
				!boxes.get(box(row, col)).contains(number);
	}

	public static int box(int row, int col) {
		return (row / 3) * 3 + col / 3;
	}

	public static void addNumber(char num, int row, int col) {
		rows.get(row).add(num);
		cols.get(col).add(num);
		boxes.get(box(row, col)).add(num);
	}

	public static void removeNumber(char num, int row, int col) {
		rows.get(row).remove(num);
		cols.get(col).remove(num);
		boxes.get(box(row, col)).remove(num);
	}
}

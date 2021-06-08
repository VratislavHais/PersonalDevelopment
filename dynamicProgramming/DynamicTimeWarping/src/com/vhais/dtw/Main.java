package com.vhais.dtw;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		final int[] sample = new int[] {1, 2, 3, 5, 5, 5, 6};
		final int[] test = new int[] {1, 1, 2, 2, 3, 5};
		final int[][] combinationsTable = generateCombinationsTable(sample, test);
		System.out.print("Matched signal:");
		matchedSignal(combinationsTable, sample, test).forEach(s -> System.out.print(" " + s));
		System.out.println();
		printTable(combinationsTable);
	}

	private static int[][] generateCombinationsTable(int[] sample, int[] test) {
		final int[][] combinationsTable = new int[sample.length + 1][test.length + 1];
		// initialize the table
		// if both sample and test are empty we can initialize with 0
		// if sample or test is empty (not both) than we have infinity as we will never match emptiness
		for (int row = 0; row <= sample.length; row++) {
			if (row == 0) combinationsTable[0][0] = 0;
			else combinationsTable[row][0] = Integer.MAX_VALUE; // represents infinity
		}
		for (int col = 1; col <= test.length; col++) {
			combinationsTable[0][col] = Integer.MAX_VALUE;
		}
		// now we initialize the rest of the values
		// we calculate the distance between two points: d(x, y) = | x - y |
		// value in table represents the optimal distance between two points
		for (int row = 1; row <= sample.length; row++) {
			for (int col = 1; col <= test.length; col++) {
				combinationsTable[row][col] = difference(sample[row-1], test[col-1]) + minimum(
						combinationsTable[row-1][col-1],
						combinationsTable[row][col-1],
						combinationsTable[row-1][col]
				);
			}
		}
		return combinationsTable;
	}

	private static List<Integer> matchedSignal(int[][] combinationsTable, int[] signal, int[] test) {
		final Stack<Integer> stack = new Stack<>();
		final List<Integer> result = new ArrayList<>();
		// no need to subtract 1 because our combinationsTable is 1 larger due to "null" signals
		int row = signal.length;
		int col = test.length;
		int diagonal;
		int left;
		int top;
		while (row > 0 && col > 0) {
			stack.push(signal[row-1]);
			diagonal = combinationsTable[row-1][col-1];
			left = combinationsTable[row][col-1];
			top = combinationsTable[row-1][col];
			if (diagonal <= top && diagonal <= left) {
				row--; col--;
			} else if (top <= diagonal && top <= left) {
				row--;
			} else {
				col--;
			}
		}
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private static int difference(int x, int y) {
		return Math.abs(x - y);
	}

	private static int minimum(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}

	private static void printTable(int[][] combinations) {
		final StringBuilder sb = new StringBuilder();
		for (int row = 0; row < combinations.length; row++) {
			sb.append("+");
			for (int col = 0; col < combinations[0].length; col++) {
				sb.append("---+");
			}
			sb.append("\n");
			for (int col = 0; col < combinations[0].length; col++) {
				if (col == 0) {
					sb.append("|");
				}
				if (combinations[row][col] == Integer.MAX_VALUE) {
					sb.append("inf|");
				} else if (combinations[row][col] / 10 > 0) {
					sb.append(combinations[row][col] + " |");
				} else {
					sb.append(" " + combinations[row][col] + " |");
				}
			}
			sb.append("\n");
		}
		sb.append("+");
		for (int col = 0; col < combinations[0].length; col++) {
			sb.append("---+");
		}
		System.out.println(sb.toString());
	}
}

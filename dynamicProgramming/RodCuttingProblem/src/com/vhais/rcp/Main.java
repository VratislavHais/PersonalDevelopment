package com.vhais.rcp;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		final int rodLength = 9;
		final int[] prices = new int[] {1, 5 , 8, 9, 10, 17, 17, 20};
		final int[][] combinationMatrix = generateCombinationMatrix(rodLength, prices);
		getBestCombination(combinationMatrix, prices, rodLength).forEach(System.out::println);
		printTable(combinationMatrix);
	}

	private static int[][] generateCombinationMatrix(int rodLength, int[] prices) {
		final int[][] combinationMatrix = new int[prices.length][rodLength+1];
		// initialize with 0 length rod
		for (int row = 0; row < prices.length; row++) {
			combinationMatrix[row][0] = 0;
		}
		// initialize for the cutted size of 1
		for (int row = 0; row < prices.length; row++) {
			for (int lenOfRod = 1; lenOfRod <= rodLength; lenOfRod++) {
				if (row == 0) {
					combinationMatrix[row][lenOfRod] = lenOfRod * prices[row];
				} else if (lenOfRod > row) {
					combinationMatrix[row][lenOfRod] = Math.max(combinationMatrix[row-1][lenOfRod], prices[row] + combinationMatrix[row][lenOfRod-row-1]);
				} else {
					combinationMatrix[row][lenOfRod] = combinationMatrix[row-1][lenOfRod];
				}
			}
		}
		return combinationMatrix;
	}

	private static List<Integer> getBestCombination(int[][] combinationMatrix, int[] prices, int length) {
		final List<Integer> result = new ArrayList<>();
		int i = prices.length - 1;
		int j = length;
		while (i > 0 && j > 0) {
			if (combinationMatrix[i][j] == combinationMatrix[i-1][j]) {
				i--;
			} else {
				result.add(i+1);
				j -= (i + 1);
			}
		}
		return result;
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
				if (combinations[row][col] / 10 > 0) {
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

package com.vhais.lcs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		final int[] coins = new int[] {1, 5, 6, 8};
		final int total = 11;
		final int[][] combinationsTable = generateCombinationsTable(coins, total);
		System.out.println(String.format("To get %s in total we need at least %s coins.", total, combinationsTable[coins.length - 1][total]));
		System.out.print("Combination of coins to get the total:");
		getCoinsForTotal(coins, combinationsTable, total).forEach(coin -> System.out.print(" " + coin));
		System.out.println();
		printTable(combinationsTable);
	}

	private static int[][] generateCombinationsTable(int[] coins, int total) {
		// total + 1 because 0 is also a possible value
		final int[][] combinationsTable = new int[coins.length][total + 1];
		// initialize first column - how many coins are needed for total = 0
		for (int row = 0; row < coins.length; row++) {
			combinationsTable[row][0] = 0;
		}
		// initialize first row - how many coins of lowest value are needed to get total
		for (int totalInTable = 1; totalInTable < total + 1; totalInTable++) {
			if (totalInTable % coins[0] == 0) {
				combinationsTable[0][totalInTable] = totalInTable / coins[0];
			} else {
				combinationsTable[0][totalInTable] = 0;
			}
		}
		// now to initialize the rest of the table
		for (int row = 1; row < coins.length; row++) {
			for (int totalInTable = 0; totalInTable < total + 1; totalInTable++) {
				if (totalInTable < coins[row]) {
					combinationsTable[row][totalInTable] = combinationsTable[row-1][totalInTable];
				} else {
					combinationsTable[row][totalInTable] = Math.min(combinationsTable[row-1][totalInTable], 1 + combinationsTable[row][totalInTable - coins[row]]);
				}
			}
		}
		return combinationsTable;
	}

	private static List<Integer> getCoinsForTotal(int[] coins, int[][] combinations, int total) {
		int i = coins.length - 1;
		int totalRemaining = total;
		int numerOfCoins = combinations[combinations.length - 1][combinations[0].length - 1];
		final List<Integer> coinsToCombine = new ArrayList<>();
		while (totalRemaining > 0) {
			if (combinations[i-1][totalRemaining] == numerOfCoins) {
				i--;
			} else {
				coinsToCombine.add(coins[i]);
				totalRemaining -= coins[i];
				numerOfCoins--;
			}
		}
		return coinsToCombine;
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
				} else sb.append(" " + combinations[row][col] + " |");
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

package com.vhais.lcs;

import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException("Program takes two parameters");
		}
		final String s1 = args[0];
		final String s2 = args[1];
		final int[][] combinations = generateCombinations(s1, s2);
		System.out.println("Length of longest common subsequence is: " + combinations[combinations.length - 1][combinations[0].length - 1]);
		System.out.println(getLongestCommonSubsequence(combinations, s1, s2));
		printTable(combinations);
	}

	private static int[][] generateCombinations(String s1, String s2) {
		// incrementing size because we need to include also combinations when one string is empty
		final int[][] combinations = new int[s1.length() + 1][s2.length() + 1];
		// fill first row and column with zeros because we have only one string with any characters
		for (int row = 0; row < s1.length(); row++) {
			combinations[row][0] = 0;
		}
		for (int col = 0; col < s2.length(); col++) {
			combinations[0][col] = 0;
		}
		// no need to go from 0 again as this row/column is pre-filled
		for (int row = 1; row <= s1.length(); row++) {
			for (int col = 1; col <= s2.length(); col++) {
				if (s1.charAt(row - 1) != s2.charAt(col - 1)) {
					combinations[row][col] = Math.max(combinations[row - 1][col], combinations[row][col - 1]);
				} else {
					combinations[row][col] = combinations[row - 1][col - 1] + 1;
				}
			}
		}
		return combinations;
	}

	private static String getLongestCommonSubsequence(int[][] combinations, String s1, String s2) {
		final StringBuilder result = new StringBuilder();
		final Stack<Character> stack = new Stack<>();
		int row = combinations.length - 1;
		int col = combinations[0].length - 1;
		while (row > 0 && col > 0) {
			// subtract 1 from row and col because our table is increased by 1 during creation to also include when
			// one string is empty
			boolean charactersEquals = s1.charAt(row-1) == s2.charAt(col-1);
			if (combinations[row][col] - 1 == combinations[row-1][col-1] && charactersEquals) {
				stack.push(s1.charAt(row-1));
				row--; col--;
			} else if (combinations[row][col-1] == combinations[row][col]) {
				col--;
			} else {
				row--;
			}
		}
		while (!stack.isEmpty()) {
			result.append(stack.pop());
		}
		return result.toString();
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
				sb.append(" " + combinations[row][col] + " |");
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

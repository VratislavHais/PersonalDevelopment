package com.vhais.lps;

public class Main {

	public static void main(String[] args) {
		final String s = "agbdba";
		final int[][] matrix = generateCombinationMatrix(s);
		printTable(matrix);
		System.out.println(matrix[0][s.length() - 1]);
	}

	private static int[][] generateCombinationMatrix(String s) {
		final int[][] matrix = new int[s.length()][s.length()];
		// initialize
		for (int i = 0; i < s.length(); i++) {
			matrix[i][i] = 1;
		}
		// string of length 2
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i+1)) {
				matrix[i][i+1] = 2;
			} else {
				matrix[i][i+1] = 1;
			}
		}
		int length = 3;
		while (length <= s.length()) {
			for (int i = 0; i <= s.length() - length; i++) {
				int j = i + length - 1;
				if (s.charAt(i) == s.charAt(j)) {
					matrix[i][j] = 2 + matrix[i+1][j-1];
				} else {
					matrix[i][j] = Math.max(matrix[i+1][j], matrix[i][j-1]);
				}
			}
			length++;
		}
		return matrix;
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

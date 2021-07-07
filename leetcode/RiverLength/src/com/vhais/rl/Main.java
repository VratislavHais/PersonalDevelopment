package com.vhais.rl;

import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final List<String> visited = new ArrayList<>();

	public static void main(String[] args) {
		final int[][] matrix = new int[][] {
				{1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0},
				{1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0},
				{0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1},
				{1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
				{1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1}
		};
		final List<Integer> result = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					int current = getRiverLength(matrix, i, j);
					if (current > 0) result.add(current);
				}
			}
		}
		result.forEach(System.out::println);
	}

	private static int getRiverLength(int[][] matrix, int xPos, int yPos) {
//		System.out.println("xPos: " + xPos + " yPos: " + yPos + " len: " + matrix[0].length);
		final String key = xPos + "," + yPos;
		if (visited.contains(key)) return 0; // already calculated, skip
		else visited.add(key);
		if (xPos >= matrix.length || yPos >= matrix[0].length || xPos < 0 || yPos < 0) return 0;
		else if (matrix[xPos][yPos] == 0) return 0;
		return 1 + getRiverLength(matrix, xPos + 1, yPos) + getRiverLength(matrix, xPos, yPos + 1) + getRiverLength(matrix, xPos, yPos - 1) + getRiverLength(matrix, xPos-1, yPos);
	}
}

package com.vhais.fwa;

public class Main {

	public static void main(String[] args) {
		final Double[][] distanceMatrix = new Double[][] {
				{0d, 3d, 6d, 15d},
				{Double.POSITIVE_INFINITY, 0d, -2d, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0d, 2d},
				{1d, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0d}
		};
		final Integer[][] pathsMatrix = new Integer[][] {
				{null, 1, 1, 1},
				{null, null, 2, null},
				{null, null, null, 3},
				{4, null, null, null}
		};
		final int[] vertices = new int[] {1, 2, 3, 4};
		performFloydWarshall(distanceMatrix, pathsMatrix);
		printTable(pathsMatrix);
		printTable(distanceMatrix);
	}

	// void because we are passing pointer as an argument and therefore the changes are present int the object
	private static void performFloydWarshall(Double[][] distanceMatrix, Integer[][] pathsMatrix) {
		for (int i = 0; i < distanceMatrix.length; i++) {
			for (int j = 0; j < distanceMatrix.length; j++) {
				for (int k = 0; k < distanceMatrix.length; k++) {
					if (distanceMatrix[i][j] > distanceMatrix[i][k] + distanceMatrix[k][j]) {
						distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
						pathsMatrix[i][j] = pathsMatrix[k][j];
					}
				}
			}
		}
	}

	private static <T> void printTable(T combinations[][]) {
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

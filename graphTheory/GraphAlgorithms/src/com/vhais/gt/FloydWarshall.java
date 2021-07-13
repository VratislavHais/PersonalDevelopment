package com.vhais.gt;

public class FloydWarshall {
	private final double[][] matrix;
	private final int[][] next;
	private boolean solved = false;
	private final int pathRanges = 50;

	public FloydWarshall(int numberOfNodes) {
		matrix = new double[numberOfNodes][numberOfNodes];
		next = new int[numberOfNodes][numberOfNodes];
		for (int i = 0; i < numberOfNodes; i++) {
			for (int j = 0; j < numberOfNodes; j++) {
				if (i == j) matrix[i][j] = 0;
				else matrix[i][j] = Double.POSITIVE_INFINITY;
				next[i][j] = -1;
			}
		}
	}

	public double[][] solve() {
		if (solved) return matrix;
		for (int k = 1; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}
		for (int k = 1; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = Double.NEGATIVE_INFINITY;
						next[i][j] = -1;
					}
				}
			}
		}
		solved = true;
		return matrix;
	}

	public int[][] getPaths() {
		if (!solved) solve();
		return next;
	}

	public void addEdge(int from, int to, int cost) {
		matrix[from][to] = cost;
		next[from][to] = to;
	}

	public void printGraphMatrix() {
		final String delimiter = "     ";
		for (double[] doubles : matrix) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(doubles[j] + delimiter.substring(getNumberOfDigits(doubles[j])));
			}
			System.out.println();
		}
	}

	public void printPaths() {
		for (int i = 0; i < next.length; i++) {
			for (int j = 0; j < next.length; j++) {
				if (i == j) continue;
//				if (next[i][j] == -1) System.out.println(String.format("No path exists between %s and %s", i, j));
				if (next[i][j] != -1) {
					System.out.print(String.format("Shortest path from %s to %s: ", i, j));
					int k = next[i][j];
					System.out.print(i + " -> ");
					while (k != j) {
						System.out.print(k + " -> ");
						k = next[k][j];
					}
					System.out.println(j);
				}
			}
		}
	}

	private int getNumberOfDigits(Double number) {
		if (number.isInfinite()) return 3;
		number = Math.abs(number);
		int count = 1;
		while ((number = Math.floor(number / 10)) != 0) count++;
		return count;
	}
}

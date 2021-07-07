package com.vhais.reshape;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
				{1, 2},
				{3, 4}
		};
		Arrays.stream(matrixReshape(matrix, 2, 4)).forEach(row -> {
			Arrays.stream(row).forEach(i -> System.out.print(i + " "));
			System.out.println();
		});
	}

	public static int[][] matrixReshape(int[][] mat, int r, int c) {
		if (r * c != mat.length * mat[0].length) return mat;
		int[][] newMat = new int[r][c];
		int rowOld = -1, rowNew = -1;
		for (int index = 0; index < r * c; index++) {
			if (index % c == 0) rowNew++;
			if (index % mat[0].length == 0) rowOld++;
			newMat[rowNew][index % c] = mat[rowOld][index % mat[0].length];
		}
		return newMat;
	}
}

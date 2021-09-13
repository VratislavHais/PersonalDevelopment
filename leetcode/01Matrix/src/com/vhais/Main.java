package com.vhais;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		int[][] mat = new int[][] {
				{0,0,0},
				{0,1,0},
				{1,1,1}
		};
		var result = updateMatrix(mat);
		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result[0].length; col++) {
				System.out.print(result[row][col] + " ");
			}
			System.out.println();
		}
	}

	public static int[][] updateMatrix(int[][] mat) {
		int[][] result = new int[mat.length][mat[0].length];
		int top, left;
		final int INF = Integer.MAX_VALUE >> 1;
		for (int row = 0; row < mat.length; row++) {
			for (int col = 0; col < mat[0].length; col++) {
				if (mat[row][col] > 0) {
					top = INF;
					left = INF;
					if (row > 0) {
						top = result[row-1][col];
					}
					if (col > 0) {
						left = result[row][col-1];
					}
					result[row][col] = 1 + Math.min(top, left);
				}
			}
		}
		int right, bottom;
		for (int row = mat.length - 1; row >= 0; row--) {
			for (int col = mat[0].length - 1; col >= 0 ; col--) {
				if (mat[row][col] > 0) {
					right = INF;
					bottom = INF;
					if (row + 1 < mat.length) {
						bottom = result[row+1][col];
					}
					if (col + 1 < mat[0].length) {
						right = result[row][col+1];
					}
					result[row][col] = Math.min(result[row][col], Math.min(right, bottom) + 1);
				}
			}
		}
		return result;
	}
}

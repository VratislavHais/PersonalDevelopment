package com.vhais.max.sum.rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		int[][] matrix2 = new int[][] {{27,5,-20,-9,1,26,1,12,7,-4,8,7,-1,5,8},
				{16,28,8,3,16,28,-10,-7,-5,-13,7,9,20,-9,26},
				{24,-14,20,23,25,-16,-15,8,8,-6,-14,-6,12,-19,-13},
				{28,13,-17,20,-3,-18,12,5,1,25,25,-14,22,17,12},
				{7,29,-12,5,-5,26,-5,10,-5,24,-9,-19,20,0,18},
				{-7,-11,-8,12,19,18,-15,17,7,-1,-11,-10,-1,25,17},
				{-3,-20,-20,-7,14,-12,22,1,-9,11,14,-16,-5,-12,14},
				{-20,-4,-17,3,3,-18,22,-13,-1,16,-11,29,17,-2,22},
				{23,-15,24,26,28,-13,10,18,-6,29,27,-19,-19,-8,0},
				{5,9,23,11,-4,-20,18,29,-6,-4,-11,21,-6,24,12},
				{13,16,0,-20,22,21,26,-3,15,14,26,17,19,20,-5},
				{15,1,22,-6,1,-9,0,21,12,27,5,8,8,18,-1},
				{15,29,13,6,-11,7,-6,27,22,18,22,-3,-9,20,14},
				{26,-6,12,-10,0,26,10,1,11,-10,-16,-18,29,8,-8},
				{-19,14,15,18,-10,24,-9,-7,-19,-14,23,23,17,-5,6}};
		int[][] matrix = new int[][] {
				{2, 2, -1},
		};
		int[][] matrix3 = new int[][] {
				{1, 0, 1},
				{0, -2, 3}
		};
		System.out.println(maxSumSubmatrix(matrix2, -100));
	}

	public static int maxSumSubmatrix(int[][] matrix, int k) {
		int result = Integer.MIN_VALUE;
		int[] columnSums = new int[matrix[0].length];

		for (int startWindow = 0; startWindow < matrix.length; startWindow++) {
			Arrays.fill(columnSums, 0);
			for (int endWindow = startWindow; endWindow < matrix.length; endWindow++) {
				for (int column = 0; column < matrix[0].length; column++) {
					columnSums[column] += matrix[endWindow][column];
				}
				result = checkSums(columnSums, result, k);
				if (result == k) return result;
			}
		}
		return result;
	}

	private static int checkSums(int[] columnSums, int result, int k) {
		TreeSet<Integer> prefixedSums = new TreeSet<>();
		prefixedSums.add(0);
		int sum = 0;
		for (int column : columnSums) {
			sum += column;
			// Integer because it can be null
			Integer x = prefixedSums.ceiling(sum - k);

			if (x != null) result = Math.max(result, sum - x);
			if (result == k) return result;
			prefixedSums.add(sum);
		}

		return result;
	}

}

package com.vhais.kth.element;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
				{1,5,9},
				{10,11,13},
				{12,13,15}
		};
//		Arrays.stream(mergeSortMatrix(matrix)).forEach(i -> System.out.print(i + " "));
		System.out.println(mergeSortMatrix(matrix)[7]);
	}

	private static int[] mergeSortMatrix(int[][] matrix) {
		if (matrix.length == 1) return matrix[0];
		int[] result = new int[0];
		for (int row = 0; row < matrix.length; row++) {
			result = mergeSort(result, matrix[row]);
		}
		return result;
	}

	private static int[] mergeSort(int[] array1, int[] array2) {
		int[] result = new int[array1.length + array2.length];
		int idxArr1 = 0, idxArr2 = 0;
		while (idxArr1 < array1.length || idxArr2 < array2.length) {
			if (idxArr1 >= array1.length) result[idxArr1 + idxArr2] = array2[idxArr2++];
			else if (idxArr2 >= array2.length) result[idxArr1 + idxArr2] = array1[idxArr1++];
			else result[idxArr1 + idxArr2] = array1[idxArr1] < array2[idxArr2] ? array1[idxArr1++] : array2[idxArr2++];
		}
		return result;
	}
}

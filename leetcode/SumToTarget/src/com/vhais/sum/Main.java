package com.vhais.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] { 5, 3, 6, 7, 9, 2, 4, 2, 1 };
		int target = 5;
		int[] result = findIndicesThatSumToTarget(arr, target);
		if (result != null) {
			printResult(result);
		}
	}

	public static int[] findIndicesThatSumToTarget(int[] array, int target) {
		final Map<Integer, Integer> complements = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			if (complements.containsKey(array[i])) return new int[] {i, complements.get(array[i])};
			else if (!complements.containsKey(target - array[i])) complements.put(target - array[i], i);
		}
		return null;
	}

	public static void printResult(int[] result) {
		Arrays.stream(result).forEach(i -> System.out.print(i + " "));
	}
}

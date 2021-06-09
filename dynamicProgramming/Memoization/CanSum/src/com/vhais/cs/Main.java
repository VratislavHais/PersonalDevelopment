package com.vhais.cs;

import java.util.HashMap;
import java.util.Map;

/*
	Write a function `canSum(tagetSum, numbers)` that takes in a targetSum
	and an array of numbers as arguments.
	The function should return a boolean indicating whether or not it is possible
	to generate the targetSum using numbers from the array.
	You may use an element of the array as many times as needed
	You may assume that all input numbers are nonnegative.

	canSum(7, [5, 4, 3, 7]) -> true
	canSum(7, [2, 4]) -> false
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(canSum(7, new int[] {5, 4, 3, 7}));
		System.out.println(canSum(3000, new int[] {5, 4, 3, 7}));
	}

	private static boolean canSum(int targetSum, int[] numbers) {
		final Map<Integer, Boolean> cache = new HashMap<>();
		return canSum(targetSum, numbers, cache);
	}

	private static boolean canSum(int targetSum, int[] numbers, Map<Integer, Boolean> cache) {
		if (cache.get(targetSum) != null) return cache.get(targetSum);
		if (targetSum == 0) return true;
		else if (targetSum < 0) return false;
		for (int n : numbers) {
			if (canSum(targetSum - n, numbers)) {
				cache.put(targetSum, true);
				return true;
			}
		}
		cache.put(targetSum, false);
		return false;
	}
}

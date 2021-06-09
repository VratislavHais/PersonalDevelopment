package com.vhais.hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
	Write a function `howSum(targetSum, numbers)` that takes in a target sum and an array
	of numbers as arguments.
	The function should return an array containing any combination of elements that add up
	to exactly the targetSum. If there are no combination that adds up to the targetSum, then
	return null.
	If there are multiple combinations possible, you may return any single one.
 */
public class Main {

	public static void main(String[] args) {
		final List<Integer> result = howSum(30000, new int[] {7, 14});
		if (result == null) {
			System.out.println("null");
		} else {
			result.forEach(System.out::println);
		}
	}

	private static List<Integer> howSum(int targetSum, int[] numbers) {
		final Map<Integer, List<Integer>> cache = new HashMap<>();
		return howSum(targetSum, numbers, cache);
	}

	private static List<Integer> howSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> cache) {
		if (cache.containsKey(targetSum)) return cache.get(targetSum);
		if (targetSum == 0) return new ArrayList<>();
		else if (targetSum < 0) return null;
		for (int n : numbers) {
			final List<Integer> recursionResult = howSum(targetSum - n, numbers, cache);
			if (recursionResult != null) {
				recursionResult.add(n);
				cache.put(targetSum, recursionResult);
				return recursionResult;
			}
		}
		cache.put(targetSum, null);
		return null;
	}
}

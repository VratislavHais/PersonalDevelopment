package com.vhais.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
	Write a function `bestSum(targetSum, numbers)` that takes in a targetSum and an array
	of numbers as arguments.
	The function should return an array containing the shortest combination of numbers
	that add up to exactly the targetSum.
	If there is a tie for the shortest combination, you may return any one of the shortest.
 */
public class Main {

	public static void main(String[] args) {
		bestSum(100, new int[] {5, 3, 4, 25}).forEach(i -> System.out.print(" " + i));
	}

	private static List<Integer> bestSum(int targetSum, int[] numbers) {
		final Map<Integer, List<Integer>> cache = new HashMap<>();
		return bestSum(targetSum, numbers, cache);
	}

	private static List<Integer> bestSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> cache) {
		if (cache.containsKey(targetSum)) return cache.get(targetSum) == null ? null : new ArrayList<>(cache.get(targetSum));
		if (targetSum == 0) return new ArrayList<>();
		else if (targetSum < 0) return null;
		List<Integer> shortest = null;
		for (int n : numbers) {
			List<Integer> combination = bestSum(targetSum - n, numbers, cache);
			if (combination != null) {
				combination.add(n);
				if (shortest == null || combination.size() < shortest.size()) {
					shortest = combination;
				}
			}
		}
		if (shortest != null) {
			cache.put(targetSum, new ArrayList<>(shortest));
		} else {
			cache.put(targetSum, null);
		}
		return shortest;
	}
}

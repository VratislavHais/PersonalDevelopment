package com.vhais.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {1, 0, -1, 0, -2, 2};
		Arrays.sort(arr);
		printResult(nSum(arr, 0, 4, 0));
		int[] arr2 = new int[] {2,2,2,2,2};
		Arrays.sort(arr2);
		printResult(nSum(arr2, 8, 4, 0));
		int[] arr3 = new int[] {-2,-1,-1,1,1,2,2};
		Arrays.sort(arr3);
		printResult(nSum(arr3, 0, 4, 0));
		int[] arr4 = new int[] {-5,5,4,-3,0,0,4,-2};
		Arrays.sort(arr4);
		printResult(nSum(arr4, 4, 4, 0));
	}

	private static void printResult(List<List<Integer>> result) {
		result.forEach(list -> {
			list.forEach(i -> System.out.print(i + " "));
			System.out.println();
		});
	}

	public static List<List<Integer>> nSum(int[] arr, int target, int n, int start) {
		if (n == 2) return twoSum(arr, target, start);
		List<List<Integer>> result = new ArrayList<>();
		if (start == arr.length || arr[start] * n > target || target > arr[arr.length - 1] * n) return result;
		for (int i = start; i < arr.length; i++) {
			if (i == start || arr[i] != arr[i - 1]) {
				var sumRes = nSum(arr, target - arr[i], n - 1, i + 1);
				if (!sumRes.isEmpty()) {
					for (List<Integer> list : sumRes) {
						list.add(arr[i]);
						result.add(list);
					}
				}
			}
		}
		return result;
	}

	public static List<List<Integer>> twoSum(int[] arr, int target, int start) {
		Map<Integer, Integer> dif = new HashMap<>();
		Set<Integer> used = new HashSet<>();
		List<List<Integer>> result = new ArrayList<>();
		for (int i = start; i < arr.length; i++) {
			if (dif.containsKey(arr[i]) && !used.contains(arr[i])) {
				result.add(new ArrayList<>(Arrays.asList(arr[i], arr[dif.get(arr[i])])));
				used.add(arr[i]);
			} else {
				dif.put(target - arr[i], i);
			}
		}
		return result;
	}
}

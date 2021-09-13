package com.vhais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,2};
		subsetsWithDup(nums).forEach(list -> {
			list.forEach(i -> System.out.print(i + " "));
			System.out.println();
		});
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		backtracking(new LinkedList<>(), result, nums, 0);
		return new ArrayList<>(result);
	}

	public static void backtracking(LinkedList<Integer> currentList, List<List<Integer>> result, int[] nums, int start) {
		result.add(new ArrayList<>(currentList));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i - 1] == nums[i]) continue;
			currentList.add(nums[i]);
			backtracking(currentList, result, nums, i+1);
			currentList.removeLast();
		}
	}
}

package com.vhais;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {5,0,3,8,6};
		int[] arr2 = new int[] {1,1,1,0,6,12};
		int[] arr3 = new int[] {32,57,24,19,0,24,49,67,87,87};
		System.out.println(partitionDisjoint(arr));
		System.out.println(partitionDisjoint(arr2));
		System.out.println(partitionDisjoint(arr3));
	}

	public static int partitionDisjoint(int[] nums) {
		int comparisonItem = nums[0];
		int localMax = comparisonItem;
		int partitionIdx = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < comparisonItem) {
				partitionIdx = i;
				comparisonItem = localMax;
			}
			else localMax = Math.max(localMax, nums[i]);
		}
		return partitionIdx + 1;
	}
}

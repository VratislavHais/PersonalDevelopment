package com.vhais;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {-3, -2, -5, 3, -4};
		System.out.println(threeSumClosest(arr, 1));
	}

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int hi, lo, sum, closest = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length - 2; i++) {
			hi = nums.length - 1; lo = i + 1;
			while (hi > lo) {
				sum = nums[i] + nums[hi] + nums[lo];
				if (abs(sum - target) < abs(closest - target)) closest = sum;
				if (sum < target) lo++;
				else if (sum > target) hi--;
				else return sum;
			}
		}
		return closest;
	}

	private static int abs(int number) {
		int mask = number >> 31;
		return (number ^ mask) - mask;
	}
}

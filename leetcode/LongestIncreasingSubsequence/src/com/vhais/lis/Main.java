package com.vhais.lis;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[] {1,3,6,7,9,4,10,5,6};
		System.out.println(lengthOfLIS(arr));
	}

	public static int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		int size = 0;
		for (int num : nums) {
			int i = 0, j = size, mid;
			while (i < j) {
				mid = (i + j) >> 1;
				if (num > dp[mid]) i = mid + 1;
				else if (num < dp[mid]) j = mid;
				else {
					i = mid;
					break;
				}
			}
			if (i == size) size++;
			dp[i] = num;
		}
		return size;
	}
}

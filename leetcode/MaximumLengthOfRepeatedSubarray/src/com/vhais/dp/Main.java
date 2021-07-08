package com.vhais.dp;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] ar1 = new int[] {1, 2, 3, 2, 1};
		int[] ar2 = new int[] {3, 2, 1, 4 ,7};
		System.out.println(findLength(ar1, ar2));
	}

	public static int findLength(int[] nums1, int[] nums2) {
		int result = 0;
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];
		for (int row = 0; row < dp.length; row++) {
			for (int col = 0; col < dp[0].length; col++) {
				if (row == 0 || col == 0) dp[row][col] = 0;
				else if (nums1[row - 1] != nums2[col - 1]) dp[row][col] = 0;
				else {
					dp[row][col] = dp[row-1][col-1] + 1;
					result = Math.max(dp[row][col], result);
				}
			}
		}
		return result;
	}
}

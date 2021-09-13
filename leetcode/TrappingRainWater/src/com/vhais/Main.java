package com.vhais;

public class Main {

	public static void main(String[] args) {
		int[] heights = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
		int[] heights2 = new int[] {4,2,0,3,2,5};
		int[] heights3 = new int[] {2,0,2};
		System.out.println(trap(heights));
		System.out.println(trap(heights2));
		System.out.println(trap(heights3));
	}
// O(n^2)
//	public static int trap(int[] height) {
//		int total = 0;
//		for (int i = 0; i < height.length; i++) {
//			int leftBoundary = height[i];
//			for (int j = 0; j < i; j++) {
//				leftBoundary = Math.max(height[j], leftBoundary);
//			}
//			int rightBoundary = height[i];
//			for (int j = i + 1; j < height.length; j++) {
//				rightBoundary = Math.max(height[j], rightBoundary);
//			}
//			total += (Math.min(leftBoundary, rightBoundary) - height[i]);
//		}
//		return total;
//	}

	public static int trap(int[] height) {
		int leftMax = 0, rightMax = 0, left = 0, right = height.length - 1;
		int total = 0;

		while (left < right) {
			if (height[left] <= height[right]) {
				if (leftMax < height[left]) {
					leftMax = height[left];
				} else {
					total += (leftMax - height[left]);
				}
				left++;
			} else {
				if (rightMax < height[right]) {
					rightMax = height[right];
				} else {
					total += (rightMax - height[right]);
				}
				right--;
			}
		}
		return total;
	}
}

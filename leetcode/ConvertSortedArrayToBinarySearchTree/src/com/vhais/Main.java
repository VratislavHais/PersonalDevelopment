package com.vhais;

public class Main {

	public static void main(String[] args) {
		int[] nums = {-10,-3,0,5,9};
		sortedArrayToBST(nums);
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		return createNode(0, nums.length - 1, nums);
	}

	public static TreeNode createNode(int start, int end, int[] nums) {
		if (start > end) return null;
		int mid = (start + end) >> 1;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = createNode(start, mid - 1, nums);
		root.right = createNode(mid + 1, end, nums);
		return root;
	}
}

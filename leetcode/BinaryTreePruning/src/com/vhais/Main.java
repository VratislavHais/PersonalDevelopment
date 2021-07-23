package com.vhais;

public class Main {

	public static void main(String[] args) {
	}

	public static TreeNode pruneTree(TreeNode root) {
		if (root == null) return null;
		root.left = pruneTree(root.left);
		root.right = pruneTree(root.right);
		if (isLeaf(root) && root.val != 1) {
			return null;
		}
		return root;
	}

	private static boolean isLeaf(TreeNode root) {
		return root.left == null && root.right == null;
	}
}

package com.vhais;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		TreeNode firstTree = createTree(new Integer[] {1, 3, 2, 5, 3, null, 9});				// 4
		TreeNode secondTree = createTree(new Integer[] {1,3,null,5,3});						// 2
		TreeNode thirdTree = createTree(new Integer[] {1,3,2,5});							// 2
		TreeNode fourthTree = createTree(new Integer[] {1,3,2,5,null,null,9,6,null,null,7});	// 8
		System.out.println(widthOfBinaryTree(firstTree));
		System.out.println(widthOfBinaryTree(secondTree));
		System.out.println(widthOfBinaryTree(thirdTree));
//		printTree(fourthTree);
		System.out.println(widthOfBinaryTree(fourthTree));
	}

	private static TreeNode createTree(Integer[] values) {
		int idx = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(values[idx++]);
		queue.offer(root);
		TreeNode current, left, right;
		while (!queue.isEmpty() && idx < values.length) {
			current = queue.poll();
			if (current != null) {
				left = createNodeOrNull(values[idx++]);
				right = idx < values.length ? createNodeOrNull(values[idx++]) : null;
				current.left = left;
				current.right = right;
				queue.offer(left);
				queue.offer(right);
			}
		}
		return root;
	}

	private static TreeNode createNodeOrNull(Integer value) {
		return value == null ? null : new TreeNode(value);
	}

	public static int widthOfBinaryTree(TreeNode root) {
		return dfs(root, 1, 0, new ArrayList<>());
	}

	public static int dfs(TreeNode root, int id, int depth, List<Integer> leftMostNodes) {
		if (root == null) return 0;
		if (depth >= leftMostNodes.size()) leftMostNodes.add(id);
		int maxDepthOfChildren = Math.max(
				dfs(root.left, id * 2, depth + 1, leftMostNodes),
				dfs(root.right, id * 2  + 1, depth + 1, leftMostNodes));
		return Math.max(id + 1 - leftMostNodes.get(depth), maxDepthOfChildren);
	}

	private static void printTree(TreeNode root) {
		if (root == null) return;
		System.out.println(root.val);
		printTree(root.left);
		printTree(root.right);
	}
}

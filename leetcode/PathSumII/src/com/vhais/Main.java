package com.vhais;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		TreeNode root = createTree(new Integer[] {5,4,8,11,null,13,4,7,2,null,null,5,1});
//		printTree(root);
		pathSum(root, 22).forEach(list -> {
			list.forEach(i -> System.out.print(i + " "));
			System.out.println();
		});
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

	public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		List<List<Integer>> result = new ArrayList<>();
		backtracking(new LinkedList<>(), result, root, targetSum);
		return result;
	}

	public static void backtracking(LinkedList<Integer> currentList, List<List<Integer>> result, TreeNode node, int targetSum) {
		if (node == null) {
			return;
		}
		currentList.add(node.val);
		if (node.right == null && node.left == null) {
			if (targetSum - node.val == 0) result.add(new ArrayList<>(currentList));
			currentList.removeLast();
			return;
		}
		targetSum -= node.val;
		backtracking(currentList, result, node.right, targetSum);
		backtracking(currentList, result, node.left, targetSum);
		currentList.removeLast();
	}

	private static void printTree(TreeNode root) {
		if (root == null) return;
		System.out.println(root.val);
		printTree(root.left);
		printTree(root.right);
	}
}

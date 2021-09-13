package com.vhais;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// write your code here
	}

	public static List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		traverse(result, 0, root);
		return result;
	}

	public static void traverse(List<List<Integer>> result, int level, Node root) {
		if (root == null) return;
		if (result.size() == level) result.add(new ArrayList<>());
		result.get(level).add(root.val);
		for (Node child : root.children) {
			traverse(result, level + 1, child);
		}
	}
}

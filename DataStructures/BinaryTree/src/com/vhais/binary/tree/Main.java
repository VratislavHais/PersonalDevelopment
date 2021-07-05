package com.vhais.binary.tree;

public class Main {

	public static void main(String[] args) {
//		BinaryTree<Integer> bt = new BinaryTree<>(new Integer[] {25, 14, 13, 18, 22, 20, 21, 17, 1, 3, 5, 30, 35, 23});
//		bt.remove(18);
//		bt.printTree();

		AVLTree<Integer> avlTree = new AVLTree<>(new Integer[] {25, 14, 13, 18, 22, 20, 21, 17, 1, 3, 5, 30, 35, 23});
		avlTree.remove(18);
		avlTree.printTree();
	}
}

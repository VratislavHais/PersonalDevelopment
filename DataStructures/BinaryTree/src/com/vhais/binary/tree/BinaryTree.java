package com.vhais.binary.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {
	private Node<T> root;

	public BinaryTree() {
	}

	public BinaryTree(T[] items) {
		this(Arrays.asList(items));
	}

	public BinaryTree(List<T> items) {
		for (T item : items) {
			this.insert(item);
		}
	}

	public boolean insert(T item) {
		if (contains(item)) return false;
		root = insert(root, item);
		return true;
	}

	public Node<T> insert(Node<T> leaf, T data) {
		if (leaf == null) return new Node<>(data);
		if (data.compareTo(leaf.getData()) < 0) {
			leaf.setLeftChild(insert(leaf.getLeftChild(), data));
		} else {
			leaf.setRightChild(insert(leaf.getRightChild(), data));
		}
		return leaf;
	}

	public boolean contains(T data) {
		return getNode(data) != null;
	}

	public boolean remove(T data) {
		if (contains(data)) {
			remove(root, data);
			return true;
		}
		return false;
	}

	private Node<T> remove(Node<T> currentNode, T data) {
		if (currentNode == null) return null;
		int cmp = data.compareTo(currentNode.getData());
		if (cmp == 0) {
			if (currentNode.getRightChild() == null) {
				Node<T> leftChild = currentNode.getLeftChild();
				currentNode.setData(null);
				currentNode.setLeftChild(null);
				return leftChild;
			} else if (currentNode.getLeftChild() == null) {
				Node<T> rightChild = currentNode.getRightChild();
				currentNode.setData(null);
				currentNode.setLeftChild(null);
				return rightChild;
			} else {
				Node<T> leftMost = getLeftMost(currentNode.getRightChild());
				currentNode.setData(leftMost.getData());
				remove(currentNode.getRightChild(), leftMost.getData());
			}
		} else if (cmp < 0) {
			currentNode.setLeftChild(remove(currentNode.getLeftChild(), data));
		} else {
			currentNode.setRightChild(remove(currentNode.getRightChild(), data));
		}
		return currentNode;
	}

	private Node<T> getLeftMost(Node<T> current) {
		if (current.getLeftChild() == null) return current;
		return getLeftMost(current.getLeftChild());
	}

	private Node<T> getRightMost(Node<T> root) {
		if (root.getRightChild() == null) return root;
		return getRightMost(root.getRightChild());
	}

	private Node<T> getNode(T data) {
		Node<T> result = root;
		while (result != null && !result.getData().equals(data)) {
			result = data.compareTo(result.getData()) < 0 ? result.getLeftChild() : result.getRightChild();
		}
		return result;
	}

	public void printTree() {
		final Queue<Node<T>> queue = new LinkedList<>();
		queue.offer(root);
		int cnt = 1;
		while (!queue.isEmpty()) {
			Node<T> current = queue.remove();
			cnt--;
			if (current == null) System.out.print("- ");
			else {
				System.out.print(current.getData() + " ");
				if (current.getLeftChild() != null) queue.offer(current.getLeftChild());
				else queue.offer(null);
				if (current.getRightChild() != null) queue.offer(current.getRightChild());
				else queue.offer(null);
			}
			if (cnt == 0) {
				System.out.println();
				cnt = queue.size();
			}
		}
	}
}

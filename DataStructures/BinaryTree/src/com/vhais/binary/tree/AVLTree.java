package com.vhais.binary.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {
	private Node<T> root;

	public AVLTree(T[] items) {
		this(Arrays.asList(items));
	}

	public AVLTree(List<T> items) {
		items.forEach(this::insert);
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
		update(leaf);
		return balance(leaf);
	}

	public boolean contains(T data) {
		Node<T> current = root;
		while (current != null) {
			if (data.equals(current.getData())) return true;
			current = data.compareTo(current.getData()) < 0 ? current.getLeftChild() : current.getRightChild();
		}
		return false;
	}

	public boolean remove(T data) {
		if (!contains(data)) return false;
		remove(data, root);
		return true;
	}

	public Node<T> remove(T data, Node<T> current) {
		int cmp = data.compareTo(current.getData());
		if (cmp == 0) {
			if (current.getLeftChild() == null) {
				Node<T> rightChild = current.getRightChild();
				current.setData(null);
				current.setRightChild(null);
				return rightChild;
			} else if (current.getRightChild() == null) {
				Node<T> leftChild = current.getLeftChild();
				current.setData(null);
				current.setLeftChild(null);
				return leftChild;
			} else {
				Node<T> leftMost = getLeftMost(current.getRightChild());
				current.setData(leftMost.getData());
				return remove(current.getData(), current.getRightChild());
			}
		} else {
			if (cmp < 0) {
				current.setLeftChild(remove(data, current.getLeftChild()));
			} else {
				current.setRightChild(remove(data, current.getRightChild()));
			}
		}
		return current;
	}

	private Node<T> getLeftMost(Node<T> current) {
		if (current.getLeftChild() == null) return current;
		return getLeftMost(current.getLeftChild());
	}

	private Node<T> rightLeftRotation(Node<T> parent) {
		parent.setRightChild(rightRotation(parent.getRightChild()));
		return leftRotation(parent);
	}

	private Node<T> leftRightRotation(Node<T> parent) {
		parent.setLeftChild(leftRotation(parent.getLeftChild()));
		return rightRotation(parent);
	}

	private Node<T> rightRotation(Node<T> parent) {
		Node<T> child = parent.getLeftChild();
		parent.setLeftChild(child.getRightChild());
		child.setRightChild(parent);
		update(parent);
		update(child);
		return child;
	}

	private Node<T> leftRotation(Node<T> parent) {
		Node<T> child = parent.getRightChild();
		parent.setRightChild(child.getLeftChild());
		child.setLeftChild(parent);
		update(parent);
		update(child);
		return child;
	}

	private void update(Node<T> current) {
		int leftNodeHeight = current.getLeftChild() == null ? -1 : current.getLeftChild().getHeight();
		int rightNodeHeight = current.getRightChild() == null ? -1 : current.getRightChild().getHeight();
		current.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
		current.setBalance(rightNodeHeight - leftNodeHeight);
	}

	private Node<T> balance(Node<T> current) {
		if (current.getBalance() == 2) {
			if (current.getRightChild().getBalance() == - 1) {
				return rightLeftRotation(current);
			} else {
				return leftRotation(current);
			}
		} else if (current.getBalance() == -2) {
			if (current.getLeftChild().getBalance() == 1) {
				return leftRightRotation(current);
			} else {
				return rightRotation(current);
			}
		}
		return current;
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

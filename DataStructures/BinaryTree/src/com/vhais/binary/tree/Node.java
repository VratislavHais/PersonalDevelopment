package com.vhais.binary.tree;

public class Node<T extends Comparable<T>> {
	private T data;
	private Node<T> leftChild;
	private Node<T> rightChild;
	private int height;
	private int balance;

	public Node(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBalance() {
		return this.balance;
	}

	public boolean isBalanced() {
		return balance > -2 && balance < 2;
	}

	public int getHeight() {
		return this.height;
	}
}

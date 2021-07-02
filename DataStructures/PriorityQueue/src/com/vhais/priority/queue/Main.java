package com.vhais.priority.queue;

public class Main {

	public static void main(String[] args) {
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(new Integer[] {6, 2, 1, 7, 9, 3, 2});
		binaryHeap.remove(6);
		binaryHeap.removeAt(3);
		binaryHeap.removeLast();
		binaryHeap.printAll();
	}
}

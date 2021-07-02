package com.vhais.priority.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>>{
	private List<T> heap;

	public BinaryHeap() {
		this(1);
	}

	public BinaryHeap(int heapSize) {
		heap = new ArrayList<>(heapSize);
	}

	public BinaryHeap(T[] items) {
		heap = new ArrayList<>(items.length);
		for (T item : items) add(item);
	}

	public void add(T item) {
		if (item == null) throw new NullPointerException("Item can not be null.");
		heap.add(item);
		swim(heap.size() - 1);
	}

	public T removeAt(int index) {
		if (index >= heap.size()) throw new IndexOutOfBoundsException("Index out of bound: " + index);
		swap(index, heap.size() - 1);
		final T removedElement = removeLast();
		final T swappedElement = heap.get(index);
		if (isEmpty()) return removedElement;
		sink(index);
		if (heap.get(index).equals(swappedElement)) swim(index);
		return removedElement;
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public T poll() {
		return removeAt(0);
	}

	public T peek() {
		if (isEmpty()) throw new IndexOutOfBoundsException("Heap is empty");
		return heap.get(0);
	}

	public int size() {
		return heap.size();
	}

	public boolean remove(T item) {
		if (item == null) return false;
		for (int i = 0; i < heap.size(); i++) {
			if (heap.get(i).equals(item)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	public T removeLast() {
		return heap.remove(heap.size() - 1);
	}

	private void swim(int index) {
		int parent = (index - 1) / 2;
		while (index  > 0 && isLesserThen(index, parent)) {
			swap(index, parent);
			index = parent;
			parent = (parent - 1) / 2;
		}
	}

	private void sink(int index) {
		do {
			int childLeft = index * 2 + 1, childRight = index * 2 + 2;
			int smallest = isLesserThen(childRight, childLeft) ? childRight : childLeft;
			if (smallest >= heap.size() || isLesserThen(index, smallest)) break;
			swap(index, smallest);
			index = smallest;
		} while (index * 2 + 1 < heap.size());
	}

	private void swap(int i, int j) {
		final T tmp = heap.get(j);
		heap.set(j, heap.get(i));
		heap.set(i, tmp);
	}

	private boolean isLesserThen(int indexSrc, int indexTarget) {
		if (indexSrc >= heap.size() || indexTarget >= heap.size()) return false;
		return heap.get(indexSrc).compareTo(heap.get(indexTarget)) <= 0;
	}

	public void printAll() {
		heap.forEach(i -> System.out.print(i + " "));
		System.out.println();
	}
}

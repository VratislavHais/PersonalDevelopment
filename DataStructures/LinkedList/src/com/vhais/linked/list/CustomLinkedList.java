package com.vhais.linked.list;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomLinkedList<T> implements Iterable<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;

	private static class Node<T> {
		private T data;
		private Node<T> next;

		public Node() {
		}

		public Node(T data) {
			this.data = data;
		}

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void add(T item) {
		if (this.size == 0) {
			this.head = this.tail = new Node<>(item);
		} else {
			this.tail.next = new Node<>(item);
			this.tail = this.tail.next;
		}
		size++;
	}

	public void addFirst(T item) {
		if (this.size == 0) {
			this.head = this.tail = new Node<>(item);
		} else {
			final Node<T> tmp = this.head;
			this.head = new Node<>(item);
			this.head.next = tmp;
		}
		size++;
	}

	public T peekFirst() {
		this.emptyOrThrow();
		return this.head.data;
	}

	public T peekLast() {
		this.emptyOrThrow();
		return this.tail.data;
	}

	public T removeFirst() {
		this.emptyOrThrow();
		T result = this.head.data;
		this.head = this.head.next;
		size--;
		if (this.isEmpty()) this.tail = null;
		return result;
	}

	public T removeLast() {
		return this.remove(size - 1);
	}

	public T remove(int index) {
		if (index > this.size) throw new IndexOutOfBoundsException(String.format("Supplied index is greater than size: index=%s size=%s", index, size));
		if (index == 0) return removeFirst();
		int currentIndex = 0;
		Node<T> pred = this.head;
		while (currentIndex < index - 1) {
			pred = pred.next;
			currentIndex++;
		}
		T result = pred.next.data;
		if (pred.next == this.tail) this.tail = pred;
		pred.next = pred.next.next;
		size--;
		return result;
	}

	public boolean remove(Object o) {
		if (indexOf(o) == -1) return false;
		Node<T> prec = this.head;
		while (prec.next.data != o) prec = prec.next;
		prec.next = prec.next.next;
		size--;
		return true;
	}

	public int indexOf(Object o) {
		int idxCount = 0;
		Node<T> current = this.head;
		while (current != null) {
			if (current.data == o) return idxCount;
			current = current.next;
			idxCount++;
		}
		return -1;
	}

	public boolean contains(Object o) {
		return this.indexOf(o) != -1;
	}

	private void emptyOrThrow() {
		if (this.isEmpty()) throw new RuntimeException("List is empty.");
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> current = head;
			@Override
			public boolean hasNext() {
				return current.next != null;
			}

			@Override
			public T next() {
				return (current = current.next).data;
			}
		};
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		Node<T> current = head;
		while (current != null) {
			action.accept(current.data);
			current = current.next;
		}
	}

	@Override
	public Spliterator<T> spliterator() {
		return null;
	}
}

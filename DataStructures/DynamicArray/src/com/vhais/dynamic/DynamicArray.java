package com.vhais.dynamic;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DynamicArray<T> implements Iterable<T> {
	private int capacity;
	private int length;
	private T[] array;

	public DynamicArray() {
		this(16);
	}

	private DynamicArray(int capacity) {
		if (capacity < 0) throw new IllegalArgumentException("Capacity must be greater than zero: " + capacity);
		this.capacity = capacity;
		this.array = (T[]) new Object[capacity];
		length = 0;
	}

	public DynamicArray(T[] items) {
		this.array = (T[]) new Object[items.length];
		this.capacity = items.length;
		this.length = items.length;
		System.arraycopy(items, 0, this.array, 0, items.length);
	}

	public int size() {
		return this.length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public T get(int index) {
		if (index > length) throw new ArrayIndexOutOfBoundsException("Index out of bound: " + index);
		return array[index];
	}

	public void set(int index, T item) {
		if (index > length) throw new ArrayIndexOutOfBoundsException("Index out of bound: " + index);
		array[index] = item;
	}

	public void add(T item) {
		if (this.length + 1 >= this.capacity) resize(this.capacity * 2);
		this.array[this.length++] = item;
	}

	public T removeAt(int index) {
		if (index >= this.length) throw new ArrayIndexOutOfBoundsException("Index out of bound: " + index);
		final T item = this.array[index];
		T[] newArray = (T[]) new Object[this.length-1];
		for (int i = 0, j = 0; i < this.length; i++, j++) {
			if (i == index) j--;
			else newArray[j] = this.array[i];
		}
		this.array = newArray;
		this.capacity = this.length--;
		return item;
	}

	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index == -1) return false;
		removeAt(index);
		return true;
	}

	public int indexOf(Object o) {
		for (int i = 0; i < this.length; i++) {
			if (o == null) {
				if (this.array[i] == null) return i;
			} else {
				if (o.equals(this.array[i])) return i;
			}
		}
		return -1;
	}

	public void clear() {
		for (int i = 0; i < this.length; i++) this.array[i] = null;
		this.length = 0;
	}

	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	private void resize(int newCapacity) {
		T[] newArray = (T[]) new Object[newCapacity];
		this.capacity = newCapacity;
		for (int i = 0; i < this.length; i++) {
			newArray[i] = array[i];
		}
		this.array = newArray;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < length;
			}

			@Override
			public T next() {
				return array[index++];
			}
		};
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		resize(this.length);
		for (T item : array) {
			action.accept(item);
		}
	}

	@Override
	public Spliterator<T> spliterator() {
		return null;
	}
}

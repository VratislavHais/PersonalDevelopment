package com.vhais.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class HashMap<K, V> implements Iterable<K> {
	// stand for max load factor
	private final double mlf;
	private int capacity, size, threshold;
	private LinkedList <Entry<K, V>>[] table;

	public HashMap() {
		this(10, 0.7);
	}

	public HashMap(int capacity) {
		this(capacity, 0.7);
	}

	public HashMap(int capacity, double mlf) {
		if (capacity <= 0 || mlf < 0.3) throw new IllegalArgumentException("Capacity or max load factor too low.");
		this.mlf = mlf;
		this.capacity = capacity;
		this.size = 0;
		this.threshold = (int) (capacity * mlf);
		table = new LinkedList[capacity];
	}

	private int normalizeIndex(int hash) {
		return (hash & 0x7FFFFFFF) % this.capacity;
	}

	public V put(K key, V value) {
		if (key == null) throw new IllegalArgumentException("Key can not be null");
		int index = normalizeIndex(key.hashCode());
		V returnValue = null;
		if (table[index] == null) {
			LinkedList<Entry<K, V>> ll = new LinkedList<>();
			ll.add(new Entry<>(key, value));
			table[index] = ll;
		} else {
			Entry<K, V> entry = getEntry(key);
			if (entry == null) table[index].add(new Entry<>(key, value));
			else {
				V prevValue = entry.value;
				entry.value = value;
				returnValue = prevValue;
			}
		}
		size++;
		if (size >= threshold) resizeTable();
		return returnValue;
	}

	public int size() {
		return this.size;
	}

	public V remove(K key) {
		if (key == null) return null;
		Entry<K, V> entry = getEntry(key);
		table[normalizeIndex(key.hashCode())].remove(entry);
		size--;
		return entry.value;
	}

	public Entry<K, V> getEntry(K key) {
		if (key == null) return null;
		LinkedList<Entry<K, V>> ll = table[normalizeIndex(key.hashCode())];
		if (ll == null) return null;
		for (Entry<K, V> entry : ll) {
			if (entry.key.equals(key)) return entry;
		}
		return null;
	}

	public List<K> keys() {
		List<K> keysResult = new ArrayList<>();
		for (LinkedList<Entry<K, V>> tableEntry : table) {
			if (tableEntry != null) {
				for (Entry<K, V> entry : tableEntry) keysResult.add(entry.key);
			}
		}
		return keysResult;
	}

	public List<V> values() {
		List<V> valuesResult = new ArrayList<>();
		for (LinkedList<Entry<K, V>> tableEntry : table) {
			if (tableEntry != null) {
				for (Entry<K, V> entry : tableEntry) valuesResult.add(entry.value);
			}
		}
		return valuesResult;
	}

	public List<Entry<K, V>> entries() {
		List<Entry<K, V>> result = new ArrayList<>();
		for (LinkedList<Entry<K, V>> tableEntry : table) {
			if (tableEntry != null) {
				for (Entry<K, V> entry : tableEntry) result.add(new Entry<>(entry.key, entry.value));
			}
		}
		return result;
	}

	public boolean containsKey(K key) {
		if (key == null) return false;
		return getEntry(key) != null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}

	private void resizeTable() {
		capacity *= 2;
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
		threshold = (int) (capacity * mlf);
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K, V>> entries = table[i];
				for (Entry<K, V> entry : entries) {
					int index = normalizeIndex(entry.hash);
					if (newTable[index] == null) newTable[index] = new LinkedList<>();
					newTable[index].add(entry);
				}
				table[i].clear();
				table[i] = null;
			}
		}
		this.table = newTable;
	}

	@Override
	public Iterator<K> iterator() {
		return new LinkedList<>(keys()).iterator();
	}

	@Override
	public void forEach(Consumer<? super K> action) {
		keys().forEach(action);
	}

	@Override
	public Spliterator<K> spliterator() {
		return keys().spliterator();
	}

	public static class Entry<K, V> {
		K key;
		V value;
		int hash;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.hash = key.hashCode();
		}

		public boolean equals(Entry<K, V> o) {
			if (this.hash != o.hash) return false;
			return (this.key.equals(o.key));
		}
	}
}

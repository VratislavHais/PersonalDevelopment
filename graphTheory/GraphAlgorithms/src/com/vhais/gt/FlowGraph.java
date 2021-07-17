package com.vhais.gt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlowGraph<T> {
	private final Map<T, List<Edge<T>>> graph;
	private final Map<T, Node<T>> nodes;
	private final int numberOfNodes;
	private final Node<T> source, sink;

	public FlowGraph(int numberOfNodes, T source, T sink) {
		graph = new HashMap<>();
		nodes = new HashMap<>();
		this.numberOfNodes = numberOfNodes;
		this.source = createNode(source);
		this.sink = createNode(sink);
	}

	public Node<T> source() {
		return this.source;
	}

	public Node<T> sink() {
		return this.sink;
	}

	public Set<T> nodeList() {
		return graph.keySet();
	}

	public boolean isEmpty() {
		return graph.keySet().isEmpty();
	}

	public List<FlowGraph.Edge<T>> getEdgesFromNode(T node) {
		if (graph.get(node) == null) return null;
		return new ArrayList<>(graph.get(node));
	}

	public List<FlowGraph.Edge<T>> getEdgesFromNode(Node<T> node) {
		return getEdgesFromNode(node.name);
	}

	public void addEdge(T from, T to, int maxCapacity) {
		if (maxCapacity < 0) throw new IllegalArgumentException("Capacity must be greater or equal zero.");
		if (to.equals(source)) throw new IllegalArgumentException("Source can not contain any incoming edge.");
		if (from.equals(sink)) throw new IllegalArgumentException("Sink can not contain any outgoing edge.");
		if (!graph.containsKey(from)) {
			graph.put(from, new ArrayList<>(numberOfNodes * 2));
		}
		if (!graph.containsKey(to)) {
			graph.put(to, new ArrayList<>(numberOfNodes * 2));
		}
		Edge<T> edge = new Edge<>(createNode(from), createNode(to), maxCapacity);
		edge.residual = new Edge<>(createNode(to), createNode(from), 0);
		graph.get(from).add(edge);
		graph.get(to).add(edge.residual);
	}

	private Node<T> createNode(T nodeName) {
		if (!nodes.containsKey(nodeName)) {
			nodes.put(nodeName, new Node<>(nodeName));
		}
		return nodes.get(nodeName);
	}

	public void resetLevels() {
		for (Node<T> node : nodes.values()) node.resetLevel();
	}

	static class Edge<T> {
		final Node<T> from, to;
		final int maxCapacity;
		private int flow;
		public Edge<T> residual;

		public Edge(Node<T> from, Node<T> to, int maxCapacity) {
			this.from = from;
			this.to = to;
			this.maxCapacity = maxCapacity;
		}

		public boolean isResidual() {
			return this.maxCapacity == 0;
		}

		public int flow() {
			return this.flow;
		}

		public int remaining() {
			return this.maxCapacity - this.flow;
		}

		public void augment(int value) {
			this.flow += value;
			residual.flow -= value;
		}
	}

	static class Node<T> {
		final T name;
		private int level;

		public Node(T name) {
			this.name = name;
			level = -1;
		}

		public void setLevel(int level) {
			if (this.level >= 0) this.level = Math.min(this.level, level);
			else this.level = level;
		}

		public int level() {
			return this.level;
		}

		public void resetLevel() {
			this.level = -1;
		}
	}
}

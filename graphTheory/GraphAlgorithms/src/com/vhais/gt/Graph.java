package com.vhais.gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T extends Comparable<T>> {
	private final Map<T, List<Edge<T>>> graph;
	private final boolean directed;
	private final boolean weighted;
	private final int numberOfNodes;

	private Graph(Map<T, List<Edge<T>>> graph, boolean directed, boolean weighted, int numberOfNodes) {
		this.graph = graph;
		this.directed = directed;
		this.weighted = weighted;
		this.numberOfNodes = numberOfNodes;
	}

	public Set<T> nodeList() {
		return graph.keySet();
	}

	public boolean isEmpty() {
		return graph.keySet().isEmpty();
	}

	public List<Edge<T>> getEdgesFromNode(T node) {
		if (graph.get(node) == null) return null;
		return new ArrayList<>(graph.get(node));
	}

	public void addEmptyNode(T node) {
		if (!graph.containsKey(node)) {
			graph.put(node, null);
		}
	}

	public void addEdge(T from, T to) {
		if (this.directed) {
			addEdge(from, to, 0);
		} else {
			addUndirectedEdge(from, to, 0);
		}
	}

	public void addEdge(T from, T to, int cost) {
		if (!this.weighted) cost = 0;
		if (this.directed) {
			addEdge(new Edge<T>(from, to, cost));
		} else {
			addUndirectedEdge(from, to, cost);
		}
	}

	private void addUndirectedEdge(T from, T to, int cost) {
		addEdge(new Edge<T>(from, to, cost));
		addEdge(new Edge<T>(to, from, cost));
	}

	private void addEdge(Edge<T> edge) {
		if (graph.containsKey(edge.from)) {
			graph.get(edge.from).add(edge);
		} else {
			graph.put(edge.from, new ArrayList<>(Arrays.asList(edge)));
		}
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	// bellman-ford
	public boolean containsNegativeCycle() {
		Map<T, Double> nodes = new HashMap<>();
		nodeList().stream().findFirst().ifPresent(node -> nodes.put(node, 0.0));
		nodeList().stream().skip(1).forEach(node -> nodes.put(node, Double.POSITIVE_INFINITY));
		for (int i = 0; i < nodes.size(); i++) {
			for (Edge<T> edge : getAllEdges()) {
				if (nodes.get(edge.from) + edge.cost < nodes.get(edge.to)) {
					nodes.replace(edge.to, nodes.get(edge.from) + edge.cost);
				}
			}
		}
		for (int i = 0; i < nodes.size(); i++) {
			for (Edge<T> edge : getAllEdges()) {
				if (nodes.get(edge.from) + edge.cost < nodes.get(edge.to)) {
					nodes.replace(edge.to, Double.NEGATIVE_INFINITY);
				}
			}
		}
		return nodes.containsValue(Double.NEGATIVE_INFINITY);
	}

	public List<Edge<T>> getAllEdges() {
		List<Edge<T>> edges = new ArrayList<>();
		for (List<Edge<T>> edgeList : graph.values()) {
			if (edgeList != null) edges.addAll(edgeList);
		}
		return edges;
	}

	public Set<T> getAllNodes() {
		return graph.keySet();
	}

	public static class GraphBuilder<T extends Comparable<T>> {
		private Map<T, List<Graph.Edge<T>>> graph = new HashMap<>();
		private boolean directed = false;
		private boolean weighted = false;
		private final int numberOfNodes;

		public GraphBuilder(int numberOfNodes) {
			this.numberOfNodes = numberOfNodes;
		}

		public GraphBuilder<T> graph(Map<T, List<Graph.Edge<T>>> graph) {
			this.graph = graph;
			return this;
		}

		public GraphBuilder<T> directed() {
			this.directed = true;
			return this;
		}

		public GraphBuilder<T> weighted() {
			this.weighted = true;
			return this;
		}

		public Graph<T> build() {
			return new Graph<T>(graph, directed, weighted, numberOfNodes);
		}
	}

	static class Edge<T extends Comparable<T>> {
		final T from, to;
		final int cost;

		public Edge(T from, T to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int getCost() {
			return this.cost;
		}

		@Override
		public String toString() {
			return String.format("%s -> %s : %s", from, to, cost);
		}

		public boolean isReverse(Edge<T> edge) {
			return this.from == edge.to && this.to == edge.from;
		}
	}

	static class Node<T> {
		T id;
		double cost;

		public Node(T id, double cost) {
			this.id = id;
			this.cost = cost;
		}

		public double getCost() {
			return cost;
		}
	}

}

package com.vhais.dfs;

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

	static class Edge<T extends Comparable<T>> implements Comparable {
		final T from, to;
		final int cost;

		public Edge(T from, T to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Object o) {
			Edge<T> edge2 = (Edge<T>) o;
			return this.cost - ((Edge<T>) o).cost;
		}
	}
}

package com.vhais.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private final Map<Integer, List<Edge>> graph;
	private final boolean directed;
	private final boolean weighted;
	private final int numberOfNodes;

	private Graph(Map<Integer, List<Edge>> graph, boolean directed, boolean weighted, int numberOfNodes) {
		this.graph = graph;
		this.directed = directed;
		this.weighted = weighted;
		this.numberOfNodes = numberOfNodes;
	}

	public boolean isEmpty() {
		return graph.keySet().isEmpty();
	}

	public List<Edge> getEdgesFromNode(int node) {
		return new ArrayList<>(graph.get(node));
	}

	public void addEdge(int from, int to) {
		if (this.directed) {
			addEdge(from, to, 0);
		} else {
			addUndirectedEdge(from, to, 0);
		}
	}

	public void addEdge(int from, int to, int cost) {
		if (!this.weighted) cost = 0;
		if (this.directed) {
			addEdge(new Edge(from, to, cost));
		} else {
			addUndirectedEdge(from, to, cost);
		}
	}

	private void addUndirectedEdge(int from, int to, int cost) {
		addEdge(new Edge(from, to, cost));
		addEdge(new Edge(to, from, cost));
	}

	private void addEdge(Edge edge) {
		if (graph.containsKey(edge.from)) {
			graph.get(edge.from).add(edge);
		} else {
			graph.put(edge.from, new ArrayList<>(Arrays.asList(edge)));
		}
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public static class GraphBuilder {
		private Map<Integer, List<Graph.Edge>> graph = new HashMap<>();
		private boolean directed = false;
		private boolean weighted = false;
		private final int numberOfNodes;

		public GraphBuilder(int numberOfNodes) {
			this.numberOfNodes = numberOfNodes;
		}

		public GraphBuilder graph(Map<Integer, List<Graph.Edge>> graph) {
			this.graph = graph;
			return this;
		}

		public GraphBuilder directed() {
			this.directed = true;
			return this;
		}

		public GraphBuilder weighted() {
			this.weighted = true;
			return this;
		}

		public Graph build() {
			return new Graph(graph, directed, weighted, numberOfNodes);
		}
	}

	static class Edge {
		final int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}

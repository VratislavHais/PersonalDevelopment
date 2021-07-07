package com.vhais.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearch {
	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	private final Map<Integer, List<Edge>> graph;
	private boolean solved;
	private boolean[] visited;
	private long result;
	private final int numberOfNodes;

	public DepthFirstSearch(Map<Integer, List<Edge>> graph, int numberOfNodes) {
		this.graph = graph;
		this.numberOfNodes = numberOfNodes;
		solved = false;
	}

	public DepthFirstSearch(int numberOfNodes) {
		this(new HashMap<>(), numberOfNodes);
	}

	public void addEdge(int from, int to) {
		addEdge(from, to, 0);
	}

	public void addEdge(int from, int to, int cost) {
		addEdge(new Edge(from, to, cost));
	}

	public void addEdge(Edge edge) {
		if (graph.containsKey(edge.from)) {
			graph.get(edge.from).add(edge);
		} else {
			graph.put(edge.from, new ArrayList<>(Arrays.asList(edge)));
		}
	}

	public void addUndirectedEdge(int from, int to, int cost) {
		addEdge(new Edge(from, to, cost));
		addEdge(new Edge(to, from, cost));
	}

	public void addUndirectedEdge(int from, int to) {
		addUndirectedEdge(from, to, 0);
	}

	public long dfs() {
		return dfs(0);
	}

	public long dfs(int start) {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty.");
		if (solved) return result;
		visited = new boolean[numberOfNodes];
		result = depthFirstSearch(start);
		solved = true;
		return result;
	}

	private long depthFirstSearch(int current) {
		if (visited[current]) return 0L;
		visited[current] = true;
		long count = 1L;
		List<Edge> edges = graph.get(current);
		if (edges != null) {
			for (Edge edge : edges) {
				count += depthFirstSearch(edge.to);
			}
		}
		return count;
	}
}

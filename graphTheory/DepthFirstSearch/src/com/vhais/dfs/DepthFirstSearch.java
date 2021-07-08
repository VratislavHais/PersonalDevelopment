package com.vhais.dfs;

import java.util.List;

public class DepthFirstSearch {
	private final Graph graph;
	private boolean solved;
	private boolean[] visited;
	private long result;

	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
		solved = false;
	}

	public long dfs() {
		return dfs(0);
	}

	public long dfs(int start) {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty.");
		if (solved) return result;
		visited = new boolean[graph.getNumberOfNodes()];
		result = depthFirstSearch(start);
		solved = true;
		return result;
	}

	private long depthFirstSearch(int current) {
		if (visited[current]) return 0L;
		visited[current] = true;
		long count = 1L;
		List<Graph.Edge> edges = graph.getEdgesFromNode(current);
		if (edges != null) {
			for (Graph.Edge edge : edges) {
				count += depthFirstSearch(edge.to);
			}
		}
		return count;
	}
}

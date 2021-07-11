package com.vhais.dfs;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch<T extends Comparable<T>> {
	private final Graph<T> graph;
	private List<T> visited;

	public DepthFirstSearch(Graph<T> graph) {
		this.graph = graph;
	}

	public long dfs(T start) {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty.");
		visited = new ArrayList<>();
		return depthFirstSearch(start);
	}

	private long depthFirstSearch(T current) {
		if (visited.contains(current)) return 0L;
		visited.add(current);
		long count = 1L;
		List<Graph.Edge<T>> edges = graph.getEdgesFromNode(current);
		if (edges != null) {
			for (Graph.Edge<T> edge : edges) {
				count += depthFirstSearch(edge.to);
			}
		}
		return count;
	}
}

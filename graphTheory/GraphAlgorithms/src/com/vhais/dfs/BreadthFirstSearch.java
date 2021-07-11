package com.vhais.dfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch<T extends Comparable<T>> {
	private final Graph<T> graph;
	private List<T> visited;

	public BreadthFirstSearch(Graph<T> graph) {
		this.graph = graph;
	}

	public long bfs(T start) {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty.");
		visited = new ArrayList<>();
		return breadthFirstSearch(start);
	}

	private long breadthFirstSearch(T start) {
		Deque<T> queue = new LinkedList<>();
		queue.push(start);
		visited.add(start);
		long count = 1L;
		while (!queue.isEmpty()) {
			for (Graph.Edge<T> edge : graph.getEdgesFromNode(queue.pop())) {
				if (!visited.contains(edge.to)) {
					visited.add(edge.to);
					count++;
					queue.push(edge.to);
				}
			}
		}
		return count;
	}
}

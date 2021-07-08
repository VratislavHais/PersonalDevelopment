package com.vhais.dfs;

import java.util.Deque;
import java.util.LinkedList;

public class BreadthFirstSearch {
	private Graph graph;
	private boolean solved;
	private boolean[] visited;
	private long result;

	public BreadthFirstSearch(Graph graph) {
		this.graph = graph;
		solved = false;
	}

	public long bfs() {
		return bfs(0);
	}

	public long bfs(int start) {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty.");
		if (solved) return result;
		visited = new boolean[graph.getNumberOfNodes()];
		result = breadthFirstSearch(start);
		solved = true;
		return result;
	}

	private long breadthFirstSearch(int start) {
		Deque<Integer> queue = new LinkedList<>();
		queue.push(start);
		visited[start] = true;
		long count = 1L;
		while (!queue.isEmpty()) {
			for (Graph.Edge edge : graph.getEdgesFromNode(queue.pop())) {
				if (!visited[edge.to]) {
					visited[edge.to] = true;
					count++;
					queue.push(edge.to);
				}
			}
		}
		return count;
	}
}

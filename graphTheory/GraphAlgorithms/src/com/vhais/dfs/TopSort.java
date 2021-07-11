package com.vhais.dfs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopSort<T extends Comparable<T>> {
	private final Graph<T> graph;
	private List<T> visited;

	public TopSort(Graph<T> graph) {
		this.graph = graph;
	}

	public T[] execute(T startNode) {
		visited = new ArrayList<>();
		T[] order = (T[]) Array.newInstance(startNode.getClass(), graph.getNumberOfNodes());
		int currentIndex = graph.getNumberOfNodes() - 1;
		for (T node : graph.nodeList()) {
			if (visited.contains(node)) continue;
			currentIndex = dfs(node, order, currentIndex);
		}
		return order;
	}

	private int dfs(T currentNode, T[] order, int currentIndex) {
		if (visited.contains(currentNode)) return currentIndex;
		visited.add(currentNode);
		List<Graph.Edge<T>> edges = graph.getEdgesFromNode(currentNode);
		if (edges != null) {
			for (Graph.Edge<T> edge : edges) {
				currentIndex = dfs(edge.to, order, currentIndex);
			}
		}
		order[currentIndex] = currentNode;
		return --currentIndex;
	}
}

package com.vhais.gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bridges<T extends Comparable<T>> {
	private final Graph<T> graph;
	private final Map<T, Integer> mapping;
	private List<Graph.Edge<T>> bridges;
	private boolean solved;
	private boolean[] visited;

	public Bridges(Graph<T> graph) {
		this.graph = graph;
		solved = false;
		mapping = new HashMap<>();
		for (T node : graph.getAllNodes()) {
			mapping.put(node, mapping.size());
		}
		visited = new boolean[graph.getNumberOfNodes()];
	}

	public List<Graph.Edge<T>> solve() {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty");
		if (solved) return bridges;
		bridges = new ArrayList<>();
		int[] lowLinks = new int[graph.getNumberOfNodes()];
		for (int i = 0; i < lowLinks.length; i++) {
			lowLinks[i] = i;
		}
		dfs(graph.getAllNodes().stream().findFirst().get(), graph.getAllNodes().stream().findFirst().get(), lowLinks);
		for (Graph.Edge<T> edge : graph.getAllEdges()) {
			if (lowLinks[mapping.get(edge.from)] < lowLinks[mapping.get(edge.to)]) bridges.add(edge);
		}
		solved = true;
		return bridges;
	}

	public int dfs(T current, T parent, int[] lowLinks) {
		int index = mapping.get(current);
		if (visited[index]) return lowLinks[index];
		visited[index] = true;
		for (Graph.Edge<T> edge : graph.getEdgesFromNode(current)) {
			if (edge.to == parent) continue;
			lowLinks[index] = Math.min(dfs(edge.to, edge.from, lowLinks), lowLinks[index]);
		}
		return lowLinks[index];
	}
}

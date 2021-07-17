package com.vhais.gt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PrimsMinimumSpanningTree<T extends Comparable<T>> {
	private final Graph<T> graph;
	private final Map<T, Integer> mapping;
	private boolean solved;
	private int result;
	private List<Graph.Edge<T>> edges;

	public PrimsMinimumSpanningTree(Graph<T> graph) {
		this.graph = graph;
		mapping = new HashMap<>();
		for (T node : graph.getAllNodes()) {
			mapping.put(node, mapping.size());
		}
		solved = false;
	}

	public int solve() {
		if (solved) return result;
		edges = new ArrayList<>();
		result = 0;
		boolean[] visited = new boolean[graph.getNumberOfNodes()];
		PriorityQueue<Graph.Edge<T>> pq = new PriorityQueue<>(graph.getNumberOfNodes() * 2, Comparator.comparingInt(Graph.Edge::getCost));
		T initialNode = graph.getAllNodes().stream().findFirst().get();
		pq.addAll(graph.getEdgesFromNode(initialNode));
		visited[mapping.get(initialNode)] = true;
		while (!pq.isEmpty()) {
			Graph.Edge<T> edge = pq.poll();
			if (!visited[mapping.get(edge.to)]) {
				result += edge.cost;
				edges.add(edge);
				pq.addAll(graph.getEdgesFromNode(edge.to));
				visited[mapping.get(edge.to)] = true;
			}
		}
		return result;
	}

	public List<Graph.Edge<T>> getUsedEdges() {
		if (!solved) solve();
		return edges;
	}
}

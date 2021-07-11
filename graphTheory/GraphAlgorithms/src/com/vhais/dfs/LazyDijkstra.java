package com.vhais.dfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LazyDijkstra<T extends Comparable<T>> {
	private final Graph<T> graph;

	public LazyDijkstra(Graph<T> graph) {
		this.graph = graph;
	}

	public double dijkstra(T start, T end) {
		if (start.equals(end)) return 0.0;
		List<T> visited = new ArrayList<>();
		PriorityQueue<Node<T>> pq = new PriorityQueue<>(2 * graph.getNumberOfNodes(), Comparator.comparingDouble(Node::getCost));
		pq.offer(new Node<>(start, 0));
		visited.add(start);
		Map<T, Double> result = new HashMap<>();
		result.put(start, 0.0);
		while (!pq.isEmpty()) {
			Node<T> node = pq.poll();
			if (result.get(node.id) < node.cost) continue;
			visited.add(node.id);
			for (Graph.Edge<T> edge : graph.getEdgesFromNode(node.id)) {
				if (visited.contains(edge.to)) continue;
				double distance = node.cost + edge.cost;
				if (result.containsKey(edge.to)) {
					if (result.get(edge.to) < distance) continue;
					else result.replace(edge.to, distance);
				} else result.put(edge.to, distance);
				pq.offer(new Node<>(edge.to, distance));
			}
			if (node.id == end) return result.get(end);
		}
		return Double.POSITIVE_INFINITY;
	}

	static class Node<T> {
		T id;
		double cost;

		public Node(T id, double cost) {
			this.id = id;
			this.cost = cost;
		}

		public double getCost() {
			return cost;
		}
	}
}

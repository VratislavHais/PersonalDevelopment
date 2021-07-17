package com.vhais.gt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EulerianPath<T extends Comparable<T>> {
	private final Graph<T> graph;
	private final Map<T, Integer> mapping;
	private final int[] outEdges;
	private final int[] inEdges;
	private T startNode, endNode;
	private LinkedList<T> result;
	private boolean solved;

	public EulerianPath(Graph<T> graph) {
		this.graph = graph;
		mapping = new HashMap<>();
		for (T node : graph.getAllNodes()) {
			mapping.put(node, mapping.size());
		}
		outEdges = new int[graph.getNumberOfNodes()];
		inEdges = new int[graph.getNumberOfNodes()];
		solved = false;
	}

	public List<T> solve() {
		if (solved) return result;
		calculateInOutEdges();
		if (!eulerianPathExists()) result = null;
		else {
			result = new LinkedList<>();
			dfs(startNode);
		}
		return result;
	}

	private void dfs(T current) {
		int index = mapping.get(current);
		while (outEdges[index] > 0) {
			Graph.Edge<T> edge = graph.getEdgesFromNode(current).get(--outEdges[index]);
			dfs(edge.to);
		}
		result.addFirst(current);
	}

	private void calculateInOutEdges() {
		for (Graph.Edge<T> edge : graph.getAllEdges()) {
			inEdges[mapping.get(edge.to)]++;
			outEdges[mapping.get(edge.from)]++;
		}
	}

	private boolean eulerianPathExists() {
		for (int i = 0; i < outEdges.length; i++) {
			if (outEdges[i] > inEdges[i]) {
				if (startNode != null) return false;
				startNode = getNodeByIndex(i);
			} else if (outEdges[i] < inEdges[i]) {
				if (endNode != null) return false;
				endNode = getNodeByIndex(i);
			}
		}
		return true;
	}

	private T getNodeByIndex(int i) {
		return mapping.entrySet().parallelStream().filter(entry -> entry.getValue().equals(i)).findFirst().get().getKey();
	}

}

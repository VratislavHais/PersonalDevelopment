package com.vhais.gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Tarjan<T extends Comparable<T>> {
	private final Graph<T> graph;
	private final Map<T, Integer> mapping;
	private List<List<T>> components;
	private boolean solved;
	private boolean[] visited;

	public Tarjan(Graph<T> graph) {
		this.graph = graph;
		solved = false;
		mapping = new HashMap<>();
		for (T node : graph.getAllNodes()) {
			mapping.put(node, mapping.size());
		}
		visited = new boolean[graph.getNumberOfNodes()];
	}

	public List<List<T>> solve() {
		if (graph.isEmpty()) throw new IllegalStateException("Graph is empty.");
		if (solved) return components;
		components = new ArrayList<>();
		int[] lowlinks = new int[graph.getNumberOfNodes()];
		for (int i = 0; i < lowlinks.length; i++) lowlinks[i] = i;
		for (T node : graph.getAllNodes()) {
			if (visited[mapping.get(node)]) continue;
			dfs(node, lowlinks, new LinkedList<>());
		}
		Map<Integer, List<T>> comp = new HashMap<>();
		for (int i = 0; i < lowlinks.length; i++) {
			if (comp.containsKey(lowlinks[i])) {
				comp.get(lowlinks[i]).add(getNodeByIndex(i));
			} else {
				comp.put(lowlinks[i], new ArrayList<>(Collections.singletonList(getNodeByIndex(i))));
			}
		}
		components.addAll(comp.values());
		return components;
	}

	private T getNodeByIndex(int i) {
		return mapping.entrySet().parallelStream().filter(entry -> entry.getValue().equals(i)).findFirst().get().getKey();
	}

	public int dfs(T node, int[] lowlinks, Deque<T> stack) {
		int index = mapping.get(node);
		if (stack.contains(node)) return lowlinks[index];
		stack.offer(node);
		for (Graph.Edge<T> edge : graph.getEdgesFromNode(node)) {
			if (visited[mapping.get(edge.to)]) continue;
			lowlinks[index] = Math.min(dfs(edge.to, lowlinks, stack), lowlinks[index]);
		}
		stack.pop();
		visited[index] = true;
		return lowlinks[index];
	}
}

package com.vhais.gt;

import java.util.LinkedList;
import java.util.Queue;

public class Dinics<T> {
	private final FlowGraph<T> graph;
	private boolean solved = false;
	private static final int INF = Integer.MAX_VALUE >> 1;

	public Dinics(FlowGraph<T> graph) {
		this.graph = graph;
	}

	public int solve() {
		int maxFlow = 0;
		while (calculateLevels()) {
			maxFlow += dfs(graph.source(), INF);
		}
		return maxFlow;
	}

	private int dfs(FlowGraph.Node<T> current, int bottleneck) {
		if (current == graph.sink()) return bottleneck;

		for (FlowGraph.Edge<T> edge : graph.getEdgesFromNode(current)) {
			if (edge.remaining() > 0 && current.level() < edge.to.level()) {
				int bn = dfs(edge.to, Math.min(bottleneck, edge.remaining()));
				if (bn > 0) {
					edge.augment(bn);
					return bn;
				}
			}
		}
		return 0;
	}

	private boolean calculateLevels() {
		graph.resetLevels();
		Queue<FlowGraph.Node<T>> queue = new LinkedList<>();
		graph.source().setLevel(0);
		queue.add(graph.source());
		FlowGraph.Node<T> node;
		do {
			node = queue.poll();
			for (FlowGraph.Edge<T> edge : graph.getEdgesFromNode(node)) {
				if (edge.remaining() > 0 && edge.to.level() == -1) {
					edge.to.setLevel(node.level() + 1);
					queue.add(edge.to);
				}
			}
		} while (!queue.isEmpty());
		return graph.sink().level() != -1;
	}
}

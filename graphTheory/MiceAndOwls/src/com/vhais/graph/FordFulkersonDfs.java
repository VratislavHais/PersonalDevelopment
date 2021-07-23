package com.vhais.graph;

import java.util.HashMap;
import java.util.Map;

public class FordFulkersonDfs {
	private final Graph graph;
	private int[] visited;
	private final Map<Node, Integer> mapping;
	private int VISITED_TOKEN = 1;
	private static final int INF = Integer.MAX_VALUE >> 1;

	public FordFulkersonDfs(Graph graph) {
		this.graph = graph;
		this.mapping = new HashMap<>();
		visited = new int[graph.getNumberOfNodes()];
		for (Node node : graph.getNodes()) mapping.put(node, mapping.size());
	}

	public int solve() {
		if (!graph.isFinished()) graph.finish();
		int maxFlow = 0;
		for (int flow = dfs(graph.source(), INF); flow != 0; flow = dfs(graph.source(), INF)) {
			VISITED_TOKEN++;
			maxFlow += flow;
		}
		return maxFlow;
	}

	private int dfs(Node node, int flow) {
		if (node.equals(graph.sink())) return flow;

		visited[mapping.get(node)] = VISITED_TOKEN;

		for (Edge edge : graph.getEdges(node)) {
			if (visited[mapping.get(edge.to)] != VISITED_TOKEN && edge.remaining() > 0) {
				int bottleneck = dfs(edge.to, Math.min(flow, edge.remaining()));

				if (bottleneck > 0) {
					edge.augment(bottleneck);
					return bottleneck;
				}
			}
		}
		return 0;
	}
}

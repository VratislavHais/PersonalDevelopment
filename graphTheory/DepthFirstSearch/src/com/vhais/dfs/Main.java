package com.vhais.dfs;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph.GraphBuilder(5).build();
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 2);
		DepthFirstSearch dfs = new DepthFirstSearch(graph);
		System.out.println(dfs.dfs(3));
		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		System.out.println(bfs.bfs(2));
	}
}

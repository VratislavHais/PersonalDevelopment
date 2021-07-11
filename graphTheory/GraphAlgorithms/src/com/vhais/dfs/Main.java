package com.vhais.dfs;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		Graph<Integer> graph = new Graph.GraphBuilder<Integer>(5).build();
//		graph.addEdge(0, 1);
//		graph.addEdge(0, 2);
//		graph.addEdge(1, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(2, 3);
//		graph.addEdge(2, 2);
//		DepthFirstSearch<Integer> dfs = new DepthFirstSearch<>(graph);
//		System.out.println(dfs.dfs(3));
//		BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(graph);
//		System.out.println(bfs.bfs(2));
//		Graph<String> graphString = new Graph.GraphBuilder<String>(5).build();
//		graphString.addEdge("A", "B");
//		graphString.addEdge("A", "C");
//		graphString.addEdge("B", "C");
//		graphString.addEdge("B", "D");
//		graphString.addEdge("C", "D");
//		graphString.addEdge("C", "C");
//		DepthFirstSearch<String> dfsString = new DepthFirstSearch<>(graphString);
//		System.out.println(dfsString.dfs("A"));
//		Graph<Integer> graphTop = new Graph.GraphBuilder<Integer>(7).directed().build();
//		graphTop.addEdge(0, 1);
//		graphTop.addEdge(0, 2);
//		graphTop.addEdge(0, 5);
//		graphTop.addEdge(1, 3);
//		graphTop.addEdge(1, 2);
//		graphTop.addEdge(2, 3);
//		graphTop.addEdge(2, 4);
//		graphTop.addEdge(3, 4);
//		graphTop.addEdge(3, 4);
//		graphTop.addEdge(5, 4);
//		graphTop.addEmptyNode(6);
//		TopSort<Integer> topSort = new TopSort<>(graphTop);
//		Arrays.stream(topSort.execute(0)).forEach(i -> System.out.print(i + " "));
		Graph<Integer> graph = new Graph.GraphBuilder<Integer>(6).weighted().build();
		graph.addEdge(0, 1, 3);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 3, 3);
		graph.addEdge(3, 4, 2);
		graph.addEdge(3, 5, 10);
		graph.addEdge(4, 5, 4);
		LazyDijkstra<Integer> dijkstra = new LazyDijkstra<>(graph);
		System.out.println(dijkstra.dijkstra(0, 5));
	}
}

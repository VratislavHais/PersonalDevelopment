package com.vhais.gt;

import java.util.List;

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
//		Graph<Integer> graph = new Graph.GraphBuilder<Integer>(6).weighted().build();
//		graph.addEdge(0, 1, 3);
//		graph.addEdge(0, 2, 1);
//		graph.addEdge(1, 3, 1);
//		graph.addEdge(2, 1, 1);
//		graph.addEdge(2, 3, 3);
//		graph.addEdge(3, 4, 2);
//		graph.addEdge(3, 5, 10);
//		graph.addEdge(4, 5, 4);
//		LazyDijkstra<Integer> dijkstra = new LazyDijkstra<>(graph);
//		System.out.println(dijkstra.dijkstra(0, 5));
//		FloydWarshall fw = new FloydWarshall(7);
//		fw.addEdge(0, 1, 2);
//		fw.addEdge(0, 2, 5);
//		fw.addEdge(0, 6, 10);
//		fw.addEdge(1, 2, 2);
//		fw.addEdge(1, 4, 11);
//		fw.addEdge(2, 6, 2);
//		fw.addEdge(6, 5, 11);
//		fw.addEdge(4, 5, 1);
//		fw.addEdge(5, 4, -2);
//		fw.solve();
//		fw.printPaths();
//		Graph<Integer> graphBridges = new Graph.GraphBuilder<Integer>(9).build();
//		graphBridges.addEdge(0, 1);
//		graphBridges.addEdge(1, 2);
//		graphBridges.addEdge(2, 0);
//		graphBridges.addEdge(2, 3);
//		graphBridges.addEdge(2, 5);
//		graphBridges.addEdge(3, 4);
//		graphBridges.addEdge(5, 6);
//		graphBridges.addEdge(6, 7);
//		graphBridges.addEdge(7, 8);
//		graphBridges.addEdge(8, 5);
//		Bridges<Integer> bridges = new Bridges<>(graphBridges);
//		bridges.solve().forEach(System.out::println);
		Graph<Integer> graphTarjan = new Graph.GraphBuilder<Integer>(8).directed().build();
		graphTarjan.addEdge(0, 1);
		graphTarjan.addEdge(1, 2);
		graphTarjan.addEdge(2, 0);
		graphTarjan.addEdge(3, 7);
		graphTarjan.addEdge(3, 4);
		graphTarjan.addEdge(4, 5);
		graphTarjan.addEdge(5, 0);
		graphTarjan.addEdge(5, 6);
		graphTarjan.addEdge(6, 0);
		graphTarjan.addEdge(6, 2);
		graphTarjan.addEdge(6, 4);
		graphTarjan.addEdge(7, 3);
		graphTarjan.addEdge(7, 5);
		Tarjan<Integer> tarjan = new Tarjan<>(graphTarjan);
		var components = tarjan.solve();
		for (List<Integer> component : components) {
			component.forEach(i -> System.out.print(i + " "));
			System.out.println();
		}
	}
}

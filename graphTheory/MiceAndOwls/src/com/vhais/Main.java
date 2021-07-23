package com.vhais;

import com.vhais.graph.FordFulkersonDfs;
import com.vhais.graph.Graph;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph(3);
		graph.addMice(new Mouse(1, 0));
		graph.addMice(new Mouse(0, 1));
		graph.addMice(new Mouse(8, 1));
		graph.addMice(new Mouse(12, 0));
		graph.addMice(new Mouse(12, 4));
		graph.addMice(new Mouse(15, 5));
		graph.addHole(new Hole(1,1,1));
		graph.addHole(new Hole(10,2,2));
		graph.addHole(new Hole(14,5,1));
		graph.finish();
		FordFulkersonDfs solver = new FordFulkersonDfs(graph);
		System.out.println(solver.solve());
	}
}

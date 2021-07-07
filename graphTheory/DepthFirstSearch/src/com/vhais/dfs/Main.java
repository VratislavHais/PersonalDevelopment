package com.vhais.dfs;

public class Main {

	public static void main(String[] args) {
		DepthFirstSearch dfs = new DepthFirstSearch(5);
		dfs.addUndirectedEdge(0, 1);
		dfs.addUndirectedEdge(0, 2);
		dfs.addUndirectedEdge(1, 2);
		dfs.addUndirectedEdge(1, 3);
		dfs.addUndirectedEdge(2, 3);
		dfs.addUndirectedEdge(2, 2);
		System.out.println(dfs.dfs(3));
	}
}

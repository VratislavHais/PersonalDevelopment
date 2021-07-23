package com.vhais;

import com.vhais.graph.Node;
import com.vhais.graph.Position;

public class Hole extends Node {
	public final int capacity;
	private static int id = 0;

	public Hole(int x, int y, int capacity) {
		super("hole" + id++, new Position(x, y));
		this.capacity = capacity;
	}
}

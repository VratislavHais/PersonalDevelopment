package com.vhais;

import com.vhais.graph.Node;
import com.vhais.graph.Position;

public class Mouse extends Node {
	private static int id = 0;
	public Mouse(int x, int y) {
		super("mouse" + id++, new Position(x, y));
	}
}

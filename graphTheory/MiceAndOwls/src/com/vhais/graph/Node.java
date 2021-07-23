package com.vhais.graph;

import java.util.Objects;

public class Node {
	public final String id;
	private final Position position;

	public Node(String id, Position position) {
		this.id = id;
		this.position = position;
	}

	public Position position() {
		return this.position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return id.equals(node.id) &&
				Objects.equals(position, node.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, position);
	}
}

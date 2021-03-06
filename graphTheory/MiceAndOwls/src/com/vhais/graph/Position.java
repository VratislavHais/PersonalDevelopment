package com.vhais.graph;

import java.util.Objects;

public class Position {
	public final int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isInRadius(Position position, int radius) {
		return (Math.abs(this.x - position.x) <= radius) &&
				(Math.abs(this.y - position.y) <= radius);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return x == position.x &&
				y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

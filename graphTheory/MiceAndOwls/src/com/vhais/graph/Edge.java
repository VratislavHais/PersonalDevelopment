package com.vhais.graph;

public class Edge {
	public final Node from, to;
	private final int maxCapacity;
	private int flow;
	private Edge residual;

	public Edge(Node from, Node to, int maxCapacity) {
		this.from = from;
		this.to = to;
		this.maxCapacity = maxCapacity;
		this.flow = 0;
	}

	public void setResidual(Edge residual) {
		if (this.residual == null) {
			this.residual = residual;
		}
	}

	public Edge residual() {
		return this.residual;
	}

	public boolean isResidual() {
		return this.maxCapacity == 0;
	}

	public int flow() {
		return this.flow;
	}

	public int remaining() {
		return this.maxCapacity - this.flow;
	}

	public void augment(int value) {
		this.flow += value;
		this.residual.flow -= value;
	}
}

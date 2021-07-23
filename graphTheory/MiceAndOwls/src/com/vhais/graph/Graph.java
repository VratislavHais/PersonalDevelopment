package com.vhais.graph;

import com.vhais.Hole;
import com.vhais.Mouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	private final Map<Node, List<Edge>> graph;
	private final Set<Mouse> mice;
	private final Set<Hole> holes;
	private final Node source, sink;
	private final int radius;
	private boolean isFinished;

	public Graph(int maxRadius) {
		graph = new HashMap<>();
		mice = new HashSet<>();
		holes = new HashSet<>();
		isFinished = false;
		this.source = new Node("source", new Position(0, 0));
		this.sink = new Node("sink", new Position(10, 10));
		this.radius = maxRadius;
	}

	public int getNumberOfNodes() {
		return graph.keySet().size();
	}

	public Node source() {
		return this.source;
	}

	public Node sink() {
		return this.sink;
	}

	public Set<Node> getNodes() {
		return graph.keySet();
	}

	public List<Edge> getEdges(Node node) {
		return graph.get(node);
	}

	public void addMice(Mouse mouse) {
		if (!isFinished) {
			addEdge(this.source, mouse, 1);
			this.mice.add(mouse);
		}
	}

	public void addHole(Hole hole) {
		if (!isFinished) {
			addEdge(hole, this.sink, hole.capacity);
			this.holes.add(hole);
		}
	}

	private void addEdge(Node from, Node to, int capacity) {
		if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
		if (!graph.containsKey(to)) graph.put(to, new ArrayList<>());
		Edge normal = new Edge(from, to, capacity);
		Edge residual = new Edge(to, from, 0);
		normal.setResidual(residual);
		residual.setResidual(normal);
		graph.get(from).add(normal);
		graph.get(to).add(residual);
	}

	public void finish() {
		if (!isFinished) {
			isFinished = true;
			miceToHoleConnections();
		}
	}

	public void edit() {
		isFinished = false;
	}

	public boolean isFinished() {
		return this.isFinished;
	}

	private void miceToHoleConnections() {
		for (Mouse mouse : mice) {
			for (Hole hole : holes) {
				if (mouse.position().isInRadius(hole.position(), radius)) {
					addEdge(mouse, hole, 1);
				}
			}
		}
	}
}

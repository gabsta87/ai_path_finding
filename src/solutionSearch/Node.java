package solutionSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import problems.Problem;

public class Node implements Comparable<Node>{
	private Node parent;
	protected Node[] children;
	private Action action;
	protected Problem problem;
	private double cost;
	
	public Node(Problem p) {
		this.parent = null;
		this.action = null;
		this.problem = p;
		this.cost = 0;
	}
	
	public Node(Node parent, Problem p, Action a) {
		this.parent = parent;
		this.problem = p;
		this.action = a;
		this.action.setEnvironment(p);
		this.cost = parent.cost;
		if(a.execute())
			this.cost += a.getCost();
	}
	
	/**
	 * @return The children of this node
	 */
	public List<Node> exploreNode(){
		List<Action> actionsAllowed = problem.getAvailableActions();
		
		List<Node> result = new ArrayList<Node>();
		
		for(int i = 0; i<actionsAllowed.size(); i++) {
			result.add(new Node(this,problem.clone(),actionsAllowed.get(i)));
		}
		return result;
	}
	
	/**
	 * @return The node's path that leads to this node
	 */
	public List<Node> getSolution(){
		Node currentNode = this;
		List<Node> solution = new LinkedList<Node>();
		
		solution.add(this);
		
		while(currentNode.parent != null) {
			currentNode = currentNode.parent;
			solution.add(currentNode);
		}
		
		return solution;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public boolean isSolved() {
		return problem.isSolved();
	}
	
	public double getCost() {
		return cost;
	}
	
	public Problem getProblem() {
		return problem;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!this.getClass().isInstance(o))
			return false;
		
		Node n = ((Node)o);
		
		return problem.equals(n.problem) && this.getCost() == n.getCost();
	}
	
	@Override
	public String toString() {
		return "NODE:{ACTION:"+action.toString()+";PROBLEM:"+problem.toString()+"}";
	}

	@Override
	public int compareTo(Node o) {
		return Double.compare(getCost(), o.getCost());
	}
	
}

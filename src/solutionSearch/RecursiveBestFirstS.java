package solutionSearch;

import java.util.LinkedList;
import java.util.List;

import problems.Problem;

public class RecursiveBestFirstS extends PathFinder {
	private static int nodesCount = 0;

	public RecursiveBestFirstS(Problem t) {
		super(t);
	}

	@Override
	public Answer findRoute() {
		NodePlus root = new NodePlus(super.problem);
		nodesCount = 1;
		return getAnswer(root, Double.POSITIVE_INFINITY);
	}
	
	private Answer getAnswer(NodePlus node, double limit) {
		
		if(node.isSolved()) {
			return new Success(node,nodesCount);
		}
		
		List<NodePlus> children = node.exploreNodePlus();
		
		if(children.isEmpty())
			return new Failure(Double.POSITIVE_INFINITY);
		
		for(NodePlus childNode : children) {
			childNode.setfCost(Math.max(node.getfCost(),childNode.getfCost()));
		}

		while(true) {
			children.sort(null);

			if(children.get(0).getfCost() > limit) 
				return new Failure(children.get(0).getfCost());
			Answer a = getAnswer(children.get(0), findValue(limit, children));
			if(a.isSuccess()) {
				return a;
			}else {
				children.get(0).setfCost(((Failure)a).fLimit);
			}
		}
	}

	private double findValue(double limit, List<NodePlus> children) {
		double value = 0;
		if(children.size() > 1)
			value = Math.min(limit,children.get(1).getfCost());
		else
			value = limit;
		return value;
	}
	
	class NodePlus extends Node{
		private double fCost;
		public NodePlus(NodePlus parent, Problem p, Action a) {
			super(parent, p, a);
			setfCost(p.getHeuristicToGoal() + getCost());
		}
		
		public NodePlus(Problem p) {
			super(p);
		}
		
		public double getfCost() {
			return this.fCost;
		}
		
		public void setfCost(double fCost) {
			this.fCost = fCost;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(fCost, ((NodePlus)o).fCost);
		}

		/**
		 * @return The children of this node, sorted by node's cost
		 */
		public List<NodePlus> exploreNodePlus(){
			List<Action> actionsAllowed = problem.getAvailableActions();
			List<NodePlus> result = new LinkedList<RecursiveBestFirstS.NodePlus>();
			nodesCount ++;

			children = new NodePlus[actionsAllowed.size()];
			
			for(int i = 0; i<actionsAllowed.size(); i++) {
				children[i] = new NodePlus(this,problem.clone(),actionsAllowed.get(i));
				result.add((NodePlus) children[i]);
			}
			return result;
		}
		
		@Override
		public String toString() {
			return super.toString() +"fCost = "+fCost+"; g = "+getCost()+"; h = "+getProblem().getHeuristicToGoal();
		}
	}

	public String getName() {
		return "Recursive best first search";
	}
}

package solutionSearch;

import java.util.ArrayList;
import java.util.List;

import problems.Problem;

public class AStar extends PathFinder {

	public AStar(Problem p) {
		super(p);
	}
	
	public Answer findRoute(){
		NodePlus actualNode = new NodePlus(problem);
		
		List<NodePlus> frontier = actualNode.exploreNodePlus();
		nodesCount ++;
		
		for(NodePlus n : frontier) {
			n.setEvaluatedCost(n.getCost()+n.getEstimationToGoal());
		}
		
		while(!actualNode.isSolved()) {
			if(frontier.isEmpty())
				return new Failure(nodesCount);

			frontier.sort(null);

			actualNode = frontier.remove(0);
			nodesCount ++;
			
			for(NodePlus n : actualNode.exploreNodePlus()) {
				n.setEvaluatedCost(n.getCost()+n.getEstimationToGoal());
				frontier.add(n);
			}
		}
		return new Success(actualNode,nodesCount);
	}

	public String getName() {
		return "A* search";
	}
	
	class NodePlus extends Node{
		private double evaluatedCost;

		public NodePlus(Node parent, Problem p, Action a) {
			super(parent, p, a);
			this.evaluatedCost = this.getCost();
		}
		
		public NodePlus(Problem p) {
			super(p);
			this.evaluatedCost = this.getCost();
		}

		public double getEstimationToGoal() {
			return super.problem.getHeuristicToGoal();
		}

		public double getEvaluatedCost() {
			return evaluatedCost;
		}

		public void setEvaluatedCost(double evaluatedCost) {
			this.evaluatedCost = evaluatedCost;
		}
		
		@Override
		public int compareTo(Node o) {
			return Double.compare(getEvaluatedCost(), ((NodePlus)o).getEvaluatedCost());
		}

		/**
		 * @return The children of this node, sorted by node's cost
		 */
		public List<NodePlus> exploreNodePlus(){
			List<Action> actionsAllowed = problem.getAvailableActions();
			List<NodePlus> result = new ArrayList<AStar.NodePlus>();
			
			children = new NodePlus[actionsAllowed.size()];
			
			for(int i = 0; i<actionsAllowed.size(); i++) {
				children[i] = new NodePlus(this,problem.clone(),actionsAllowed.get(i));
				result.add((NodePlus) children[i]);
			}
			return result;
		}
		
		public String toString() {
			return super.toString() +" NODEPLUS:{f = "+ evaluatedCost +"; g = " + getCost() + "; h = " + getEstimationToGoal()+"}";
		}
	}
}


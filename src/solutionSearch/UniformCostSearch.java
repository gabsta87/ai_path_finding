package solutionSearch;

import java.util.List;

import problems.Problem;

public class UniformCostSearch extends PathFinder{

	public UniformCostSearch(Problem p) {
		super(p);
	}

	@Override
	public Answer findRoute() {
		Node actualNode = new Node(problem);
		List<Node> frontier = actualNode.exploreNode();
		nodesCount ++;
		
		while(!actualNode.isSolved()) {
			if(frontier.isEmpty())
				return new Failure(nodesCount);
			
			frontier.sort(null);
			
			actualNode = frontier.remove(0);
			nodesCount ++;
			
			for (Node n: actualNode.exploreNode()) {
				frontier.add(n);
			}
		}
		return new Success(actualNode,nodesCount);
	}

	@Override
	public String getName() {
		return "Uniform Cost Search";
	}
}

package solutionSearch;

import problems.Problem;

public abstract class PathFinder {
	protected Problem problem;
	protected int nodesCount;
	
	public PathFinder(Problem p) {
		this.problem = p;
		this.nodesCount = 0;
	}
	
	public abstract Answer findRoute();
	
	public abstract String getName();
}

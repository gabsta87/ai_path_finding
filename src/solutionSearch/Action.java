package solutionSearch;

import problems.Problem;

public abstract class Action {
	public abstract boolean execute();
	
	public abstract boolean setEnvironment(Problem p);
	
	public abstract double getCost();
	
	public abstract String toString();
}

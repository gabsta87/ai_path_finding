package problems;

import java.util.List;

import solutionSearch.Action;

public abstract class Problem implements Cloneable{
	
	public abstract boolean isSolved();

	public abstract List<Action> getAvailableActions();
	
	public abstract Problem clone();
	
	public abstract boolean equals(Object o);
	
	public abstract double getHeuristicToGoal();
	
}

package problems.puzzle;

public class PuzzleProblemAltered extends PuzzleProblem {
	
	public PuzzleProblemAltered(PuzzleProblem p) {
		this.grid = p.grid.clone();
	}

	/**
	 * @return The distance to goal computed according to the Manhattan distance
	 */
	@Override
	public double getHeuristicToGoal() {
		double result = super.getHeuristicToGoal();
		result += Math.random()*5;
		return result;
	}
}

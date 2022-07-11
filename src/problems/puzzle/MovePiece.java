package problems.puzzle;

import problems.Problem;
import solutionSearch.Action;

public class MovePiece extends Action {
	private Direction direction;
	private Grid grid;
	
	public MovePiece(Direction d) {
		this.direction = d;
	}

	@Override
	public boolean setEnvironment(Problem p) {
		if(p.getClass() != PuzzleProblem.class)
			return false;
		this.grid = ((PuzzleProblem)p).getGrid();
		return true;
	}
	
	@Override
	public boolean execute() {
		return this.grid.movePieceFrom(direction);
	}

	@Override
	public double getCost() {
		return 1;
	}

	@Override
	public String toString() {
		return ""+direction;
	}

}

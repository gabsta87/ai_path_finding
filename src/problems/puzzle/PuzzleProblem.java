package problems.puzzle;

import java.util.List;

import problems.Problem;
import solutionSearch.Action;

public class PuzzleProblem extends Problem{
	protected Grid grid;
	
	public PuzzleProblem() {
		this.grid = new Grid();
	}
	
	private PuzzleProblem(Grid grid) {
		this.grid = grid;
	}
	
	public void randomizeGridAuto() {
		int i = (int) (20 + Math.random() * 50);
		randomizeGrid(i);
	}
	
	public void randomizeGrid(int i) {
		
		for(int j = 0; j < i; j++) {
			int direction = (int) (Math.random()*4);
			switch(direction) {
				case 0:
					this.grid.movePieceFrom(Direction.DOWN);
					break;
				case 1:
					this.grid.movePieceFrom(Direction.UP);
					break;
				case 2:
					this.grid.movePieceFrom(Direction.LEFT);
					break;
				case 3:
					this.grid.movePieceFrom(Direction.RIGHT);
					break;
			}
		}
	}
	
	Grid getGrid() {
		return this.grid;
	}
	
	/**
	 * @return The distance to goal computed according to the Manhattan distance
	 */
	@Override
	public double getHeuristicToGoal() {
		int distanceToGoal = 0;
		
		int goalValue = 0;
		for(int i = 0; i < grid.getSize(); i++) {
			for(int j = 0; j < grid.getSize(); j++) {
				if(grid.getValue(j, i) != goalValue){
					distanceToGoal += findDistanceToGoal(grid.getValue(j, i),j,i);
				}
				goalValue++;
			}
		}
		return distanceToGoal;
	}
	
	/**
	 * @return The distance to goal computed according to the Manhattan distance
	 */
	private int findDistanceToGoal(int value, int x, int y) {
		int distance = 0;
		
		if(value <= 2)
			distance += Math.abs(y-0);
		else if(value <= 5)
			distance += Math.abs(y-1);
		else
			distance += Math.abs(y-2);
		
		if(value == 0 || value == 3 || value == 6)
			distance += Math.abs(x-0);
		else if(value == 1 || value == 4 || value == 7)
			distance += Math.abs(x-1);
		else
			distance += Math.abs(x-2);
		
		return distance;
	}

	public boolean isSolved() {
		if(grid.getValue(0,0) != Grid.EMPTY)
			return false;
		
		int counter = 0;
		
		for(int i = 0; i < grid.getSize(); i++) {
			for (int j = 0; j < grid.getSize(); j++) {
				if(counter == grid.getValue(j, i))
					counter++;
			}
		}
		
		return counter == grid.getSize()*grid.getSize();
	}
	
	@Override
	public PuzzleProblem clone() {
		return new PuzzleProblem(grid.clone());
	}

	@Override
	public List<Action> getAvailableActions() {
		return grid.getAvailableActions();
	}

	@Override
	public boolean equals(Object o) {
		if(!this.getClass().isInstance(o))
			return false;
		
		return grid.equals(((PuzzleProblem)o).grid);
	}
	
	@Override
	public String toString() {
		return grid.toString();
	}
}

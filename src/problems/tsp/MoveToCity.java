package problems.tsp;

import java.util.List;

import problems.Problem;
import solutionSearch.Action;

public class MoveToCity extends Action{
	private TravelingSalesmanProblem p;
	private City newCity;
	private double cost;
	
	public MoveToCity(City newCity, TravelingSalesmanProblem problem) {
		this.newCity = newCity;
		this.p = problem;
		
		List<City> visitedCities = p.getPath().getVisitedCities();
		if(visitedCities.size() > 0) {
			this.cost = newCity.distance(visitedCities.get(visitedCities.size()-1));
		}else {
			this.cost = newCity.distance(p.getStartingCity());
		}
	}

	@Override
	public boolean execute() {
		return p.moveToCity(newCity);
	}

	@Override
	public boolean setEnvironment(Problem p) {
		if(p.getClass() != TravelingSalesmanProblem.class)
			return false;
		this.p = (TravelingSalesmanProblem)p;
		return true;
	}

	@Override
	public double getCost() {
		return this.cost;
	}

	@Override
	public String toString() {
		return "MOVE TO "+newCity+" - "+getCost();
	}

}

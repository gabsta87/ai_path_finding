package problems.tsp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import problems.Problem;
import solutionSearch.Action;

public class TravelingSalesmanProblem extends Problem {
	private City[] cities;
	private Path path;
	private MinimumSpanningTree heuristic;
	
	private TravelingSalesmanProblem() {}
	
	public TravelingSalesmanProblem(int size) {
		cities = new City[size];

		for(int i = 0; i < cities.length; i++) {
			cities[i] = new City(i);
		}
		
		this.path = new Path();
		if(cities.length > 0)
			this.path.setStart(cities[0]);
		
		this.heuristic = new MinimumSpanningTree(Arrays.asList(cities));
	}
	
	public TravelingSalesmanProblem(City[] cities) {
		this.cities = cities;
		this.path = new Path();
		this.path.setStart(cities[0]);
		this.heuristic = new MinimumSpanningTree(Arrays.asList(cities));
	}
	
	public City getStartingCity() {
		return this.path.getStartingCity();
	}
	
	public City[] getCities() {
		return cities;
	}
	
	public boolean moveToCity(City c) {
		if(path.addCity(c)) {
			this.heuristic = new MinimumSpanningTree(getUnexploredCities(this));
			return true;
		}
		return false;
	}
	
	public void randomizeCoords(int tspGeographicMaxDistance) {
		int x,y;
		for(City c : getCities()) {
			x = (int) (Math.random()*tspGeographicMaxDistance);
			y = (int) (Math.random()*tspGeographicMaxDistance);
			c.setCoordinates(x,y);
		}
	}
	
	@Override
	public double getHeuristicToGoal() {
		return heuristic.getMSTCost();
	}

	@Override
	public boolean isSolved() {
		if(path.size() < cities.length)
			return false;
		
		if(!path.isCircular())
			return false;
		
		return true;
	}

	@Override
	public List<Action> getAvailableActions() {
		List<Action> result = new LinkedList<Action>();
		
		for(int i = 0; i < cities.length; i++) {
			if(!path.contains(cities[i]) 
					&& !(path.size() < cities.length-1 && cities[i].equals(getStartingCity()))) {
//					){
				result.add(new MoveToCity(cities[i],(TravelingSalesmanProblem)this.clone()));
			}
		}
		
		return result;
	}

	@Override
	public Problem clone() {
		TravelingSalesmanProblem p = new TravelingSalesmanProblem();
		p.cities = this.cities;
		p.path = this.path.clone();
		
		List<City> remainingCities = getUnexploredCities(p);
		
		p.heuristic = new MinimumSpanningTree(remainingCities);
		return p;
	}

	private List<City> getUnexploredCities(TravelingSalesmanProblem p) {
		List<City> remainingCities = new LinkedList<City>();
		for(City c : cities) {
			if(!p.path.getVisitedCities().contains(c))
				remainingCities.add(c);
		}
		return remainingCities;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o.getClass() != this.getClass())
			return false;
		TravelingSalesmanProblem p = ((TravelingSalesmanProblem)o);
		return p.cities == this.cities && p.path.equals(this.path);
	}
	
	@Override
	public String toString() {
		return path.toString();
	}

	public Path getPath() {
		return path;
	}
}

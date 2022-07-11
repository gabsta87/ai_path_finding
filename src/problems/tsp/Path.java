package problems.tsp;

import java.util.LinkedList;
import java.util.List;

public class Path implements Cloneable{
	private City startCity;
	private List<City> visitedCities;
	private double pathCost;
	
	public Path() {
		this.visitedCities = new LinkedList<City>();
		this.pathCost = 0;
	}
	
	public void setStart(City c) {
		this.startCity = c;
	}
	
	public City getStartingCity() {
		return startCity;
	}
	
	public List<City> getVisitedCities(){
		return visitedCities;
	}
	
	public boolean addCity(City newCity) {
		if(visitedCities.contains(newCity))
			return false;
			
		if(visitedCities.size() > 0) {
			this.pathCost += newCity.distance(visitedCities.get(visitedCities.size()-1));
		}else {
			this.pathCost += newCity.distance(startCity);
		}
		
		visitedCities.add(newCity);
		
		return true;
	}
	
	public boolean contains(City c) {
		return visitedCities.contains(c);
	}
	
	public double getCost() {
		return pathCost;
	}
	
	public int size() {
		return visitedCities.size();
	}
	
	public boolean isCircular() {
		return visitedCities.get(visitedCities.size()-1).equals(startCity);
	}
	
	@Override
	public Path clone() {
		Path p = new Path();
		p.startCity = this.startCity;
		p.visitedCities = new LinkedList<City>();
		for(City c : this.visitedCities) {
			p.visitedCities.add(c);
		}
		p.pathCost = this.pathCost;
		return p;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o.getClass() != this.getClass())
			return false;
		
		Path p = (Path)o;
		
		if(!startCity.equals(p.startCity))
			return false;
		
		return visitedCities.equals(p.visitedCities);
	}
	
	@Override
	public String toString() {
		return "Visited cities : "+startCity+visitedCities+" - Path cost = "+this.pathCost;
	}
}

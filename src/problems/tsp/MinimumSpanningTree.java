package problems.tsp;

import java.util.LinkedList;
import java.util.List;

public class MinimumSpanningTree {
	private List<City> cities;
	private List<List<City>> subtrees;
	private List<Edge> edges;
	private double cost;
	
	public MinimumSpanningTree(List<City> cities) {
		this.cities = cities;
		this.subtrees = new LinkedList<List<City>>();
		this.edges = new LinkedList<MinimumSpanningTree.Edge>();
		this.cost = 0;
		if(cities.size() > 1)
			buildMST();
	}
	
	public double getMSTCost() {
		return this.cost;
	}
	
	public List<City> getCities(){
		return this.cities;
	}
	
	public List<Edge> getEdges(){
		return this.edges;
	}
	
	private void buildMST() {
		List<City> tempCities = new LinkedList<City>();
		for(City c : cities) {
			tempCities.add(c);
		}
		
		List<Edge> allEdges = new LinkedList<MinimumSpanningTree.Edge>();
		
		computeAllEdges(tempCities, allEdges);
		
		Edge cheapestEdge;
		int firstCityIndex, secondCityIndex, counter;
		
		do {
			firstCityIndex = -1;
			secondCityIndex = -1;
			counter = 0;
			
			cheapestEdge = allEdges.remove(0);
			
			// Search edge's cities among already explored cities
			for(List<City> l : subtrees) {
				if(l.contains(cheapestEdge.start))
					firstCityIndex = counter;
				if(l.contains(cheapestEdge.end))
					secondCityIndex = counter;
				counter++;
			}
			
			// If edge's cities are still not contained, it means we create a new subset(sub-tree)
			if(firstCityIndex == -1 && secondCityIndex == -1) {
				LinkedList<City> l = new LinkedList<City>();
				l.add(cheapestEdge.start);
				l.add(cheapestEdge.end);
				edges.add(cheapestEdge);
				subtrees.add(l);
			// Else if the edge is connecting two subsets, we transfer his cities in
			// the first subset, and remove the second subset
			}else if(firstCityIndex != -1 && secondCityIndex != -1) {
				if(firstCityIndex != secondCityIndex) {
					// We transfer the cities from one set to the other
					List<City> l = subtrees.get(secondCityIndex);
					for(City c : l) 
						subtrees.get(firstCityIndex).add(c);
					subtrees.remove(secondCityIndex);
					edges.add(cheapestEdge);
				}
				// If the two cities belong to the same subtree we don't add the edge, since it will create a loop
			}else {
//			}else if(firstCityContained == -1 && secondCityContained != -1) {
//			}else if(firstCityContained != -1 && secondCityContained == -1) {
				int index = -1;
				
				if(firstCityIndex != -1) {
					index = firstCityIndex;
				}else {
					index = secondCityIndex;
				}
				
				List<City> setToComplete = subtrees.get(index);
				if(!setToComplete.contains(cheapestEdge.start))
					setToComplete.add(cheapestEdge.start);
				if(!setToComplete.contains(cheapestEdge.end))
					setToComplete.add(cheapestEdge.end);
				edges.add(cheapestEdge);
			}
			
		}while(subtrees.size() != 1 || subtrees.get(0).size() != cities.size());
		
		for(Edge e : edges)
			cost += e.getCost();
	}

	private void computeAllEdges(List<City> tempCities, List<Edge> edges) {
		City temporaryCity;
		while(tempCities.size() > 0) {
			temporaryCity = tempCities.remove(0); 
			for(int i = 0; i < tempCities.size(); i++) {
				edges.add(new Edge(temporaryCity,tempCities.get(i)));
			}
		}
		edges.sort(null);
	}
	
	public class Edge implements Comparable<Edge>{
		City start, end;
		double cost;
		
		public Edge(City start, City end) {
			this.start = start;
			this.end = end;
			this.cost = start.distance(end);
		}
		
		public double getCost() {
			return this.cost;
		}
		
		public City getStart() {
			return this.start;
		}
		
		public City getEnd() {
			return this.end;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
}

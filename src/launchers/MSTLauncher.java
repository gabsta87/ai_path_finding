package launchers;

import java.util.LinkedList;
import java.util.List;

import problems.tsp.City;
import problems.tsp.MinimumSpanningTree;
import problems.tsp.graphics.MSTPainter;

public class MSTLauncher {
	private static int windowSize = 500;
	private static int citiesNumber = 7;
	
	public static void main(String[] args) {

		List<City> cities = new LinkedList<City>();
		for(int i = 0; i < citiesNumber; i++) {
			City c = new City(i);
			c.setCoordinates((int)(Math.random()*windowSize), (int)(Math.random()*windowSize));
			cities.add(c);
		}
		
		MinimumSpanningTree mst = new MinimumSpanningTree(cities);
		
		System.out.println("MST cost = "+mst.getMSTCost());
		
		MSTPainter.printSolution(mst, "Minimum spanning tree", windowSize);
		
		System.exit(0);
	}

}

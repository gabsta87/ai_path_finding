package launchers;

import problems.tsp.City;
import problems.tsp.TravelingSalesmanProblem;
import problems.tsp.graphics.TSPPainter;
import solutionSearch.AStar;
import solutionSearch.RecursiveBestFirstS;
import solutionSearch.UniformCostSearch;
import util.ProblemPrinter;

public class TSPBugFinder {

	public static void main(String[] args) {
		int geographicSize = 400;
		
//		City[] cities = new City[8];
//		cities[0] = new City(0);
//		cities[0].setCoordinates(18,179);
//		cities[1] = new City(1);
//		cities[1].setCoordinates(5,383);
//		cities[2] = new City(2);
//		cities[2].setCoordinates(371,41);
//		cities[3] = new City(3);
//		cities[3].setCoordinates(290,191);
//		cities[4] = new City(4);
//		cities[4].setCoordinates(348,207);
//		cities[5] = new City(5);
//		cities[5].setCoordinates(355,267);
//		cities[6] = new City(6);
//		cities[6].setCoordinates(285,57);
//		cities[7] = new City(7);
//		cities[7].setCoordinates(195,195);
		
		City[] cities = new City[4];
		cities[0] = new City(0);
		cities[0].setCoordinates(10,10);
		cities[1] = new City(1);
		cities[1].setCoordinates(110,10);
		cities[2] = new City(2);
		cities[2].setCoordinates(10,110);
		cities[3] = new City(3);
		cities[3].setCoordinates(110,110);
		
		TravelingSalesmanProblem p = new TravelingSalesmanProblem(cities);

		System.out.println("Starting with "+p.getCities().length+" cities");
		
		TSPPainter.printSolution(p, "Problem", geographicSize);

		ProblemPrinter.examineSolution(geographicSize, p,new AStar(p));
		
		ProblemPrinter.examineSolution(geographicSize, p,new UniformCostSearch(p));
		
		ProblemPrinter.examineSolution(geographicSize, p,new RecursiveBestFirstS(p));

		System.exit(0);
	}

}

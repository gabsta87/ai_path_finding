package launchers;

import problems.tsp.TravelingSalesmanProblem;
import problems.tsp.graphics.TSPPainter;
import solutionSearch.AStar;
import solutionSearch.RecursiveBestFirstS;
import solutionSearch.UniformCostSearch;
import util.ProblemPrinter;

public class TSPLauncher {
	
	public static void main(String[] args) {
		int citiesNumber = 12;
		int geographicSize = 400;
		
		TravelingSalesmanProblem p = new TravelingSalesmanProblem(citiesNumber);
		p.randomizeCoords(geographicSize);
		
//		ProblemPrinter.printProblemConfiguration(p);

		TSPPainter.printSolution(p, "Problem", geographicSize);
		
		ProblemPrinter.examineSolution(geographicSize, p,new AStar(p));
		
		ProblemPrinter.examineSolution(geographicSize, p,new RecursiveBestFirstS(p));
		
		ProblemPrinter.examineSolution(geographicSize, p,new UniformCostSearch(p));
		
		System.exit(0);
	}

}

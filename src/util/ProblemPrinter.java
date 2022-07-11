package util;

import java.awt.Frame;

import problems.tsp.TravelingSalesmanProblem;
import problems.tsp.graphics.TSPPainter;
import solutionSearch.Answer;
import solutionSearch.Node;
import solutionSearch.PathFinder;
import solutionSearch.Success;

public class ProblemPrinter {
	private static int windowPosition = 0;

	public static void printProblemConfiguration(TravelingSalesmanProblem p) {
		System.out.println("City[] cities = new City["+p.getCities().length+"];");

		for(int i = 0; i < p.getCities().length; i++) {
			System.out.println("cities["+i+"] = new City("+i+");");
			System.out.println("cities["+i+"].setCoordinates("+p.getCities()[i].getCoordinates().x+","+p.getCities()[i].getCoordinates().y+");");
		}
		System.out.println();
	}
	
	public static void examineSolution(int geographicSize, TravelingSalesmanProblem problem,PathFinder algorithm) {
		String algoName = algorithm.getName();
		System.out.println("Trying "+algoName+" on "+problem.getCities().length+" cities");
		long startTime = System.currentTimeMillis();
		Answer a = algorithm.findRoute();
		long endTime = System.currentTimeMillis();
		System.out.println("Execution time = "+ TimePrinter.getTime(endTime-startTime));
		examineSolution(a, algoName,geographicSize).setLocation(geographicSize*windowPosition++,0);
		System.out.println();
	}
	
	public static Frame examineSolution(Answer a, String algorithmName,int geographicSize) {
		if(!a.isSuccess()) {
			System.out.println("No solution was found");
			return null;
		}else {
			Success solution = (Success)a;
			System.out.println("Nodes generated = "+solution.getNodesCount());
			System.out.println("Total cost = "+ solution.getCost());
			
//			printNodeDetails(solution);
			
			return TSPPainter.printSolution((TravelingSalesmanProblem)solution.getLastNode().getProblem(),algorithmName,geographicSize);
		}
	}

	@SuppressWarnings("unused")
	private static void printNodeDetails(Success solution) {
		for(Node n : solution.getSolution()) {
			System.out.println(n);
		}
	}
}

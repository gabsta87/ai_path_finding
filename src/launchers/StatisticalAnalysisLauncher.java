package launchers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import problems.tsp.TravelingSalesmanProblem;
import solutionSearch.AStar;
import solutionSearch.Answer;
import solutionSearch.PathFinder;
import solutionSearch.RecursiveBestFirstS;
import solutionSearch.UniformCostSearch;
import util.TimePrinter;
import util.statistics.DataSet;
import util.statistics.Histogram;
import util.statistics.LogarithmicHistogram;

public class StatisticalAnalysisLauncher {
	private static int iterationsToCompute = 21;
	private static int tspSize = 8;
	private static int geographicSize = 400;

	public static void main(String[] args) {
		DataSet aStarNC = new DataSet("A*");
		DataSet rbfsNC = new DataSet("RBFS");
		DataSet ucsNC = new DataSet("UCS");

		DataSet aStarT = new DataSet("A*");
		DataSet rbfsT = new DataSet("RBFS");
		DataSet ucsT = new DataSet("UCS");

		List<Answer> aStarStats = new LinkedList<Answer>();
		List<Answer> rbfsStats = new LinkedList<Answer>();
		List<Answer> ucsStats = new LinkedList<Answer>();

		for (int i = 0; i < iterationsToCompute; i++) {
			System.out.println((i + 1) + " of " + (iterationsToCompute) + " iterations ");
			TravelingSalesmanProblem p = new TravelingSalesmanProblem(tspSize);
			p.randomizeCoords(geographicSize);

			Answer a = compute(new AStar(p));
			aStarT.addEntry((int) a.getExecutionTime(),""+i);
			aStarNC.addEntry(a.getNodesCount(),"");
			aStarStats.add(a);

			Answer b = compute(new RecursiveBestFirstS(p));
			rbfsT.addEntry((int) b.getExecutionTime(),""+i);
			rbfsNC.addEntry(b.getNodesCount(),"");
			rbfsStats.add(b);

			Answer c = compute(new UniformCostSearch(p));
			ucsT.addEntry((int) c.getExecutionTime(),""+i);
			ucsNC.addEntry(c.getNodesCount(),"");
			ucsStats.add(c);
		}
		
//		aStarNC.sort();
//		rbfsNC.sort();
//		ucsNC.sort();
//		
//		aStarT.sort();
//		rbfsT.sort();
//		ucsT.sort();
		
		Histogram.printHistogram("Nodes expanded", aStarNC, rbfsNC, ucsNC);

		Histogram.printHistogram("Execution times", aStarT, rbfsT, ucsT);
		
		LogarithmicHistogram.printHistogram("Nodes expanded", aStarNC, rbfsNC, ucsNC);

		LogarithmicHistogram.printHistogram("Execution times", aStarT, rbfsT, ucsT);

		System.out.println();
		System.out.println(iterationsToCompute + " iterations of a TSP with " + tspSize + " cities");
		System.out.println();

		System.out.println("AStar stats :");
		printAlgorithmStats(aStarStats);

		System.out.println("RBFS stats :");
		printAlgorithmStats(rbfsStats);

		System.out.println("UCS stats :");
		printAlgorithmStats(ucsStats);
	}

	private static void printAlgorithmStats(List<Answer> stats) {
		Answer meanNodesCountAnswer = getMeanNodesCountAnswer(stats);
		int meanNodesCount = meanNodesCountAnswer.getNodesCount();
		int minNodesCount = stats.get(0).getNodesCount();
		int maxNodesCount = stats.get(stats.size() - 1).getNodesCount();
		Answer meanTimeAnswer = getMeanTimeAnswer(stats);
		long meanTime = meanTimeAnswer.getExecutionTime();
		long minTime = stats.get(0).getExecutionTime();
		long maxTime = stats.get(stats.size() - 1).getExecutionTime();

		long averageTime = getAverageTime(stats);
		int averageNodesCount = getAverageNodesCount(stats);

		System.out.println("Min-Max times = " + TimePrinter.getTime(minTime) + "-" + TimePrinter.getTime(maxTime));
		System.out.println("Mean time = " + TimePrinter.getTime(meanTime));
		System.out.println("Average time = " + TimePrinter.getTime(averageTime));
		System.out.println("Min-Max nodes count = " + minNodesCount + "-" + maxNodesCount);
		System.out.println("Mean nodes count = " + meanNodesCount);
		System.out.println("Average nodes count = " + averageNodesCount);
//		System.out.println(stats);
		System.out.println();
	}

	private static long getAverageTime(List<Answer> stats) {
		int totaltime = 0;
		for (Answer a : stats) {
			totaltime += a.getExecutionTime();
		}
		return totaltime / stats.size();
	}

	private static int getAverageNodesCount(List<Answer> stats) {
		int totalNodesCount = 0;
		for (Answer a : stats) {
			totalNodesCount += a.getNodesCount();
		}
		return totalNodesCount / stats.size();
	}

	private static Answer getMeanNodesCountAnswer(List<Answer> stats) {
		stats.sort(new Comparator<Answer>() {
			@Override
			public int compare(Answer o1, Answer o2) {
				return Double.compare(o1.getNodesCount(), o2.getNodesCount());
			}
		});
		return stats.get(stats.size() / 2);
	}

	private static Answer getMeanTimeAnswer(List<Answer> stats) {
		stats.sort(new Comparator<Answer>() {
			@Override
			public int compare(Answer o1, Answer o2) {
				return Double.compare(o1.getExecutionTime(), o2.getExecutionTime());
			}
		});
		return stats.get(stats.size() / 2);
	}

	private static Answer compute(PathFinder algorithm) {
		long startTime = System.currentTimeMillis();
		Answer a = algorithm.findRoute();
		long endTime = System.currentTimeMillis();
		a.setExecutionTime(endTime - startTime);
		return a;
	}
}

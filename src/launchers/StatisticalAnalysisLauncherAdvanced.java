package launchers;

import java.util.LinkedList;
import java.util.List;

import problems.tsp.TravelingSalesmanProblem;
import solutionSearch.AStar;
import solutionSearch.Answer;
import solutionSearch.PathFinder;
import solutionSearch.RecursiveBestFirstS;
import solutionSearch.UniformCostSearch;
import util.statistics.DataManager;
import util.statistics.DataSet;
import util.statistics.Histogram;
import util.statistics.LogarithmicHistogram;

@SuppressWarnings("unused")
public class StatisticalAnalysisLauncherAdvanced {
	private static int iterationsToCompute = 21;
	private static int tspSizeMin = 5, tspSizeMax = 9;
	private static int geographicSize = 400;

	public static void main(String[] args) {
		
		DataSet aStarMeanTimes = new DataSet("A* Mean");
		DataSet aStarAvTimes = new DataSet("A* Average");
		DataSet aStarMeanNodesCount = new DataSet("A* Mean");
		DataSet aStarAvNodesCount = new DataSet("A* Average");
		List<Integer> aStarTimes = new LinkedList<Integer>();
		List<Integer> aStarNodesCounts = new LinkedList<Integer>();
		
		DataSet rbfsMeanTimes = new DataSet("RBFS Mean");
		DataSet rbfsAvTimes = new DataSet("RBFS Average");
		DataSet rbfsMeanNodesCount = new DataSet("RBFS Mean");
		DataSet rbfsAvNodesCount = new DataSet("RBFS Average");
		List<Integer> rbfsTimes = new LinkedList<Integer>();
		List<Integer> rbfsNodesCounts = new LinkedList<Integer>();
		
//		DataSet ucsMeanNodesCount = new DataSet("UCS");
//		DataSet ucsMeanTimes = new DataSet("UCS");
//		DataSet ucsAvTimes = new DataSet("UCS");
//		DataSet ucsAvNodesCount = new DataSet("UCS");
//		List<Integer> ucsTimes = new LinkedList<Integer>();
//		List<Integer> ucsNodesCounts = new LinkedList<Integer>();
//		
		for (int j = tspSizeMin; j < tspSizeMax + 1; j++) {
			aStarTimes.clear();
			aStarNodesCounts.clear();

			rbfsTimes.clear();
			rbfsNodesCounts.clear();
			
//			ucsTimes.clear();
//			ucsNodesCounts.clear();

			for (int i = 0; i < iterationsToCompute; i++) {
				System.out.println((i + 1) + " of " + (iterationsToCompute) + " iterations for TSP of size "+j+" (up to "+tspSizeMax+")");
				TravelingSalesmanProblem p = new TravelingSalesmanProblem(j);
				p.randomizeCoords(geographicSize);

				Answer a = compute(new AStar(p));
				aStarTimes.add((int) a.getExecutionTime());
				aStarNodesCounts.add(a.getNodesCount());

				Answer b = compute(new RecursiveBestFirstS(p));
				rbfsTimes.add((int) b.getExecutionTime());
				rbfsNodesCounts.add(b.getNodesCount());

//				Answer c = compute(new UniformCostSearch(p));
//				ucsTimes.add((int) c.getExecutionTime());
//				ucsNodesCounts.add(c.getNodesCount());
			}

			aStarMeanTimes.addEntry((int) DataManager.getMean(aStarTimes),""+j);
			aStarAvTimes.addEntry((int) DataManager.getAverage(aStarTimes),""+j);
			aStarMeanNodesCount.addEntry((int) DataManager.getMean(aStarNodesCounts),""+j);
			aStarAvNodesCount.addEntry((int) DataManager.getAverage(aStarNodesCounts),""+j);
			
			rbfsMeanTimes.addEntry((int) DataManager.getMean(rbfsTimes),""+j);
			rbfsAvTimes.addEntry((int) DataManager.getAverage(rbfsTimes),""+j);
			rbfsMeanNodesCount.addEntry((int) DataManager.getMean(rbfsNodesCounts),""+j);
			rbfsAvNodesCount.addEntry((int) DataManager.getAverage(rbfsNodesCounts),""+j);
			
//			ucsMeanTimes.addEntry((int) DataManager.getMean(ucsTimes),""+j);
//			ucsAvTimes.addEntry((int) DataManager.getAverage(ucsTimes),""+j);
//			ucsMeanNodesCount.addEntry((int) DataManager.getMean(ucsTimes),""+j);
//			ucsAvNodesCount.addEntry((int) DataManager.getAverage(ucsTimes),""+j);
		}
		
//		Histogram.printHistogram("Mean Times", windowHeigth, windowWidth, aStarMeanTimes, rbfsMeanTimes, ucsMeanTimes)
		Histogram.printHistogram("Times for TSP", aStarMeanTimes,aStarAvTimes,rbfsMeanTimes,rbfsAvTimes);

		LogarithmicHistogram.printHistogram("Times for TSP",aStarMeanTimes,aStarAvTimes,rbfsMeanTimes,rbfsAvTimes);
		
//		Histogram.printHistogram("Mean Nodes Counts", windowHeigth, windowWidth, aStarMeanNodesCount, rbfsMeanNodesCount, ucsMeanNodesCount)
		Histogram.printHistogram("Nodes Counts for TSP", aStarMeanNodesCount,aStarAvNodesCount, rbfsMeanNodesCount,rbfsAvNodesCount);
		
	}

	private static Answer compute(PathFinder algorithm) {
		long startTime = System.currentTimeMillis();
		Answer a = algorithm.findRoute();
		long endTime = System.currentTimeMillis();
		a.setExecutionTime(endTime - startTime);
		return a;
	}
}

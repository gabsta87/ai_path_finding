package launchers;

import problems.puzzle.PuzzleProblem;
import problems.puzzle.PuzzleProblemAltered;
import solutionSearch.AStar;
import solutionSearch.Answer;
import solutionSearch.Node;
import solutionSearch.PathFinder;
import solutionSearch.RecursiveBestFirstS;
import solutionSearch.Success;
import util.TimePrinter;

public class PuzzleLauncher {

	public static void main(String[] args) {
		PuzzleProblem p = new PuzzleProblem();
		p.randomizeGridAuto();
		PuzzleProblem p2 = p.clone();

		System.out.println("Puzzle problem");
		examineSolution(p2,new AStar(p2));
		examineSolution(p,new RecursiveBestFirstS(p));

		PuzzleProblemAltered p3 = new PuzzleProblemAltered(p);
		p3.randomizeGridAuto();

		System.out.println("Altered puzzle problem");
		examineSolution(p3,new RecursiveBestFirstS(p3));

	}

	private static void examineSolution(PuzzleProblem p, PathFinder algorithm) {
		long startTime = System.currentTimeMillis();
		Answer a = algorithm.findRoute();
		long endTime = System.currentTimeMillis();
		printResultMinimal(p, a, algorithm.getName(),(endTime-startTime));
		System.out.println();
	}

	private static void printResultMinimal(PuzzleProblem p, Answer a, String algoName,long timeInMS) {
		Success s;
		if(a.isSuccess()) {
			s = ((Success)a);
			
			System.out.println(algoName);
			System.out.println("A solution was found in "+s.getSolution().size()+" steps and in "+TimePrinter.getTime(timeInMS));
		}
	}
	
	@SuppressWarnings("unused")
	private static void printResultDetailed(PuzzleProblem p, Answer a, String algoName,long timeInMS) {
		Success s;
		if(a.isSuccess()) {
			s = ((Success)a);
			
			System.out.print("Initial state");
			System.out.println(p);
			
			printResultMinimal(p,a,algoName,timeInMS);
			
			for(Node n : s.getSolution()) {
				System.out.println(n);
				System.out.println();
			}
		}
	}
}

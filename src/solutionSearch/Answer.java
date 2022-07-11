package solutionSearch;

public abstract class Answer{
	private long executionTime;
	protected int nodesCount = 0;
	
	public Answer() {}
	
	public Answer(int nodesCount) {
		this.nodesCount = nodesCount;
	}
	
	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
	
	public int getNodesCount() {
		return this.nodesCount;
	}

	public boolean isSuccess() {
		return false;
	}

	public boolean isCutOff() {
		return false;
	}

	public boolean isFailure() {
		return false;
	}
	
	@Override
	public abstract String toString();
	
}

class Failure extends Answer{
	double fLimit;

	public Failure(double newLimit) {
		this.fLimit = newLimit;
	}
	
	public Failure(int nodesCount) {
		super(nodesCount);
		this.fLimit = -1;
	}

	@Override
	public String toString() {
		return "Failure" +(fLimit>0?":"+fLimit:"");
	}

	@Override
	public boolean isFailure() {
		return true;
	}

}

class CutOff extends Answer{
	public CutOff() {}

	@Override
	public boolean isCutOff() {
		return true;
	}

	@Override
	public String toString() {
		return "CutOff";
	}
}
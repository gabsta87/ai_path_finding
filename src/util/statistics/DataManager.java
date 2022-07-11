package util.statistics;

import java.util.List;

public class DataManager {
	
	public static double getMeanValuePrecise(List<Integer> data) {
		data.sort(null);
		if(data.size()%2==0) {
			double total = data.get(data.size()/2)+data.get(data.size()/2-1);
			return total/2;
		}else {
			return data.get(data.size()/2);
		}
	}
	
	public static double getMean(List<Integer> data) {
		data.sort(null);
		return data.get(data.size()/2);
	}
	
	public static double getAverage(List<Integer> data) {
		double total = 0;
		for(Integer a : data) {
			total += a;
		}
		return total/data.size();
	}

	static int max(DataSet[] datas) {
		int result = 0;
		for(DataSet s : datas) {
			result = Math.max(result, s.getMaxValue());
		}
		return result;
	}
	
	static int max(List<Integer>[] datas2) {
		int result = 0;
		for(List<Integer> l : datas2)
			result = Math.max(result, max(l));
		return result;
	}

	static int max(List<Integer> data2) {
		int result = 0;
		for(Integer i : data2) {
			result = Math.max(i, result);
		}
		return result;
	}

	static int average(List<Integer> data2) {
		int total = 0;
		for(int i : data2) {
			total += i;
		}
		int result = total /data2.size();
		return result;
	}
}
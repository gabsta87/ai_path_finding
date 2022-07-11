package util.statistics;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class DataSet {
	private String title;
	private List<Entry> data;
	
	public DataSet(String title) {
		this.data = new LinkedList<Entry>();
		this.title = title;
	}
	
	public void sort() {
		data.sort(new Comparator<Entry>() {
			@Override
			public int compare(Entry o1, Entry o2) {
				return Integer.compare(o1.value, o2.value);
			}
		});
	}

	public void addEntry(int value, String label) {
		this.data.add(new Entry(value, label));
	}
	
	List<Entry> getData() {
		return data;
	}
	
	int getMaxValue() {
		int result = 0;
		for(Entry e : data) {
			result = Math.max(result, e.value);
		}
		return result;
	}

	public String getTitle() {
		return title;
	}
	
	public boolean isSorted() {
		for(int i = 0; i < data.size()-1; i++) {
			if(data.get(i).value > data.get(i+1).value)
				return false;
		}
		return true;
	}
	
	class Entry{
		int value;
		String label;
		Entry(int value, String label){
			this.value = value;
			this.label = label;
		}
	}
	
	public static int average(DataSet set) {
		int total = 0;
		for(Entry i : set.data) {
			total += i.value;
		}
		int result = total / set.data.size();
		return result;
	}
}

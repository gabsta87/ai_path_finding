package util.statistics;

import java.awt.Frame;

public class LogarithmicHistogram extends Histogram {
	private static final long serialVersionUID = 6335880677589098463L;

	public LogarithmicHistogram(DataSet[] datas) {
		super(datas);
	}

	@Override
	protected int getHeight(int value) {
		return (int) Math.round(Math.log10(value*windowHeigth)/maxValue);
	}
	
	public static Frame printHistogram(String frameTitle,DataSet... data) {
		Frame f = new Frame();
		f.add(new LogarithmicHistogram(data));
		int frameWidth = windowWidth+50;
		int frameHeight = windowHeigth+50;
		f.setSize(frameWidth, frameHeight);
		f.setVisible(true);
		f.setTitle(frameTitle);
		return f;
	}
}

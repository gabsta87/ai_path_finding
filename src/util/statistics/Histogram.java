package util.statistics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.List;

import util.statistics.DataSet.Entry;

public class Histogram extends Component{
	protected static int windowHeigth = 300, windowWidth = 500;
	protected static int windowPositionX=0, windowPositionY=0;
	protected static int margin = 50;
	
	protected static final long serialVersionUID = -3190321517954535417L;
	protected DataSet[] datas;
	protected int stringHeight = 15,maxValue;
	protected Color[] colors = {Color.red,Color.blue,Color.green,Color.orange,Color.pink,Color.cyan,Color.magenta,Color.gray};

	public Histogram(DataSet... datas) {
		this.datas = datas;
		this.maxValue = DataManager.max(datas);
	}

	public void paint(Graphics g) {
		int maxStrLength = 0;
		int counter = 0;
		
		for(DataSet l : datas) {
			drawHistogram(g, l, counter, datas.length,colors[counter]);
			maxStrLength = Math.max(maxStrLength, l.getTitle().length());
			counter++;
		}
		
		// Adding the legend
		int margin = 3;
		int charWidth = 9;
		int legendWidth = maxStrLength*charWidth;
		int legendHeight = (stringHeight+margin)*datas.length;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, legendWidth, legendHeight);
		g.setColor(Color.black);
		g.drawRect(0, 0, legendWidth, legendHeight);
		
		for(int i = 0; i < datas.length; i++) {
			g.setColor(colors[i]);
			g.drawString(datas[i].getTitle(), margin, (1+i)*(stringHeight+margin/2));
		}
	}

	protected Graphics drawHistogram(Graphics g,DataSet set,int histogramPosition,int histogramsCount,Color c) {
		List<Entry>data = set.getData();
		boolean isSorted = set.isSorted();
		int rectHeigth, rectPosition;
		int rectWidth = windowWidth/data.size()/histogramsCount;
		
		for(int i = 0; i < data.size(); i++) {
			rectHeigth = getHeight(data.get(i).value);
			rectPosition = (i*windowWidth/data.size())+(rectWidth*histogramPosition);
			if(data.size()/2 == i && isSorted)
				g.setColor(c.darker());
			else
				g.setColor(c);
			g.fillRect(rectPosition,windowHeigth-rectHeigth,rectWidth,rectHeigth);
			
			g.setColor(c);
			g.drawString(data.get(i).label, rectPosition, windowHeigth+(stringHeight*(1+histogramPosition)));
			
			g.setColor(Color.black);
			
			g.drawString(""+data.get(i).value, rectPosition, (Math.max(10,windowHeigth-rectHeigth-5)));
			
			g.drawRect(rectPosition,windowHeigth-rectHeigth,rectWidth,rectHeigth);
		}
			
		int averageValue = DataSet.average(set);
		g.setColor(c);
		g.drawLine(0, windowHeigth-averageValue*windowHeigth/maxValue, windowWidth, windowHeigth-averageValue*windowHeigth/maxValue);
		return g;
	}

	protected int getHeight(int value) {
		return value*windowHeigth/maxValue;
	}
	
	public static Frame printHistogram(String frameTitle,DataSet... data) {
		Frame f = new Frame();
		f.add(new Histogram(data));
		int frameWidth = windowWidth+50;
		int frameHeight = windowHeigth+50;
		f.setSize(frameWidth, frameHeight);
		f.setVisible(true);
		f.setTitle(frameTitle);
//		f.setLocation((margin+windowWidth) * columnCounter++, (margin+windowHeigth) * lineCounter);
		f.setLocation(windowPositionX,windowPositionY);
		windowPositionX += f.getWidth();
//		System.get
		return f;
	}
}

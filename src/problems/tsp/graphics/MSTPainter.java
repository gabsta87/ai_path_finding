package problems.tsp.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import problems.tsp.City;
import problems.tsp.MinimumSpanningTree;
import problems.tsp.MinimumSpanningTree.Edge;

public class MSTPainter extends Component {
	private static final long serialVersionUID = -7753863532657533535L;
	private MinimumSpanningTree mst;
	private int circleSize = 16;
	
	public MSTPainter(MinimumSpanningTree t) {
		this.mst = t;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		for(City c : mst.getCities()) {
			g2d.setColor(Color.lightGray);
			g2d.fillOval(c.getCoordinates().x-circleSize/2, c.getCoordinates().y-circleSize/2, circleSize, circleSize);
			
			g2d.setColor(Color.black);
			g2d.drawOval(c.getCoordinates().x-circleSize/2, c.getCoordinates().y-circleSize/2, circleSize, circleSize);
			
			g2d.setColor(Color.black);
			g2d.drawString(""+c.getID(), c.getCoordinates().x-4, c.getCoordinates().y+4);
		}

		g2d.setColor(Color.red);
		City c1,c2;
		for(Edge e : mst.getEdges()) {
			c1 = e.getStart();
			c2 = e.getEnd();
			g2d.drawLine(c1.getCoordinates().x, c1.getCoordinates().y, c2.getCoordinates().x, c2.getCoordinates().y);
		}
	}

	public static Frame printSolution(MinimumSpanningTree t, String frameTitle, int geographicMaxDistance) {
		Frame f = new Frame();
		f.add(new MSTPainter(t));
		int frameWidth = geographicMaxDistance+50;
		int frameHeight = geographicMaxDistance+50;
		f.setSize(frameWidth, frameHeight);
		f.setVisible(true);
		f.setTitle(frameTitle);
		return f;
	}
}

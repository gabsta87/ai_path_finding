package problems.tsp.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import problems.tsp.City;
import problems.tsp.TravelingSalesmanProblem;

public class TSPPainter extends Component {
	private static final long serialVersionUID = -7753863532657533535L;
	private TravelingSalesmanProblem p;
	private int circleSize = 16;
	
	public TSPPainter(TravelingSalesmanProblem p) {
		this.p = p;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		for(City c : p.getCities()) {
			g2d.setColor(Color.lightGray);
			g2d.fillOval(c.getCoordinates().x-circleSize/2, c.getCoordinates().y-circleSize/2, circleSize, circleSize);
			
			g2d.setColor(Color.black);
			g2d.drawOval(c.getCoordinates().x-circleSize/2, c.getCoordinates().y-circleSize/2, circleSize, circleSize);
			
			g2d.setColor(Color.black);
			g2d.drawString(""+c.getID(), c.getCoordinates().x-4, c.getCoordinates().y+4);
		}

		if(p.getPath().size() == 0)
			return;
		
		g2d.setColor(Color.red);
		g2d.drawLine(
				p.getStartingCity().getCoordinates().x, 
				p.getStartingCity().getCoordinates().y, 
				p.getPath().getVisitedCities().get(0).getCoordinates().x, 
				p.getPath().getVisitedCities().get(0).getCoordinates().y);
		
		City c1,c2;
		
		for(int i = 0; i < p.getPath().getVisitedCities().size()-1; i++) {
			c1 = p.getPath().getVisitedCities().get(i);
			c2 = p.getPath().getVisitedCities().get(i+1);
			g2d.drawLine(c1.getCoordinates().x, c1.getCoordinates().y, c2.getCoordinates().x, c2.getCoordinates().y);
		}
	}

	public static Frame printSolution(TravelingSalesmanProblem p, String frameTitle, int geographicMaxDistance) {
		Frame f = new Frame();
		f.add(new TSPPainter(p));
		int frameWidth = geographicMaxDistance+50;
		int frameHeight = geographicMaxDistance+50;
		f.setSize(frameWidth, frameHeight);
		f.setVisible(true);
		f.setTitle(frameTitle);
		return f;
	}
}

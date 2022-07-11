package problems.tsp;

import java.awt.Point;

public class City {
	private int id;
	private Point coords;
	
	public City(int id) {
		this.id = id;
		this.coords = new Point();
	}
	
	public void setCoordinates(int x,int y) {
		this.coords.x = x;
		this.coords.y = y;
	}
	
	public Point getCoordinates() {
		return coords;
	}
	
	public double distance(City a) {
		return coords.distance(a.coords);
	}
	
	public String toString() {
		return ""+id+":["+coords.x+","+coords.y+"]";
	}

	public int getID() {
		return id;
	}
}

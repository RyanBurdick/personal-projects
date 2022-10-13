package game;

import java.awt.Point;
import java.util.Scanner;

public class Path 
{
	int x; 
	int y; 
	int size; 
	double length; 
	Point location; 
	Point path[];

	/**
	 * This constructor does the following: - It creates a new array (or
	 * ArrayList) to hold the path coordinates, and stores it in the path
	 * variable. - It reads a number of coordinates, n, from the scanner. - It
	 * loops n times, each time scanning a coordinate x,y pair, creating an
	 * object to represent the coordinate, and storing the coordinate object in
	 * the path.
	 * 
	 * @param s - a Scanner set up by the caller to provide a list of coordinates
	 */
	public Path (Scanner s)
	{
		size = s.nextInt(); 
		this.path = new Point[size]; 
		for (int i = 0; i < size; i++)
		{
			x = s.nextInt(); 
			y = s.nextInt(); 
			path[i] = new Point (x, y); 
		}
	}

	public Path (Point a, Point b)
	{
		size = 2;
		this.path = new Point[size]; 
		path[0] = a; 
		path[1] = b; 
	}

	/**
	 * Returns the total length of the path. Since the path is specified using
	 * screen coordinates, the length is in pixel units (by default).
	 * 
	 * @return the length of the path
	 */
	public double getPathLength (){

		for(int i = 0; i <  size-1; i++)
		{ 
			double x1= path[i].getX(); 
			double y1= path[i].getY(); 
			double x2= path[i+1].getX(); 
			double y2= path[i+1].getY(); 
			this.length = length + Math.sqrt( (Math.pow((x2 - x1) ,2)) + (Math.pow ((y2 - y1),2)) );
		}
		return length;

	}

	/**
	 * Given a percentage between 0% and 100%, this method calculates the
	 * location along the path that is exactly this percentage along the path.
	 * The location is returned in a Point object (int x and y), and the
	 * location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is returned. If
	 * the percentage is greater than 100%, the final position is returned.
	 * 
	 * If students don't want to use Point objects, they may write or use
	 * another object to represent coordinates.
	 *
	 * Caution: Students should never directly return a Point object from a path
	 * list. It could be changed by the outside caller. Instead, always create
	 * and return new point objects as the result from this method.
	 * 
	 * @param percentTraveled - a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point getPathPosition (double percentTraveled)
	{
		this.location = new Point(); 
		Point p1 = new Point(); 
		Point p2 = new Point(); 

		double distanceTravelNeeded = percentTraveled*length; 
		double p2Dis = 0.0; 

		if (percentTraveled <= 0.001)
		{
			return path[0]; 
		}

		if (percentTraveled >= 1.0)
		{
			return new Point (path[path.length-1]);
		}
		for (int i = 0; i < size-1; i++)
		{
			double x1= path[i].getX(); 
			double y1= path[i].getY(); 
			double x2= path[i+1].getX(); 
			double y2= path[i+1].getY(); 
			// Getting the distance that is a little more than the distance inputed 
			p2Dis = p2Dis + Point.distance(x1, y1, x2, y2); 
			if (p2Dis > distanceTravelNeeded){
				// First point
				p1.setLocation(path[i].getX(), path[i].getY());  
				// Second point
				p2.setLocation(path[i+1].getX(), path[i+1].getY());  
				// Distance length between the two points
				double pvp = Point.distance(p1.getX(), p1.getY(), p2.getX(), p2.getY()); 
				// The length to the second point subtracted by the distanced needed
				double over = p2Dis - distanceTravelNeeded; 

				// The percent of the line segment
				double per = (pvp - over)/pvp; 
				// Gets the location of the line segment
				location.setLocation(   ( 1- per)*(p1.x )  +  (per)* ( p2.x)   ,     (1- per)* (p1. y)    +    (per)*( p2.y)     );
				i = size; // Stops loop 
			}
		}
		return location; 
	}
}

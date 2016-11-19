

import java.awt.Point;

import transforms.Point2D;

public class SLine {
	public Point2D a;
	public Point2D b;
	public float q = -1;
	public float k = -1;
	
	public SLine(Point2D a, Point2D b) {
		this.a = a;
		this.b = b;
	}
	
	public SLine(Point a, Point b){
		this(new Point2D(a.x, a.y),
				new Point2D(b.x, b.y));
	}
	
	public boolean isHorizontal(){
		if(a.getY() == b.getY())
			return true;
		
		return false;
	}
	
	public void changeDirection(){
		if(a.getY() > b.getY()){
			Point2D ab = b;
			b = a;
			a = ab;
		}
	}
	
	public boolean isIntersection(int y){
		if(y >= a.getY() && y < b.getY()){
			return true;
		}
		return false;
	}
	
	public int getIntersection(double y){
		double dx = b.getX() - a.getX();
		double dy = b.getY() - a.getY();

		return (int)((a.getX() + (dx / dy) * (y - a.getY()))+0.5);
	}
	
	public void cut(){
		//TODO
	}
	
	private void computeKQ(){
		k = (int)(((b.getX() - a.getX())/(b.getY() - a.getY()))+0.5);
		q = (int)((a.getX() - k * a.getY())+0.5);
	}
}
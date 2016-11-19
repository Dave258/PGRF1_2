import java.util.List;

import transforms.Mat3;
import transforms.Mat3Rot2D;
import transforms.Point2D;

import java.util.ArrayList;
import java.awt.Point;

public class PolyLine {
	private List<Point2D> points = new ArrayList<Point2D>();
	
	public void add(Point2D p){
		points.add(p);
	}
	
	public List<Point2D> rotate(double alpha){
		Mat3 mat3 = new Mat3Rot2D(alpha);
		 
		List<Point2D> points2d = new ArrayList<>();
		
		for (Point2D p : points) {
			points2d.add(p.mul(mat3));
		}
		
		return points2d;
		
	}
}

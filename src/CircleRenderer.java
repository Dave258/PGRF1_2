import java.awt.Point;
import java.awt.image.BufferedImage;

public class CircleRenderer {
	private BufferedImage image;
	private int color;
	private LineRenderer line;
	
	public CircleRenderer(BufferedImage image, int color){
		this.image = image;
		this.color = color;
		line = new LineRenderer(image, color);
	}
	
	public CircleRenderer(BufferedImage image){
	}
	
	public void draw(int xA, int yA, int polomer){
		
		int xS = xA;
		int yS = yA;
		
		xA += polomer;
		
		int xB = xA;
		int yB = yA;
		
		
		
		for(double i = 0.1; i < Math.PI*2; i+=0.1){
			xA = (int)((Math.cos(i)*polomer)+0.5+xS);
			yA = (int)((Math.sin(i)*polomer)+0.5+yS);

			line.draw(xA, yA, xB, yB);
			
			xB = xA;
			yB = yA;
			
		}
		
		line.draw(xA, yA, xS+polomer, yS);
	}
	
	public void drawSector(Point s, Point a, Point b){
		
		int polomer = (int) ((Math.sqrt(Math.pow(a.x-s.x, 2)+Math.pow(s.y-a.y, 2)))+0.5);
		
		double alpha = calcAngle(s, a);
		double beta = calcAngle(s, b);
		
		if(alpha > beta){
			alpha -= 2* Math.PI;
		}
		
		int xA, yA, xB, yB;
		
		xA = a.x;
		yA = a.y;
		
		xB = xA;
		yB = yA;
		
		line.draw(s.x,s.y, a.x,a.y);
		
		for(double i = alpha; i < beta; i+=0.1){
		
			if(i>alpha && i<beta){
				xA = (int)((Math.cos(i)*polomer)+0.5+s.x);
				yA = (int)((Math.sin(i)*polomer)+0.5+s.y);

				line.draw(xA, yA, xB, yB);
			
				xB = xA;
				yB = yA;
			}
			
			
		}

		//line.draw(s.x,s.y,xA,yA);
		
		Point actualP = findPointOnCircle(s, b, polomer, beta);
		
		line.draw(xA, yA, actualP.x, actualP.y);
		line.draw(s.x, s.y, actualP.x, actualP.y);
	}
	
	private double calcAngle(Point s, Point a){
		double alpha = 0;
		double pi = Math.PI;
		
		if(a.x >= s.x && a.y >= s.y){
			alpha = Math.atan2(a.y-s.y, a.x-s.x);
		} else if(a.x <= s.x && a.y >= s.y){
			alpha = Math.atan2(s.x-a.x, a.y-s.y) + pi/2;
		} else if(a.x <= s.x && a.y <= s.y){
			alpha = Math.atan2(s.y-a.y, s.x-a.x) + pi;
		} else if(a.x >= s.x && a.y <= s.y){
			alpha = Math.atan2(a.x-s.x, s.y-a.y) + pi*1.5;
		}
		
		return alpha;
 	}
	
	public Point findPointOnCircle(Point s,Point a, int polomer, double alpha){
		
		Point actual = new Point(0,0);
		double pi = Math.PI;
		
		 if(a.x >= s.x && a.y >= s.y){
			actual.x = s.x+(int)((Math.cos(alpha)*polomer)+0.5);
			actual.y = s.y+(int)((Math.sin(alpha)*polomer)+0.5);
		} else if(a.x <= s.x && a.y >= s.y){
			actual.x = s.x-(int)((Math.sin(alpha-pi/2)*polomer)+0.5);
			actual.y = s.y+(int)((Math.cos(alpha-pi/2)*polomer)+0.5);
		} else if(a.x <= s.x && a.y <= s.y){
			actual.x = s.x-(int)((Math.cos(alpha-pi)*polomer)+0.5);
			actual.y = s.y-(int)((Math.sin(alpha-pi)*polomer)+0.5);
		} else if(a.x >= s.x && a.y <= s.y){
			actual.x = s.x+(int)((Math.sin(alpha-pi*1.5)*polomer)+0.5);
			actual.y = s.y-(int)((Math.cos(alpha-pi*1.5)*polomer)+0.5);
		}

		return actual;
	}
}













import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
	
	private BufferedImage img;
	private int background;
	
	private LineRenderer lineP1;
	private LineRenderer lineP2;
	private List<Point> polygonPoints;
	
	public Polygon(BufferedImage img, int background)
	{
		this.background = background;
		this.img = img;
		
		lineP1 = new LineRenderer(img, 0xFF0000);
		lineP2 = new LineRenderer(img, 0xFFFFFF);
		polygonPoints = new ArrayList<Point>();
	}
	
	public Polygon(BufferedImage img, int background, int color)
	{	
		this(img, background);
	}
	
	public void drawPolygon(){
		clear(background);
		
		Point last = polygonPoints.get(0);
		
		for(Point point : polygonPoints){
			lineP1.draw(last.x, last.y, point.x, point.y);
			last = point;
		}
		
		lineP1.draw(last.x, last.y, polygonPoints.get(0).x, polygonPoints.get(0).y);
		
	}
	
	public void drawPolygon(Point p){
		clear(background);
		
		Point last = polygonPoints.get(0);
		
		
		for(Point point : polygonPoints){
			lineP1.draw(last.x, last.y, point.x, point.y);
			last = point;
		}
		
		lineP2.draw(last.x, last.y, p.x, p.y);
		lineP2.draw(p.x, p.y, polygonPoints.get(0).x, polygonPoints.get(0).y);
	}

	public void clear() {
		clear(background);
	}
	
	public void clear(int color) {
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(color));
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
	
	public List<Point> getPolygonPoints(){
		return polygonPoints;
	}
	
	public void addPointToList(Point p){
		polygonPoints.add(p);
	}
}

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import transforms.Point2D;

public class ScanLineFiller extends Filler{
	private List<SLine> lines ;
	private List<Integer> inter;
	
	
	public ScanLineFiller(BufferedImage img,int color) {
		super(img,color);
	}
	public ScanLineFiller(BufferedImage img) {
		this(img, 0xFFFFFF);
	}
	
	//TODO ym2nit v3echnz int na double
	public void fill(List<Point> points){
		double yMax, yMin;
		yMax = yMin = points.get(0).y;
		
		LineRenderer lineR = new LineRenderer(img, color);
		
		List<Integer> intersectionX = new ArrayList<Integer>();
		List<SLine> sLines = new ArrayList<SLine>();
		
		int pSize = points.size();
		
		for (int i = 0; i < pSize; i++) {
			
			Point p1 = points.get(i);
			Point p2;
			
			if(i+1 == pSize){
				p2 = points.get(0);
			} else {
				p2 = points.get(i+1);
			}
			
			SLine sl = new SLine(p1, p2);
			
			if(!sl.isHorizontal()){
				sl.changeDirection();
				sLines.add(sl);
				
				if(p1.y > yMax){
					yMax = p1.y;
				}
				
				if(p1.y < yMin){
					yMin = p1.y;
				}
			}
		}
		
		for(int y = (int)(yMin+0.5); y <= (int)(yMax+0.5); y++){
			for (SLine sl : sLines) {
				if(sl.isIntersection(y)){
					intersectionX.add(new Integer(sl.getIntersection(y)));
				}
			}
			
			Collections.sort(intersectionX);
			
			for(int i=0; i<intersectionX.size()-1; i+=2){
				lineR.draw(intersectionX.get(i), y, intersectionX.get(i+1), y);
			}
			
			intersectionX.clear();
			
		}
		
		
	}
}
















/*lines = new ArrayList<>();
inter = new ArrayList<>();
int ymin = p.get(0).y;	
int ymax = p.get(0).y;
for (int i = 0; i < p.size(); i++) {
	Point pA = p.get(i);
	Point pB = p.get((i+1) % p.size());
	SLine s= new SLine(pA, pB);
	if(!s.isHorizontal()){
		if(ymin>p.get(i).y) ymin=p.get(i).y;
		if(ymax<p.get(i).y) ymax=p.get(i).y;
		s.changeDirection();
		lines.add(s);
	}	
}


for (int y = ymin; y < ymax; y++) {
	for (int i = 0; i < lines.size(); i++) {
		SLine s = lines.get(i);
		System.out.println(y);
		if(s.isIntersection(y)){
			
			int b = s.getIntersection(y);
			inter.add(b);
		}
		
	}
	
	//setridit podle x
	//Collections.sort(inter, compare);
	
	//pospojovat licyh se sudym
	for (int i = 0; i < inter.size()-1; i+=2) {
		for (int x = inter.get(i); x < inter.get(i+1); x++) {
			img.setRGB(x, y, color);
		}
	}
		
	
}*/


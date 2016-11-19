import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SeedFiller extends Filler {
	
	private int bgColor;
	
	//TODO: zmìnit texture
	int c1 = 0x926239;
	int c2 = 0x00e500;
	int c3 = 0x4da5ff;
	private int[][] texture;
	
	public SeedFiller(BufferedImage img, int color){
		super(img, color);
		this.texture = new int[][]{
			{c3,c3,c3,c3,c3,c3,c3,c3,c3,c3},
			{c3,c3,c3,c3,c3,c3,c3,c3,c3,c3},
			{c3,c2,c2,c3,c3,c3,c3,c3,c3,c3},
			{c2,c2,c2,c3,c3,c3,c3,c3,c3,c3},
			{c3,c2,c2,c1,c3,c3,c3,c3,c3,c3},
			{c3,c3,c1,c1,c3,c3,c3,c3,c3,c3},
			{c3,c3,c1,c1,c3,c3,c2,c2,c3,c3},
			{c3,c3,c1,c1,c3,c2,c2,c2,c3,c3},
			{c3,c3,c1,c1,c1,c2,c2,c3,c3,c3},
			{c3,c3,c3,c1,c1,c1,c3,c3,c3,c3},
			{c3,c3,c3,c3,c1,c1,c1,c3,c3,c3},
			{c3,c3,c3,c2,c3,c1,c1,c3,c3,c3},
			{c3,c3,c2,c2,c3,c1,c1,c3,c3,c3},
			{c3,c3,c2,c2,c3,c3,c1,c1,c3,c3},
			{c3,c3,c2,c3,c3,c3,c1,c1,c3,c3},
			{c3,c3,c3,c3,c3,c3,c1,c1,c3,c3},
			{c3,c3,c3,c3,c3,c1,c1,c3,c3,c3},
			{c3,c3,c3,c3,c3,c1,c1,c2,c2,c3},
			{c3,c3,c3,c3,c1,c1,c2,c2,c2,c3},
			{c3,c3,c3,c3,c1,c1,c3,c2,c3,c3},
			{c3,c3,c3,c1,c1,c1,c3,c3,c3,c3},
			{c3,c3,c3,c3,c3,c3,c3,c3,c3,c3}
		};
	}
	
	public SeedFiller(BufferedImage img){
		this(img, 0xFFFFFF);
	}
	
	public void fillRek(int x, int y, int bgColor){
		
		this.bgColor = bgColor;
		
		fillReku(x, y);
	}
	
	private void fillReku(int x, int y){
		if((x>-1) && (y>-1) && (x<img.getWidth()) && (y<img.getHeight()))
		{
			if(img.getRGB(x, y)==bgColor)
			{
				img.setRGB(x, y, color);
				fillReku(x+1, y);
				fillReku(x, y+1);
				fillReku(x-1, y);
				fillReku(x, y-1);
			}
		}
	}
	
	public void fill(int x, int y, int backgrounColor){
		Color bgColor = new Color(backgrounColor);
		Color clickedColor = new Color(img.getRGB(x, y));
		
		if(bgColor.equals(clickedColor)){
			
			List<Point> pointList = new ArrayList<Point>();
			Point firstP = new Point(x, y);
			pointList.add(firstP);

			while(!pointList.isEmpty())
			{
				Point p = pointList.get(0);
				
				if((p.getX()>-1) && (p.y>-1) && (p.x<img.getWidth()) && (p.y<img.getHeight()))
				{
	 				
					Color cl = new Color(img.getRGB(p.x, p.y));
					
					if(cl.equals(bgColor)){
						img.setRGB(p.x, p.y, texture[p.y % texture.length][p.x % texture[0].length]);
						
						pointList.add(new Point(p.x+1, p.y));
						pointList.add(new Point(p.x, p.y+1));
						pointList.add(new Point(p.x-1, p.y));
						pointList.add(new Point(p.x, p.y-1));
					}
				}
				
				pointList.remove(p);
			}
		}
	}
}

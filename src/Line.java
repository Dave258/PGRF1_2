import java.awt.image.BufferedImage;

public class Line {

	
	private int xA, yA, xB, yB;
	
	public Line(int xA, int yA, int xB, int yB)
	{
		this.xA = xA;
		this.yA = yA;
		this.xB = xB;
		this.yB = yB;
	}	
	
	public void draw(BufferedImage img, int color)
	{		
		double k = (double) (yB - yA) / (double) (xB - xA);
		double q = yA - k * xA;
		for (double x = xA; x <= xB; x += 0.1) {

			double y = k * x + q;
			img.setRGB((int) (x + 0.5), (int) (y + 0.5), color);
		}
	}
	
	
	public static void draw(BufferedImage img, int xA, int yA, int xB, int yB, int color) {

		double k = (double) (yB - yA) / (double) (xB - xA);
		double q = yA - k * xA;
		for (double x = xA; x <= xB; x += 0.1) {

			double y = k * x + q;
			img.setRGB((int) (x + 0.5), (int) (y + 0.5), color);
		}
	}

}

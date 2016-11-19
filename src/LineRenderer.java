import java.awt.image.BufferedImage;

public class LineRenderer
{
	
	private BufferedImage image;
	private int color;
	
	public void setImage(int color)
	{
		this.color = color;
	}
	
	public LineRenderer(BufferedImage img)
	{
		this.image = img;
		this.color = 0xff0000;
	}
	
	public LineRenderer(BufferedImage img, int color)
	{	
		this(img);
		
		this.color = color;
	}
	
	public void draw(int xA, int yA, int xB, int yB) {

		double dx = xB-xA;
		double dy = yB-yA;

		if (Math.abs(yB - yA) <= Math.abs(xB - xA)) {

		    if ((xA == xB) && (yA == yB)) {
		       image.setRGB(xA, yA, color);
		    } else {
		        if (xB < xA) {
		            int tmp = xB;
		            xB = xA;
		            xA = tmp;

		            tmp = yB;
		            yB = yA;
		            yA = tmp;
		        }

		        double k = (double)dy/dx; 
		        int roundedY;           
		        double y = (double)yA + 0.5;

		        for (int x = xA ; x <= xB ; x++) {
		    	   roundedY = (int)y;
		           image.setRGB(x, roundedY, color);

		           y += k;
		        }
		     }
		} else {

		           if (yB < yA) {
		               int tmp = xB;
		               xB = xA;
		               xA = tmp;

		               tmp = yB;
		               yB = yA;
		               yA = tmp;
		           }

		           double k = (double)dx/dy;
		           int roundedX;
		           double x = (double)xA  + 0.5;
		           for (int y = yA; y <= yB; y++) {
		        	   roundedX = (int)x;
		               image.setRGB(roundedX, y, color);

		               x += k;
		           }
		   }
	}
	
	public void drawWithoutPoints(){
		//TODO
	}
}

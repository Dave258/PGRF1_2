import java.awt.image.BufferedImage;

public abstract class Filler {
	protected int color;
	protected BufferedImage img;
	
	protected Filler(BufferedImage img){
		this(img, 0xFFFFFF);
	}
	
	protected Filler(BufferedImage img, int color){
		this.img = img;
		this.color = color;
	}
	
	protected void drawPixel(){
		
	}
	
}

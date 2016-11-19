
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * trida pro kresleni na platno:
 * zobrazeni  pixelu 
 * @author PGRF FIM UHK
 * @version 2015
 */

public class CanvasPaint {

	private JFrame frame;
	private JPanel panel;
	
	private BufferedImage img;
	
	private Point pA, pB, pS;
	private Point fP;
	
	private ScanLineFiller scanLineFiller;
	private SeedFiller seedFiller;
	private Polygon polygon;
	
	private int background = 0x2f2f2f;
	
	

	public CanvasPaint(int width, int height) {
		
		frame = new JFrame();
		frame.setTitle("UHK FIM PGRF : Canvas");
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		MouseAdapter adapter = new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON3){
					
					fP = e.getPoint();
					
					scanLineFiller.fill(polygon.getPolygonPoints());
					//seedFiller.fill(fP.x, fP.y, background);
				}	
				
				present();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)){
					
					polygon.drawPolygon(e.getPoint());
					
					present();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					
					polygon.addPointToList(e.getPoint());
					polygon.drawPolygon();
					
					present();
				}
			}
		};
		
		panel.addMouseListener(adapter);
		panel.addMouseMotionListener(adapter);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		fP = new Point(-1, -1);
		
		polygon = new Polygon(img, background, 0xFF0000);
		scanLineFiller = new ScanLineFiller(img, 0xFFFFFF);
		seedFiller = new SeedFiller(img, 0xFFFFFF);
		
		polygon.clear(background);
		present();
	}
	
	
	

	public void present() {
		if (panel.getGraphics() != null)
			panel.getGraphics().drawImage(img, 0, 0, null);
	}
	
	public void present(BufferedImage img) {
		if (panel.getGraphics() != null)
			panel.getGraphics().drawImage(img, 0, 0, null);
	}
	
	public void start() {
		//draw();
		present();
	}
	
	/*public BufferedImage copyIgm(BufferedImage img){
	 	 	 ColorModel cm = img.getColorModel();
			 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
			 WritableRaster raster = img.copyData(null);
			 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public void copyIgm(BufferedImage from, BufferedImage to){
	 	to.setData(from.getData());
	}*/

	public static void main(String[] args) {
		CanvasPaint canvas = new CanvasPaint(800, 600);
		SwingUtilities.invokeLater(() -> {
			SwingUtilities.invokeLater(() -> {
				SwingUtilities.invokeLater(() -> {
					SwingUtilities.invokeLater(() -> {
						canvas.start();
					});
				});
			});
		});
	}

}
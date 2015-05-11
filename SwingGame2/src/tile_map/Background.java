package tile_map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Background {

	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	// Creates parallax effect.
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(
					getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y){
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	// BG auto scroll
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g){
		
		g.drawImage(image, (int)x, (int)y, null);
		//make sure it fills all the screen
		//draw another instance of the BG
		if (x < 0) {
			g.drawImage(image, (int)x + GamePanel.WIDTH,
					(int)y, null);
		}
		//draw another
		if (x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH,
					(int)y, null);
		}
	}
}

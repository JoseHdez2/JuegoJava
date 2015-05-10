package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Explosion {
	
	private int p_x;
	private int p_y;
	private int map_x;
	private int map_y;
	
	private int width;
	private int height;
	
	private Animation animation;
	private BufferedImage[] sprites;
	
	private boolean remove;
	
	public Explosion(int x, int y) {
		this.p_x = x;
		this.p_y = y;
		
		width = 30;
		height = 30;
		
		try {
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/explosion.gif"
				)
			);
			
			sprites = new BufferedImage[6];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(70);
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) {
			remove = true;
		}
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void setMapPosition(int x, int y) {
		map_x = x;
		map_y = y;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(
			animation.getImage(),
			p_x + map_x - width / 2,
			p_y + map_y - width / 2,
			null);
		
	}
}

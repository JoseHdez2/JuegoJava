package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tile_map.TileMap;

public class FireBall extends MapObject {

	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	final int DELAY = 70;
	
	public FireBall(TileMap tm, boolean right) {
		
		super(tm);
		
		facingRight = right;
		
		moveSpeed = 3.8;
		if(right) d_x = moveSpeed;
		else d_x = -moveSpeed;
		
		width = 30;
		height = 30;
		colWidth = 14;
		colHeight = 14;
		
		// Load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
						"/Sprites/Player/fireball.gif"
					)
			);
			
			sprites = new BufferedImage[4];
			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			hitSprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(DELAY);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHit(){
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(DELAY);
		d_x = 0;
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		checkTileMapCollision();
		setPosition(xTemp, yTemp);
		
		if (d_x == 0 && !hit) {
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()){
			remove = true;
		}
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		

		super.draw(g);
	}
}

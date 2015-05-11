package entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tile_map.TileMap;
import entity.Animation;
import entity.Enemy;

public class Slugger extends Enemy{

	final int A_MILLION = 1000000;
	
	private BufferedImage[] sprites;
	
	final int DELAY = 300;
	final int FLINCH_TIME = 400;
	
	public Slugger(TileMap tm) {
		super(tm);

		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
				
		width = 30;
		height = 30;
		colWidth = 20;
		colHeight = 20;
		
		health = maxHealth = 2;
		damage = 1;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
						"/Sprites/Enemies/slugger.gif"
				)	
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(DELAY);
		
		right = true;
	}
	
	private void getNextPosition() {
		
		// Movement
		if(left) {
			d_x -= moveSpeed;
			if(d_x < -maxSpeed) {
				d_x = -maxSpeed;
			}
		}
		else if(right) {
			d_x += moveSpeed;
			if(d_x > maxSpeed) {
				d_x = maxSpeed;
			}
		}
		
		if(falling) {
			d_y += fallSpeed;
		}
		
	}
	
	public void update() {
		
		//update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);
		
		// check flinching
		if (flinching){
			long elapsed =
				(System.nanoTime() - flinchTimer) / A_MILLION;
			if (elapsed > FLINCH_TIME){
				flinching = false;
			}
		}
		
		// if it hits a wall, go in other direction
		if(right && d_x == 0) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if( left && d_x == 0){
			right = true;
			left = false;
			facingRight = true;
		}
		
		// update animation
		animation.update();
	}
	
	public void draw(Graphics2D g){
		
		// if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
	}
}

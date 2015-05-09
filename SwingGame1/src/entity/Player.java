package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import tile_map.TileMap;

public class Player extends MapObject {

	public static final int A_MILLION = 1000000;
	public final int TILE_SIZE = 30;
	
	public final int DELAY_IDLE = 400;
	public final int DELAY_WALKING = 40;
	public final int DELAY_JUMPING = -1;
	public final int DELAY_FALLING = 0;
	public final int DELAY_GLIDING = 100;
	public final int DELAY_FIREBALL = 100;
	public final int DELAY_SCRATCHING = 50;
	
	// player stuff
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	
	// fireball
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;
	
	// scratch
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	
	// gliding
	private boolean gliding;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			2, 8, 1, 2, 4, 2, 5
	};
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	
	public Player(TileMap tm){
		super(tm);
		
		width = 30;
		height = 30;
		colWidth = 20;
		colHeight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		fire = maxFire = 2500;

		fireCost = 200;
		fireBallDamage = 5;
		fireBalls = new ArrayList<FireBall>();
		
		scratchDamage = 8;
		scratchRange = 40;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/playersprites.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 7; i++) {
				BufferedImage[] bi =
						new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++) {
					if(i != 6){
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					// special "scratch" animation
					else{
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
				}
				sprites.add(bi);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(DELAY_IDLE);
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}
	
	public void setFiring() {
		firing = true;
	}
	
	public void setScratching() {
		scratching = true;
	}
	
	public void setGliding(boolean b) {
		gliding = b;
	}
	
	private boolean currentActionIsAttack(){
		return (currentAction == SCRATCHING || currentAction == FIREBALL);
	}
	
	//where the next position of the player is...
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
		else {
			if(d_x > 0){
				d_x -= stopSpeed;
				if(d_x < 0) {
					d_x = 0;
				}
			}
			else if(d_x < 0){
				d_x += stopSpeed;
				if(d_x > 0) {
					d_x = 0;
				}
			}
		}
		
		// cannot move while attacking, except in air
		if(
		currentActionIsAttack() &&
		!(jumping || falling)) {
			d_x = 0;
		}
		
		// saltando
		if(jumping && !falling) {
			d_y = jumpStart;
			falling = true;
		}
		
		// cayendo
		if(falling) {
			if (d_y > 0 && gliding) d_y += fallSpeed * 0.1;
			else d_y += fallSpeed;
			
			if(d_y > 0) jumping = false;
			if(d_y < 0 && !jumping) d_y += stopJumpSpeed;
			
			if(d_y > maxFallSpeed) d_y = maxFallSpeed;
		}
	}
	
	public void update() {
		
		// Refrescar posicion
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);
		
		// Check whether the attack has stopped
		if(currentAction == SCRATCHING) {
			if(animation.hasPlayedOnce()) scratching = false;
		}
		if(currentAction == FIREBALL) {
			if(animation.hasPlayedOnce()) firing = false;
		}
		
		// fireball attack
		fire += 1;
		if(fire > maxFire) fire = maxFire;
		if(firing && currentAction != FIREBALL) {
			if(fire > fireCost) {
				fire -= fireCost;
				FireBall fb = new FireBall(tileMap, facingRight);
				fb.setPosition(p_x, p_y);
				fireBalls.add(fb);
			}
		}
		
		// Update fireballs
		for (int i = 0; i < fireBalls.size(); i++){
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}
		
		// Asignar animacion
		if(scratching) {
			if (currentAction != SCRATCHING) {
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(DELAY_SCRATCHING);
				width = TILE_SIZE * 2;
			}
		}
		else if (firing) {
			if(currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(DELAY_FIREBALL);
				width = TILE_SIZE;
			}
		}
		else if (d_y > 0) {
			if (gliding) {
				if (currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(DELAY_GLIDING);
					width = TILE_SIZE;
				}
			}
		}
		else if (d_y < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(DELAY_JUMPING);
				width = TILE_SIZE;
			}
		}
		else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(DELAY_WALKING);
				width = TILE_SIZE;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(DELAY_IDLE);
				width = TILE_SIZE;
			}
		}
		
		animation.update();
		
		// set direction
		if(!currentActionIsAttack()){
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		// draw fireballs
		for (FireBall fb : fireBalls){
			fb.draw(g);
		}
		
		// draw player
		if(flinching) {
			long elapsed =
					(System.nanoTime() - flinchTimer) / A_MILLION;
					
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(p_x + xmap - width / 2),
				(int)(p_y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(p_x + xmap - width / 2),
				(int)(p_y + ymap - height / 2),
				-width,
				height,
				null
			);
		}
	}
}

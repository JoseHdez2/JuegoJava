package levels;

import java.awt.Graphics2D;

import entity.Entity1;
import entity.Entity2;
import entity.util.Vect2F;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class LevelTest1 extends LevelState {
	
	public LevelTest1(GameStateManager gsm) {
		super(gsm);
		
		// Test entities
		entities.add(new Entity2());
	}

	/*
	 * Called once the level is done loading.
	 */
	public void init() {
		super.init();
	}
	
	
	/*
	 * Update the game logic (Collisions, movement).
	 */
	public void update() {
		
		// Entity updating.
		for (Entity2 e : entities){
			e.update();
		}
	}

	/*
	 * Draw entities and other graphical elements to screen.
	 */
	public void draw(Graphics2D g) {
		
		// Draw BG
		bg.draw(g);
		
		for (Entity1 e : entities){
			e.drawDebug(g);
		}
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		
		if (k == KEY_LEFT){
			entities.get(0).go(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			entities.get(0).go(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT)
			entities.get(0).stop();
		if (k == KEY_RIGHT)
			entities.get(0).stop();
	}
	
}

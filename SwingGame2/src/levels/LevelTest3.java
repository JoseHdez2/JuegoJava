package levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entity.Entity1;
import entity.Entity3;
import entity.Entity4;
import entity.util.Vect2F;
import bg.Background;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class LevelTest3 extends LevelState {

	final String FILE_BACKGROUND = "/Backgrounds/bg3.gif";
	final float BACKGROUND_PARALLAX = 0.1f;
	
	final int KEY_LEFT = KeyEvent.VK_LEFT;
	final int KEY_RIGHT = KeyEvent.VK_RIGHT;

	final Color COLOR_CLEAR_SCREEN = Color.WHITE;
	
	private Background bg;
	
	public ArrayList<Entity3> entities = new ArrayList<Entity3>();
	
	public LevelTest3(GameStateManager gsm) {
		super(gsm);
		
		// Test entities
		entities.add(new Entity4());
		entities.add(new Entity4());
		entities.get(0).body.x = 20;
		entities.get(0).body.y = 30;
		entities.get(1).body.x = 155;
		entities.get(1).body.y = 125;
	}

	/*
	 * Called once the level is done loading.
	 */
	public void init() {

		bg = new Background(FILE_BACKGROUND, BACKGROUND_PARALLAX);

	}
	
	
	/*
	 * Update the game logic (Collisions, movement).
	 */
	public void update() {
		
		// Collision calculation.
		for (int i = 0; i < entities.size(); i++){
			ArrayList<Entity1> others = new ArrayList<Entity1>();
			for (int j = 0; j < entities.size(); j++){
				if (j == i) continue;
				others.add(entities.get(j));
			}
			entities.get(i).checkCornersForCollisions(others);
		}
		
		// Entity updating.
		for (Entity3 e : entities){
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
			entities.get(0).body.x -= 1;
			//entities.get(1).d.x = -1f;
			entities.get(1).go(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			entities.get(0).body.x += 1;
			//entities.get(1).d.x = 1f;
			entities.get(1).go(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT)
			entities.get(1).stop();
		if (k == KEY_RIGHT)
			entities.get(1).stop();
	}
	
}

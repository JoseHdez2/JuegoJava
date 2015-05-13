package game_state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import newer.entity.Entity1;
import newer.entity.Entity2;
import newer.entity.Entity3;
import newer.entity.util.Vect2;
import tile_map.Background;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class Level2State extends LevelState {

	final String FILE_BACKGROUND = "/Backgrounds/bg3.gif";
	final float BACKGROUND_PARALLAX = 0.1f;
	
	final int KEY_LEFT = KeyEvent.VK_LEFT;
	final int KEY_RIGHT = KeyEvent.VK_RIGHT;

	final Color COLOR_CLEAR_SCREEN = Color.WHITE;
	
	private Background bg;
	
	public ArrayList<Entity3> entities = new ArrayList<Entity3>();
	
	public Level2State(GameStateManager gsm) {
		super(gsm);
		entities.add(new Entity3());
		entities.add(new Entity3());
	}

	public void init() {

		bg = new Background(FILE_BACKGROUND, BACKGROUND_PARALLAX);

	}
	
	
	public void update() {
		/*
		for (Entity3 e : entities){
			Entity3[] others = (Entity3[]) entities.toArray();
			e.checkCornersForCollisions(others);
		}*/
		for (Entity3 e : entities){
			e.update();
		}
	}

	public void draw(Graphics2D g) {
		
		// Draw BG
		bg.draw(g);
		
		for (Entity1 e : entities){
			e.drawDebug(g);
		}
	}

	public void keyPressed(int k) {
		if (k == KEY_LEFT){
			entities.get(0).body.x -= 1;
			//entities.get(1).d.x = -1f;
			entities.get(1).go(Vect2.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			entities.get(0).body.x += 1;
			//entities.get(1).d.x = 1f;
			entities.get(1).go(Vect2.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT)
			entities.get(1).stop();
		if (k == KEY_RIGHT)
			entities.get(1).stop();
	}
	
}

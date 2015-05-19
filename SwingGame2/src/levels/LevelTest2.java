package levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entity.Entity1_Visible;
import entity.Entity2_Movable;
import entity.Entity3_Collidable;
import entity.EntityF;
import entity.util.Vect2F;
import bg.Background;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class LevelTest2 extends LevelState {

	final String backgroundFile = "/Backgrounds/bg3.gif";

	public LevelTest2(GameStateManager gsm) {
		super(gsm);
		
		// Test entities
		entities.add(new EntityF(100,100,100,100));
		entities.add(new EntityF(120,120,100,100));
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		if (k == KEY_LEFT){
			entities.get(0).body.x -= 1;
			entities.get(1).go(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			entities.get(0).body.x += 1;
			entities.get(1).go(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT || k == KEY_RIGHT)
			entities.get(1).stop();
	}
	
}

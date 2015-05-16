package levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entity.Entity2;
import entity.Entity3;
import main.GamePanel;
import bg.Background;


/**
 * @author jose
 *	State in which the game is played.
 *	Implements player input, allowing the player
 *	to control the main character.
 */
public abstract class LevelState extends GameState {
	
	/*
	 *  Definition of the control keys.
	 *  These are the keys that will be used in any state of the game.
	 */
	
	public static final int KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static final int KEY_UP = KeyEvent.VK_UP;
	public static final int KEY_DOWN = KeyEvent.VK_DOWN;
	public static final int KEY_JUMP = KeyEvent.VK_SPACE;
	public static final int KEY_MENU = KeyEvent.VK_ESCAPE;

	// Constants and objects that appear in any level.
	
	final Color COLOR_CLEAR_SCREEN = Color.WHITE;
	protected Background bg;

	// Values that will be different for each level.
	
	String backgroundFile = "/Backgrounds/bg3.gif";
	
	boolean backgroundEnabled = false;
	
	// Player entity.
	Entity2 player = new Entity2();
	
	// Rest of entities.
	public ArrayList<Entity2> entities = new ArrayList<Entity2>();
	
	public LevelState(GameStateManager gsm) {
		this.gsm = gsm;
		init ();
	}

	public void init() {

		bg = new Background(backgroundFile);
	}

	public void update() {
		
		// Update the player.
		player.update();
	}

	public void draw(Graphics2D g) {

		// Draw the background, be it an image or a white void.
		if (backgroundEnabled) {
			bg.draw(g);
		}
		else {
			g.setColor(COLOR_CLEAR_SCREEN);
			g.drawRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
		}
		
		// Draw the player.
		player.draw(g);

	}

	public void keyPressed(int k) {
		if (k == KEY_MENU){
			gsm.setState(GameStateManager.STATE_MENU);
		}
	}
}

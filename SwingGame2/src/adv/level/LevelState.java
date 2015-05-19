package adv.level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import adv.entity.Entity3_Collidable;
import adv.main.GamePanel;
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
	 *  These keys will be used in any state of the game that is playable.
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
	boolean backgroundEnabled = true;
	
	// Rest of entities.
	public ArrayList<Entity3_Collidable> entities = new ArrayList<Entity3_Collidable>();
	
	public LevelState(GameStateManager gsm) {
		this.gsm = gsm;
		init ();
	}

	public void init() {

		bg = new Background(backgroundFile);
	}

	public void update() {
		
		// Update the entities.
		for (Entity3_Collidable e : entities){
			ArrayList<Entity3_Collidable> collisionList = new ArrayList<Entity3_Collidable>();
			collisionList.addAll(entities);
			e.update(collisionList);
		}
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
		
		// Draw the entities.
		for (Entity3_Collidable e : entities)
			e.draw(g);

	}

	public void keyPressed(int k) {
		if (k == KEY_MENU){
			gsm.setState(GameStateManager.STATE_MENU);
		}
	}
	
	public void keyReleased(int k) {
	}
}

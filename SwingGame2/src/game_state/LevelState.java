package game_state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import tile_map.Background;
import tile_map.TileMap;


/**
 * @author jose
 *	State in which the game is played.
 *	Implements player input, allowing the player
 *	to control the main character.
 */
public abstract class LevelState extends GameState {
	
	// Controls that will be used in any level of the game.
	
	public static final int KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static final int KEY_UP = KeyEvent.VK_UP;
	public static final int KEY_DOWN = KeyEvent.VK_DOWN;
	public static final int KEY_JUMP = KeyEvent.VK_A;
	public static final int KEY_GLIDE = KeyEvent.VK_S;
	public static final int KEY_SCRATCH = KeyEvent.VK_D;
	public static final int KEY_FIRE = KeyEvent.VK_F;

	// 
	
	final Color COLOR_CLEAR_SCREEN = Color.WHITE;
	final int TILE_SIZE = 30;

	public TileMap tileMap;
	private Background bg;

	// Constants that will be different for each level.
	
	final String FILE_MAP = "/Maps/level1-1.map";
	final String FILE_TILESET = "/Tilesets/grasstileset.gif";
	final String FILE_BACKGROUND = "/Backgrounds/grassbg1.gif";
	final float BACKGROUND_PARALLAX = 0.1f;
	
	final int PLAYER_START_X = 100;
	final int PLAYER_START_Y = 100;
	
	public LevelState(GameStateManager gsm) {
		this.gsm = gsm;
		init ();
	}

	public void init() {
		// tileSize of 30
		tileMap = new TileMap(TILE_SIZE);
		//
		tileMap.loadMap(FILE_MAP);
		//
		tileMap.loadTiles(FILE_TILESET);
		//
		tileMap.setPosition(0, 0);

		bg = new Background(FILE_BACKGROUND, BACKGROUND_PARALLAX);
	}

	public void update() {
	}

	public void draw(Graphics2D g) {

		// Draw BG
		bg.draw(g);

		// Draw tilemap
		tileMap.draw(g);
	}

}

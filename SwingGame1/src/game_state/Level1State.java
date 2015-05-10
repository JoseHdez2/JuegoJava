package game_state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GamePanel;
import tile_map.Background;
import tile_map.TileMap;
import entity.Enemy;
import entity.HUD;
import entity.Player;
import entity.enemies.Slugger;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class Level1State extends LevelState {

	final String FILE_MAP = "/Maps/level1-1.map";
	final String FILE_TILESET = "/Tilesets/grasstileset.gif";
	final String FILE_BACKGROUND = "/Backgrounds/grassbg1.gif";
	final float BACKGROUND_PARALLAX = 0.1f;
	
	final int PLAYER_START_X = 100;
	final int PLAYER_START_Y = 100;
	
	final int KEY_LEFT = KeyEvent.VK_LEFT;
	final int KEY_RIGHT = KeyEvent.VK_RIGHT;
	final int KEY_UP = KeyEvent.VK_UP;
	final int KEY_DOWN = KeyEvent.VK_DOWN;
	final int KEY_JUMP = KeyEvent.VK_A;
	final int KEY_GLIDE = KeyEvent.VK_S;
	final int KEY_SCRATCH = KeyEvent.VK_D;
	final int KEY_FIRE = KeyEvent.VK_F;

	final Color COLOR_CLEAR_SCREEN = Color.WHITE;
	final int TILE_SIZE = 30;

	public TileMap tileMap;
	private Background bg;

	private Player player;
	
	private ArrayList<Enemy> enemies;

	private HUD hud;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
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
		tileMap.setTween(1);

		bg = new Background(FILE_BACKGROUND, BACKGROUND_PARALLAX);

		player = new Player(tileMap);
		player.setPosition(PLAYER_START_X, PLAYER_START_Y);
		
		enemies = new ArrayList<Enemy>();
		
		Slugger s = new Slugger(tileMap);
		s.setPosition(100, 100);
		enemies.add(s);
		
		hud = new HUD(player);
	}
	

	public void update() {

		// update player
		player.update();
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getX(), 
				GamePanel.HEIGHT / 2 - player.getY()
		);
		
		// scroll background
		bg.setPosition(tileMap.getX(), tileMap.getY());
		
		// update all enemies
		for (Enemy e : enemies){
			e.update();
		}
	}

	public void draw(Graphics2D g) {

		// Draw BG
		bg.draw(g);

		// Draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);
		
		// draw all enemies
		for (Enemy e : enemies){
			e.draw(g);
		}
		
		// draw hud
		hud.draw(g);
	}

	public void keyPressed(int k) {
		if (k == KEY_LEFT)
			player.setLeft(true);
		if (k == KEY_RIGHT)
			player.setRight(true);
		if (k == KEY_UP)
			player.setUp(true);
		if (k == KEY_DOWN)
			player.setDown(true);
		if (k == KEY_JUMP)
			player.setJumping(true);
		if (k == KEY_GLIDE)
			player.setGliding(true);
		if (k == KEY_SCRATCH)
			player.setScratching();
		if (k == KEY_FIRE)
			player.setFiring();
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT)
			player.setLeft(false);
		if (k == KEY_RIGHT)
			player.setRight(false);
		if (k == KEY_UP)
			player.setUp(false);
		if (k == KEY_DOWN)
			player.setDown(false);
		if (k == KEY_JUMP)
			player.setJumping(false);
		if (k == KEY_GLIDE)
			player.setGliding(false);
	}
	
}

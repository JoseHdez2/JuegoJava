package game_state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GamePanel;
import tile_map.Background;
import tile_map.TileMap;
import audio.AudioPlayer;
import entity.Enemy;
import entity.Explosion;
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
	private ArrayList<Explosion> explosions;

	private HUD hud;
	
	private AudioPlayer bgMusic;
	
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
		
		populateEnemies();
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
		
		bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		bgMusic.play();
	}
	
	private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		
		Slugger s;
		Point[] points = new Point[] {
			new Point(300 , 200),
			new Point(860, 200),
			new Point(1525, 200),
			new Point(1680, 200),
			new Point(1800, 200)
		};
		
		for (Point p : points) {
			s = new Slugger(tileMap);
			s.setPosition(p.x, p.y);
			enemies.add(s);
		}
		
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
		
		// attack enemies
		player.checkAttack(enemies);
		
		// update all enemies
		for (int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
					new Explosion((int)e.getX(), (int)e.getY()));
			}
		}
		
		// update explosions
		for (int i = 0; i < explosions.size(); i++){
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()){
				explosions.remove(i);
			}
		}
		/*
		for (Explosion e : explosions){
			e.update();
			if (e.shouldRemove()) {
				explosions.remove(e);
			}
		}*/
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
		
		// draw all explosions
		for (Explosion e : explosions){
			e.setMapPosition(
				(int)tileMap.getX(), (int)tileMap.getY());
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

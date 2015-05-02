package game_state;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import tile_map.TileMap;

public class Level1State extends GameState {
	
	final Color COLOR_CLEAR_SCREEN = Color.WHITE;
	
	public TileMap tileMap;
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		// tileSize of 30
		tileMap = new TileMap(30);
		// 
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		// 
		tileMap.loadMap("/Maps/level1-1.map");
		// 
		tileMap.setPosition(0, 0);
	}
	
	public void update() {}
	public void draw(Graphics2D g) {
		
		// Clear screen
		g.setColor(COLOR_CLEAR_SCREEN);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// Draw tilemap
		tileMap.draw(g);
	}
	public void keyPressed(int k) {}
	public void keyReleased(int k) {}
}

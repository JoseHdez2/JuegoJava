package game_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.Game;
import tile_map.Background;

public class MenuState extends GameState {
	
	final String TITLE_STRING = Game.NOMBRE_JUEGO;
	final Color TITLE_COLOR = new Color(128, 0, 0);
	
	final Color COLOR_OPTION_ON = Color.BLACK;
	final Color COLOR_OPTION_OFF = Color.RED;
	
	final String[] OPTIONS = {
			"Empezar",
			"Ayuda",
			"Salir"
	};
	
	private Background bg;
	
	private int currentChoice = 0;
	
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm){
		
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);

			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			
			font = new Font("Arial", Font.PLAIN, 12);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void init() {}
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		//
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 1000);
		//bg.draw(g);
		
		//draw title
		g.setColor(TITLE_COLOR);
		g.setFont(titleFont);
		g.drawString(TITLE_STRING, 80, 70);
		
		g.setFont(font);
		for (int i = 0; i < OPTIONS.length; i++){
			if (i == currentChoice) {
				g.setColor(COLOR_OPTION_ON);
			}
			else {
				g.setColor(COLOR_OPTION_OFF);
			}
			g.drawString(OPTIONS[i], 145, 140 + i * 15);
		}
	}
	
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.STATE_LV_1);
		}
		
		if (currentChoice == 1) {
			
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		if (k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = OPTIONS.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == OPTIONS.length) {
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k) {}
}

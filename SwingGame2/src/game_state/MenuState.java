package game_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import bg.Background;
import main.Game;

public class MenuState extends GameState {
	
	final String TITLE_STRING = Game.NOMBRE_JUEGO;
	final Color TITLE_COLOR = new Color(180, 30, 0);
	
	final Color COLOR_OPTION_ON = Color.RED;
	final Color COLOR_OPTION_OFF = Color.ORANGE;
	
	final int TITLE_START_X = 100;
	final int TITLE_START_Y = 100;
	
	final int OPTION_START_X = 30;
	final int OPTION_START_Y = 140;
	final int OPTION_SPACING_X = 110;
	
	final int HELP_START_X = 60;
	final int HELP_START_Y = 180;
	final int HELP_SPACING_Y = 20;
	
	boolean showHelp = false;
	
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
			
			bg = new Background("/Backgrounds/bg2.gif", 1);
			//bg.setVector(-0.1, 0);

			titleFont = new Font("Arial", Font.ITALIC, 20);
			
			font = new Font("Arial", Font.PLAIN, 12);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void init() {
	}
	public void update() {
		bg.update();
		
	}
	
	public void draw(Graphics2D g) {
		//
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 1000);
		bg.draw(g);
		
		//draw title
		g.setColor(TITLE_COLOR);
		g.setFont(titleFont);
		g.drawString(TITLE_STRING, TITLE_START_X, TITLE_START_Y);
		
		g.setFont(font);
		for (int i = 0; i < OPTIONS.length; i++){
			if (i == currentChoice) {
				g.setColor(COLOR_OPTION_ON);
			}
			else {
				g.setColor(COLOR_OPTION_OFF);
			}
			g.drawString(OPTIONS[i], OPTION_START_X + i * OPTION_SPACING_X, OPTION_START_Y);
		}
		if (showHelp){
			g.setColor(COLOR_OPTION_OFF);
			String[] helpStrings = {
				"Moverse: Flechas direccionales.",
				"Objetivo: Llegar a la meta."
			};
			for (int i = 0; i < helpStrings.length; i++){
				g.drawString(helpStrings[i], HELP_START_X, HELP_START_Y + i * HELP_SPACING_Y);
			}
			
		}
	}
	
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.STATE_LV_0);
		}
		
		if (currentChoice == 1) {
			showHelp = (showHelp) ? false : true;
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		if (k == KeyEvent.VK_LEFT){
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = OPTIONS.length - 1;
			}
		}
		if (k == KeyEvent.VK_RIGHT){
			currentChoice++;
			if(currentChoice == OPTIONS.length) {
				currentChoice = 0;
			}
		}
	}
	
	public void keyReleased(int k) {}
}

package adv.level;

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
	final int TITLE_START_Y = 50;
	
	// Constants for drawing the choices' options.
	final int OPTION_CHOICE_START_X = TITLE_START_X - 70;
	final int OPTION_CHOICE_START_Y = TITLE_START_Y + 40;
	final int OPTION_CHOICE_OFFSET_X = 110;
	final int OPTION_CHOICE_OFFSET_Y = 0;
	
	// Constants for drawing the levels' options.
	final int OPTION_LEVEL_START_X = 30;
	final int OPTION_LEVEL_START_Y = OPTION_CHOICE_START_Y + 40;
	final int OPTION_LEVEL_OFFSET_X = 10;
	final int OPTION_LEVEL_OFFSET_Y = 20;
	
	final int HELP_START_X = 60;
	final int HELP_START_Y = 180;
	final int HELP_SPACING_Y = 20;
	
	boolean showLevels = false;
	boolean showHelp = false;
	
	final String[] OPTIONS_CHOICES = {
		"Empezar",
		"Ayuda",
		"Salir"
	};
	
	final String[] OPTIONS_LEVELS = {
		"Menu",
		"Test Movimiento (MRU)",
		"Test Colisiones: Deteccion",
		"Test Colisiones: Recuperacion"
	};
	
	private Background bg;
	
	private int currentChoice = 0;
	private int currentLevel = 0;
	
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
		
		// Draw choice options.
		for (int i = 0; i < OPTIONS_CHOICES.length; i++){
			Color colorUsed = (i == currentChoice)? COLOR_OPTION_ON : COLOR_OPTION_OFF;
			g.setColor(colorUsed);
			g.drawString(OPTIONS_CHOICES[i], 
						OPTION_CHOICE_START_X + i * OPTION_CHOICE_OFFSET_X, 
						OPTION_CHOICE_START_Y + i * OPTION_CHOICE_OFFSET_Y);
		}
		
		// Draw level options.
		if(showLevels){
			for (int i = 0; i < OPTIONS_LEVELS.length; i++){
				Color colorUsed = (i == currentLevel)? COLOR_OPTION_ON : COLOR_OPTION_OFF;
				g.setColor(colorUsed);
				g.drawString(OPTIONS_LEVELS[i], 
						OPTION_LEVEL_START_X + i * OPTION_LEVEL_OFFSET_X,
						OPTION_LEVEL_START_Y + i * OPTION_LEVEL_OFFSET_Y);
			}
		}
		
		// mostrar ayuda
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
		if (!showLevels){
			switch(currentChoice){
			case 0 : showHelp = false; showLevels = true; break;
			case 1 : showHelp = (showHelp) ? false : true; break;
			case 2 : System.exit(0); break;
			}
		}
		else {
			switch(currentLevel){
			case 0 : gsm.setState(GameStateManager.STATE_MENU); break;
			case 1 : gsm.setState(GameStateManager.STATE_LV_TEST_1); break;
			case 2 : gsm.setState(GameStateManager.STATE_LV_TEST_2); break;
			case 3 : gsm.setState(GameStateManager.STATE_LV_TEST_3); break;
			}
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		if (k == KeyEvent.VK_LEFT){
			if (!showLevels) {
				currentChoice--;
				if(currentChoice == -1) currentChoice = OPTIONS_CHOICES.length - 1;
			}
			if (showLevels) {
				currentLevel--;
				if(currentLevel == -1) currentLevel = OPTIONS_LEVELS.length - 1;
			}
		}
		if (k == KeyEvent.VK_RIGHT){
			if (!showLevels) {
				currentChoice++;
				if(currentChoice == OPTIONS_CHOICES.length) currentChoice = 0;
			}
			if (showLevels) {
				currentLevel++;
				if(currentLevel == OPTIONS_LEVELS.length) currentLevel = 0;
			}
		}
	}
	
	public void keyReleased(int k) {}
}

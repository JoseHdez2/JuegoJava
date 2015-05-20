package adv.level;

import java.awt.Color;
import java.awt.Graphics2D;

import adv.entity.EntitySloppy;
import bg.Background;
import entity.util.Dir4DEnum;
import entity.util.Vect2F;

public class Level1 extends LevelState {

	float playerSpeed = 1f;
	float playerJump = 3f;
	float playerGrav = 2f;
	
	int levelScore = 0;
	Color LEVEL_SCORE_COLOR = Color.RED;
	int LEVEL_SCORE_X = 10;
	int LEVEL_SCORE_Y = 20;
	
	int framesPlayerJump = 0;	// Time the player has been jumping.
	int MAX_JUMP = 60;			// Time the player can jump before falling.
	
	int framesPlayerFall = 0;	// Time the player has been in the air.
	int MAX_FALL = 120;			// Time the player can stay in the air before losing.
	
	boolean gameOver = false;	// Whether the game is over.
	int framesGameOver = 0;		// NTime the Game Over message has been shown.
	int MAX_OVER = 240;			// Time the Game Over message will show.
	
	Color GAME_OVER_COLOR = Color.RED;
	int GAME_OVER_X = 100;
	int GAME_OVER_Y = 100;
	
	int DEV_TEXT_SP = 20;
	
	public Level1(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		entities.add(new EntitySloppy(100, 10, 100, 100, "Player"));
		entities.add(new EntitySloppy(90, 190, 120, 10, "Plataforma"));
		bg = new Background(backgroundFile);
		entities.get(0).goY(Vect2F.DOWN, playerGrav);
		entities.get(0).spd.y = 1f;
//		setBackgroundEnabled(false);
	}
	
	public void update() {
		if (gameOver == true){
			framesGameOver++;
			
			if (framesGameOver >= MAX_OVER)
				gsm.setState(GameStateManager.STATE_MENU);
				
		}
		else {
			super.update();
			if(entities.get(0).spd.y > 0)
				framesPlayerFall++;
			if(entities.get(0).spd.y < 0)
				framesPlayerJump++;
			
			if (entities.get(0).cornerIsColliding.get(Dir4DEnum.BOTTOM_LEFT) ||
				entities.get(0).cornerIsColliding.get(Dir4DEnum.BOTTOM_RIGHT)){
				framesPlayerFall = framesPlayerJump = 0;
				levelScore += 1;
				entities.get(0).goY(Vect2F.UP, playerJump);
			}
			
			if (framesPlayerJump >= MAX_JUMP)
				entities.get(0).goY(Vect2F.DOWN, playerGrav);
			if (framesPlayerFall >= MAX_FALL)
				gameOver = true;
		}
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setColor(LEVEL_SCORE_COLOR);
		g.drawString(String.format("Score: %d", levelScore), LEVEL_SCORE_X, LEVEL_SCORE_Y);
		//Debug values
		g.drawString(String.format("Jump: %d", framesPlayerJump), 
				LEVEL_SCORE_X, LEVEL_SCORE_Y + DEV_TEXT_SP * 1);
		g.drawString(String.format("Fall: %d", framesPlayerFall), 
				LEVEL_SCORE_X, LEVEL_SCORE_Y + DEV_TEXT_SP * 2);
		if (gameOver) {
			g.setColor(GAME_OVER_COLOR);
			g.drawString("GAME OVER!", GAME_OVER_X, GAME_OVER_Y);
		}
		
	}
	
	public void keyPressed(int k) {
		super.keyPressed(k);
		if (k == KEY_LEFT)
			entities.get(0).goX(Vect2F.LEFT, playerSpeed);	// Player goes left.
		if (k == KEY_RIGHT)
			entities.get(0).goX(Vect2F.RIGHT, playerSpeed);	// Player goes right.
	}
	
	public void keyReleased(int k) {
		super.keyReleased(k);
//		if (k == KEY_LEFT || k == KEY_RIGHT)
//			entities.get(0).stopX();
	}
}

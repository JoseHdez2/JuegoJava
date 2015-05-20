package adv.level;

import java.awt.Color;
import java.awt.Graphics2D;

import adv.entity.EntitySloppy;
import bg.Background;
import entity.util.Dir4DEnum;
import entity.util.Vect2F;

public class Level1 extends LevelState {

	float playerSpeed = 1f;
	float playerGrav = 2f;
	
	int levelScore = 0;
	Color LEVEL_SCORE_COLOR = Color.RED;
	int LEVEL_SCORE_X = 10;
	int LEVEL_SCORE_Y = 10;
	
	
	
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
		super.update();
		if (entities.get(0).cornerIsColliding.get(Dir4DEnum.BOTTOM_LEFT) ||
			entities.get(0).cornerIsColliding.get(Dir4DEnum.BOTTOM_RIGHT)){
			levelScore += 1;
			
		}
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setColor(LEVEL_SCORE_COLOR);
		g.drawString(String.format("Score: %d", levelScore), LEVEL_SCORE_X, LEVEL_SCORE_Y);
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

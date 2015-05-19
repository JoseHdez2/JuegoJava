package adv.level;

import adv.entity.Entity3_Collidable;

public class Level1 extends LevelState {

	public Level1(GameStateManager gsm) {
		super(gsm);
		entities.add(new Entity3_Collidable(100, 100, 100, 100));
	}
	
	

}

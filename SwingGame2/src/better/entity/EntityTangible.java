package better.entity;

import java.awt.Rectangle;

public class EntityTangible extends EntityVisible {
	
	// TODO Maybe "isMoving" is redundant, since it just means "moveSpeed == 0"
	
	FourDirs<Boolean> isMoving = new FourDirs<Boolean>(false, false, false, false);
	FourDirs<Float> moveSpeed = new FourDirs<Float>(0f, 0f, 0f, 0f);
	
	public EntityTangible() {};
	public EntityTangible(Rectangle rect){
		super(rect);
	}
	
	/**
	 * Move towards a certain direction, in a constant speed.
	 * @param dir
	 * @param speed
	 */
	public void startMoving(Direction dir, Float speed){
		isMoving.set(dir, true);
		moveSpeed.set(dir, 0f);
	}
	
	/**
	 * Stop moving in a certain direction.
	 * @param dir
	 */
	public void stopMoving(Direction dir){
		isMoving.set(dir, false);
		moveSpeed.set(dir, 0f);
	}
	
	/**
	 * Move a single step towards the direction, in the amount.
	 * @param dir		Direction in which we move towards.
	 * @param amount	Amount of space covered in this step.
	 */
	public void move(Direction dir, float amount){
		switch (dir){
		case UP:	body.y -= amount;
		case DOWN:	body.y += amount;
		case LEFT:	body.x -= amount;
		case RIGHT:	body.x += amount;
		/*case UP:	body.translate((int)amount, (int)0);
		case DOWN:	body.y += amount;
		case LEFT:	body.x -= amount;
		case RIGHT:	body.x += amount;*/
		}
	}
	
	/**
	 * Move in any direction that we are currently moving in.
	 */
	public void update(){
		Direction[] allDirections = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
		for (Direction dir : allDirections){
			// If set to move in that direction,
			// move in that direction in the amount that we know from the speed.
			if (isMoving.get(dir) == true) move(dir, moveSpeed.get(dir));
		}
		
		if (isMoving.get(Direction.UP) == true) body.y -= moveSpeed.get(Direction.UP);
	}
}

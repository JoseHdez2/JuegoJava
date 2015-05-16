package entity;

import entity.util.Dir4D;
import entity.util.Dir4DEnum;
import entity.util.Vect2F;


/**
 * @author jose
 * An expansion based on the Entity3 class.
 * This class enforces collisions.
 */
public class Entity4 extends Entity3{

	// Speed at which we will move any colliding objects away from each other.
	float COLLISION_MENDING_SPEED = 10f;
	
	public Entity4(){
		super();
		debugName = "Entity4";
	}
	
	public void update(){
		super.update();
		
		// Update colliding
		
		// Mend / fix collisions.
		for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
			// If we detect that a corner is inside another entity...
			Vect2F oppositeDir = Vect2F.invert(Vect2F.get(dir));
			if (cornerIsColliding.get(dir)){
				this.go(oppositeDir, COLLISION_MENDING_SPEED);
			}
			//else{
			//	this.go(oppositeDir, 0);
			//}
		}
	}
}

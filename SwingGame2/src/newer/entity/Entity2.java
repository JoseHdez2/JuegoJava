package newer.entity;

import java.awt.Graphics2D;

import newer.entity.util.Dir4;
import newer.entity.util.Dir4Enum;
import newer.entity.util.Vect2;

/**
 * @author jose
 *	Moving is added.
 */
public class Entity2 extends Entity1 {
	
	public Vect2<Float> d = new Vect2<Float>(0f,0f);
	public Dir4<Boolean> isMoving = new Dir4<Boolean>(false, false, false, false);
	
	public Entity2(){
		super();
		debugName = "Entity2";
	}
	
	/**
	 * Have the object move in the direction and speed specified.
	 * @param dir	Directional vector, specifies the direction of the movement.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void go(Vect2<Float> dir, float speed){
		d.x = dir.x * speed;
		d.y = dir.y * speed;
	}
	
	/**
	 * Has the object completely stop its movement in all directions.
	 */
	public void stop(){
		d = new Vect2<Float>(0f,0f);
	}
	
	/* (non-Javadoc)
	 * @see newer.entity.Entity1#update()
	 */
	public void update(){
		body.x += d.x;
		body.y += d.y;
	}
	
	/* (non-Javadoc)
	 * @see newer.entity.Entity1#drawDebug(java.awt.Graphics2D)
	 */
	public void drawDebug (Graphics2D g){
		super.drawDebug(g);
		// Draw the speed.
		String spd = String.format("(%2.2f,%2.2f)", d.x, d.y);
		g.drawString(spd, body.x, body.y + DEBUG_SPACING * 2);
	}
}

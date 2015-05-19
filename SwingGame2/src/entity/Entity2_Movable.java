package entity;

import java.awt.Graphics2D;

import entity.util.Dir4;
import entity.util.Vect2;

/**
 * @author jose
 *	An expansion based on the Entity1 class.
 *	This class adds the possibility of movement.
 */
public class Entity2_Movable extends Entity1_Visible {
	
	// Speed at which this entity is moving in the X and Y planes.
	public Vect2<Float> spd = new Vect2<Float>(0f,0f);
	public Dir4<Boolean> isMoving = new Dir4<Boolean>(false, false, false, false);
	
	public Entity2_Movable(int x, int y, int w, int h){
		super(x,y,w,h);
		setDebugName("Entity2");
	}
	
	/*
	 * Update functions.
	 */
	
	public void update(){
		super.update();
		
		applySpeed();
	}
	
	void applySpeed(){
		body.x += spd.x;
		body.y += spd.y;
	}
	
	/*
	 * Draw functions.
	 */
	
	public void drawDebug (Graphics2D g){
		super.drawDebug(g);
		// Draw the speed.
		String strSpeed = String.format("(%2.2f,%2.2f)", spd.x, spd.y);
		g.drawString(strSpeed, body.x, body.y + DEBUG_SPACING * 2);
	}
	
	/*
	 * Utility functions.
	 */
	
	/**
	 * Have the object move in the direction and speed specified.
	 * @param dir	Directional vector, specifies the direction of the movement.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void go(Vect2<Float> dir, float speed){
		//float dirModulo = dir.x + dir.y; TODO normalize dir vector, just in case.
		spd.x = dir.x * speed;
		spd.y = dir.y * speed;
	}
	
	/**
	 * Same as go(), but we only change the X speed.
	 * @param dir	Specifies the direction. Only the X value is considered.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void goX(Vect2<Float> dir, float speed){
		float d_x = (dir.x >= 0) ? 1 : -1;
		spd.x += d_x * speed;
	}
	
	/**
	 * Same as go(), but we only change the Y speed.
	 * @param dir	Specifies the direction. Only the Y value is considered.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void goY(Vect2<Float> dir, float speed){
		float d_y = (dir.y >= 0) ? 1 : -1;
		spd.y += d_y * speed;
	}
	
	/**
	 * Has the object completely stop its movement in all directions.
	 */
	public void stop(){
		stopX();
		stopY();
	}
	
	/**
	 * Has the object stop its movement in the X direction.
	 */
	public void stopX(){
		spd.x = 0f;
	}
	
	/**
	 * Has the object stop its movement in the Y direction.
	 */
	public void stopY(){
		spd.y = 0f;
	}
}

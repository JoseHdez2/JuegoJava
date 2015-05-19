package adv.entity;

import java.awt.Graphics2D;

import entity.util.Vect2;

/**
 * @author jose
 *	An expansion based on the Entity1 class.
 *	This class adds movement functionality.
 */
public class Entity2_Movable extends Entity1_Visible {
	
	// Speed at which this entity is moving in the X and Y planes.
	public Vect2<Float> spd = new Vect2<Float>(0f,0f);
	
	/*
	 * Update functions.
	 */
	
	public void update(){
		applySpeed();
	}
	
	/**
	 * Called from update().
	 * Move the entity by its current speed vector.
	 */
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
	 * Change the speed of the entity.
	 * @param dir	Directional vector, specifies the direction of the movement.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void go(Vect2<Float> dir, float speed){
		//float dirModulo = dir.x + dir.y; TODO normalize dir vector, just in case.
		spd.x = dir.x * speed;
		spd.y = dir.y * speed;
	}
	
	/**
	 * Change the horizontal speed of the entity.
	 * @param dir	Specifies the direction. Only the X value is considered.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void goX(Vect2<Float> dir, float speed){
		float d_x = (dir.x >= 0) ? 1 : -1;
		spd.x += d_x * speed;
	}
	
	/**
	 * Change the vertical speed of the entity.
	 * @param dir	Specifies the direction. Only the Y value is considered.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void goY(Vect2<Float> dir, float speed){
		float d_y = (dir.y >= 0) ? 1 : -1;
		spd.y += d_y * speed;
	}
	
	/**
	 * Set the entity's speed to zero.
	 */
	public void stop(){
		stopX();
		stopY();
	}
	
	/**
	 * Set the entity's horizontal speed to zero.
	 */
	public void stopX(){
		spd.x = 0f;
	}
	
	/**
	 * Set the entity's vertical speed to zero.
	 */
	public void stopY(){
		spd.y = 0f;
	}

	/**
	 * @param x	Entity's initial horizontal position.
	 * @param y Entity's initial vertical position.
	 * @param w Entity's initial width.
	 * @param h Entity's initial height.
	 */
	public Entity2_Movable(int x, int y, int w, int h){
		super(x,y,w,h);
		setDebugName("Entity2");
	}
}

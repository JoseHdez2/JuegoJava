package adv.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import entity.util.Dir4D;
import entity.util.Dir4DEnum;

/**
 * @author jose
 * An expansion based on the Entity2 class.
 * This class adds collision checking.
 */
public class Entity3_Collidable extends Entity2_Movable{
	
	/*
	 * Collision
	 */
	
	// Toggle collision functionality.
	boolean collidable = true;
	
	public Dir4D<Boolean> cornerIsColliding = new Dir4D<Boolean>(false, false, false, false);
	
	final int DEBUG_CIRCLE_RADIUS = 5;
	final int DEBUG_CIRCLE_DIAMETER = DEBUG_CIRCLE_RADIUS * 2;
	final Color DEBUG_COLOR_CIRCLE_NO_COL = Color.GREEN;
	final Color DEBUG_COLOR_CIRCLE_COL = Color.RED;
	
	/*
	 *	Collision 
	 */
	public Entity3_Collidable(int x, int y, int w, int h){
		super(x,y,w,h);
		setDebugName("Entity3");
	}
	
	/*
	 * Update functions.
	 */
	
	public void update(ArrayList<Entity3_Collidable> collisionList){
		checkCornersForCollisions(collisionList);
		applySpeed();
//		applySpeedToHugBoxes();
	}
	
	/**
	 * For each corner, we ask: is it inside another Entity?
	 * @param collisionList	List of Entities we might be colliding with.
	 * @return whether corner collides with any Entity in collisionList.
	 */
	public void checkCornersForCollisions(ArrayList<Entity3_Collidable> collisionList){
		
		Dir4D<Point> corners = body.getCorners();

		cornerIsColliding = new Dir4D<Boolean>(false, false, false, false);
		for (Entity3_Collidable e : collisionList){
			if (e.equals(this))
				continue;
			for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
				if (e.body.contains(corners.get(dir)))
					cornerIsColliding.set(dir, true);
			}
		}
	}
	
	/*
	 * Draw functions.
	 */
	
	public void drawDebug(Graphics2D g){
		super.drawDebug(g);
		drawDebugCollisionCircles(g);
		
	}
	
	/**
	 * To be called from drawDebug().
	 * Draws the collision circles, to show the collision value for each corner.
	 * @param g
	 */
	void drawDebugCollisionCircles(Graphics2D g) {
		Dir4D<Point> corners = body.getCorners();
		for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){

			Color circleColor = (cornerIsColliding.get(dir)) ? 
				DEBUG_COLOR_CIRCLE_COL : DEBUG_COLOR_CIRCLE_NO_COL;
			g.setColor(circleColor);

			g.drawOval(corners.get(dir).x - DEBUG_CIRCLE_RADIUS, 
					corners.get(dir).y - DEBUG_CIRCLE_RADIUS,
					DEBUG_CIRCLE_DIAMETER,
					DEBUG_CIRCLE_DIAMETER);
		}
	}
}

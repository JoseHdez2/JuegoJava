package newer.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import newer.entity.util.Dir4D;
import newer.entity.util.Dir4DEnum;

public class Entity3 extends Entity2{
	
	public String debugName = "Entity3";
	
	final int DEBUG_CIRCLE_RADIUS = 5;
	final int DEBUG_CIRCLE_DIAMETER = DEBUG_CIRCLE_RADIUS * 2;
	final Color DEBUG_COLOR_CIRCLE_NO_COL = Color.BLUE;
	final Color DEBUG_COLOR_CIRCLE_COL = Color.RED;
	
	Dir4D<Boolean> cornerIsColliding = new Dir4D<Boolean>(false, false, false, false);
	
	public Entity3(){
		super();
		debugName = "Entity3";
	}
	
	/**
	 * For each corner, we ask all entities: "Is this Point inside you?".
	 * @param collisionList	List of Entities we might be colliding with.
	 * @return	Whether corner collides with any Entity in collisionList.
	 */
	public void checkCornersForCollisions(ArrayList<Entity1> collisionList){
		Dir4D<Point> corners = body.getCorners();
		for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
			// A priori, the corner is not inside any other entity.
			boolean cornerCollides = false;

			for (Entity1 e : collisionList){
				// If the corner is inside any other entity, set to true.
				if (e.body.contains(corners.get(dir)))
					cornerCollides = true;
			}
			cornerIsColliding.set(dir, cornerCollides);
		}
	}
	
	public void drawDebug(Graphics2D g){
		super.drawDebug(g);
		drawDebugCircles(g);
	}
	
	/**
	 * To be called from drawDebug().
	 * Draws the collision circles, to show the collision value for each corner.
	 * @param g
	 */
	void drawDebugCircles(Graphics2D g) {
		Dir4D<Point> corners = body.getCorners();
		for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
			// Color of circle depends on whether there is a collision.
			Color circleColor = (cornerIsColliding.get(dir)) ? 
				DEBUG_COLOR_CIRCLE_COL : DEBUG_COLOR_CIRCLE_NO_COL;
			g.setColor(circleColor);
			// Draw a centered circle.
			g.drawOval(corners.get(dir).x - DEBUG_CIRCLE_RADIUS, 
					corners.get(dir).y - DEBUG_CIRCLE_RADIUS,
					DEBUG_CIRCLE_DIAMETER,
					DEBUG_CIRCLE_DIAMETER);
		}
	}
}

package newer.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import newer.entity.util.Dir4D;
import newer.entity.util.Dir4DEnum;

public class Entity3 extends Entity2{
	
	final int DEBUG_CIRCLE_RADIUS = 5;
	final int DEBUG_CIRCLE_DIAMETER = DEBUG_CIRCLE_RADIUS * 2;
	final Color DEBUG_COLOR_CIRCLE_NO_COL = Color.RED;
	final Color DEBUG_COLOR_CIRCLE_COL = Color.GREEN;
	
	Dir4D<Boolean> cornerIsColliding = new Dir4D<Boolean>(false, false, false, false);
	
	/**
	 * For each corner, we ask all entities: "Is this Point inside you?".
	 * @param collisionList	List of Entities we might be colliding with.
	 * @return	Whether corner collides with any Entity in collisionList.
	 */
	public void checkCornersForCollisions(Entity1[] collisionList){
		Dir4D<Point> corners = body.getCorners();
		for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
			// A priori, the corner is not inside any other entity.
			cornerIsColliding.set(dir, false);
			for (Entity1 e : collisionList){
				// If the corner is inside another entity, set to true.
				if (e.body.contains(corners.get(dir)))
					cornerIsColliding.set(dir, true);
			}
		}
	}
	
	public void drawDebug(Graphics2D g){
		super.drawDebug(g);
		drawDebugCircles(g);
	}
	
	void drawDebugCircles(Graphics2D g) {
		Dir4D<Point> corners = body.getCorners();
		for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
			Color circleColor = (cornerIsColliding.get(dir)) ? Color.BLACK : Color.BLACK;
			if(cornerIsColliding.get(dir)) {
				g.setColor(DEBUG_COLOR_CIRCLE_COL);
			} else {
				g.setColor(DEBUG_COLOR_CIRCLE_NO_COL);
			}
			// Draw a centered circle.
			g.drawOval(corners.get(dir).x - DEBUG_CIRCLE_RADIUS, 
					corners.get(dir).y - DEBUG_CIRCLE_RADIUS,
					DEBUG_CIRCLE_DIAMETER,
					DEBUG_CIRCLE_DIAMETER);
		}
	}
}

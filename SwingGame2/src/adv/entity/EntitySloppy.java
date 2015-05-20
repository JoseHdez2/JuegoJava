package adv.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import entity.util.Dir4D;
import entity.util.Dir4DEnum;
import entity.util.Rect;
import entity.util.Vect2;

/**
 * @author jose
 *	You don't want to know.
 */
public class EntitySloppy {
	
	public Rect body;
	
	// Variables, part 1
	
	// Used in debugDraw. Spacing between the debug lines.
	public int DEBUG_SPACING = 20;
	// Name that will be displayed in debugDraw.
	public String debugName = "Entity";
	public Color colorDebugName = Color.BLACK;
	public Color colorDebugBody = Color.BLACK;

	// Variables, part 3
	
	public Dir4D<Boolean> cornerIsColliding = new Dir4D<Boolean>(false, false, false, false);
	// Variables used in drawing collision data.
	final int DEBUG_CIRCLE_RADIUS = 5;
	final int DEBUG_CIRCLE_DIAMETER = DEBUG_CIRCLE_RADIUS * 2;
	final Color DEBUG_COLOR_CIRCLE_NO_COL = Color.GREEN;
	final Color DEBUG_COLOR_CIRCLE_COL = Color.RED;
	
	/**
	 * @param x	Entity's initial horizontal position.
	 * @param y Entity's initial vertical position.
	 * @param w Entity's initial width.
	 * @param h Entity's initial height.
	 */
	public EntitySloppy(int x, int y, int w, int h){
		body = new Rect(x, y, w, h);
	}
	
	public EntitySloppy(int x, int y, int w, int h, String debugName){
		body = new Rect(x, y, w, h);
		setDebugName(debugName);
	}
	
	public void update(ArrayList<EntitySloppy> collisionList){
		applySpeed();
		checkCornersForCollisions(collisionList);
		applySpeed();
//		applySpeedToHugBoxes();
	}
	
	public void draw(Graphics2D g){
		drawDebug(g);
	}

	/**
	 * Draw the entity in debug mode.
	 * In this mode, the entity is a plain rectangle with debug values.
	 * @param g Graphics object.
	 */
	public void drawDebug(Graphics2D g){
		// Draw the body (the rectangle).
		g.setColor(colorDebugBody);
		g.drawRect(body.x, body.y, body.width, body.height);
		// Draw the name.
		g.setColor(colorDebugName);
		g.drawString(debugName, body.x, body.y);
		// Draw the position.
		String pos = "(" + body.x + "," + body.y + ")";
		g.drawString(pos, body.x, body.y + DEBUG_SPACING);
		// Draw the speed.
		String strSpeed = String.format("(%2.2f,%2.2f)", spd.x, spd.y);
		g.drawString(strSpeed, body.x, body.y + DEBUG_SPACING * 2);
		// Draw the collision data.
		drawDebugCollisionCircles(g);
	}

	/**
	 * @param debugName	Name that the entity will show when drawn in debug mode.
	 */
	public void setDebugName(String debugName) {
		this.debugName = debugName;
	}

	
	// Speed at which this entity is moving in the X and Y planes.
	public Vect2<Float> spd = new Vect2<Float>(0f,0f);
	
	/**
	 * Called from update().
	 * Move the entity by its current speed vector.
	 */
	void applySpeed(){
		body.x += spd.x;
		body.y += spd.y;
	}
	
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
//		float d_x = (dir.x >= 0) ? 1 : -1;
//		spd.x += d_x * speed;
//		spd.x += dir.x * speed;
		spd.x = dir.x * speed;
	}
	
	/**
	 * Change the vertical speed of the entity.
	 * @param dir	Specifies the direction. Only the Y value is considered.
	 * @param speed	Speed value, indicates the speed of the movement.
	 */
	public void goY(Vect2<Float> dir, float speed){
//		float d_y = (dir.y >= 0) ? 1 : -1;
//		spd.y += d_y * speed;
//		spd.y += dir.y * speed;
		spd.y = dir.y * speed;
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
	 * For each corner, we ask: is it inside another Entity?
	 * @param collisionList	List of Entities we might be colliding with.
	 * @return whether corner collides with any Entity in collisionList.
	 */
	public void checkCornersForCollisions(ArrayList<EntitySloppy> collisionList){
		
		Dir4D<Point> corners = body.getCorners();

		cornerIsColliding = new Dir4D<Boolean>(false, false, false, false);
		for (EntitySloppy e : collisionList){
			if (e.equals(this))
				continue;
			for (Dir4DEnum dir : Dir4D.ALL_DIRECTIONS){
				if (e.body.contains(corners.get(dir)))
					cornerIsColliding.set(dir, true);
			}
		}
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

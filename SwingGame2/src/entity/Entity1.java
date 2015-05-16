package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.util.Rect;

/**
 * @author jose
 *	Entity class, which represents objects in the game.
 *	Contains position and size of the object.
 *	Allows the object to be drawn in "debug" mode.
 */
public class Entity1{
	
	// Used in debugDraw. Spacing between the debug lines.
	public int DEBUG_SPACING = 20;
	
	// Rectangle that acts as the body of the object.
	// Used for tracking position and collision of the object.
	public Rect body = new Rect(100,100,100,100);
	
	// Name that will be displayed in debugDraw.
	public String debugName = "Entity1";
	public Color colorDebugName = Color.BLACK;
	public Color colorDebugBody = Color.BLACK;
	
	/**
	 *	Update the entity's logic: its position, speed... 
	 */
	public void update(){
		
	}
	
	/**
	 * Draw the entity to the screen.
	 * @param g	Graphics object.
	 */
	public void draw(Graphics2D g){
		drawDebug(g);
	}
	
	/**
	 * Draw the entity in debug mode.
	 * In this mode, it is a plain rectangle with debug values.
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
	}
	

}

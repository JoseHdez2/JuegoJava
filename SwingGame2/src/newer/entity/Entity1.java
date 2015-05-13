package newer.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import newer.entity.util.Rect;

/**
 * @author jose
 *	Entity class, which represents objects in the game.
 *	Contains position and size of the object.
 *	Allows the object to be drawn in "debug" mode.
 */
public class Entity1{
	
	public int DEBUG_SPACING = 20;
	
	public Rect body = new Rect(100,100,100,100);
	
	public String debugName = "Entity";
	public Color colorDebugName = Color.BLACK;
	public Color colorDebugBody = Color.BLACK;
	
	public void draw(Graphics2D g){
		drawDebug(g);
	}
	
	/**
	 * Draw the entity in debug mode.
	 * In this mode, it is a plain rectangle with debug values.
	 * @param g
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
	
	public void update(){
		
	}
}

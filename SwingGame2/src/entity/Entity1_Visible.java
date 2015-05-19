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
public class Entity1_Visible{
	
	// Used in debugDraw. Spacing between the debug lines.
	public int DEBUG_SPACING = 20;
	
	// Default body
	int DEFAULT_BODY_X = 100;
	int DEFAULT_BODY_Y = 100;
	int DEFAULT_BODY_W = 100;
	int DEFAULT_BODY_H = 100;
	
	// Rectangle that acts as the body of the object.
	// Used for tracking position and collision of the object.
	public Rect body = new Rect(DEFAULT_BODY_X,
								DEFAULT_BODY_Y,
								DEFAULT_BODY_W,
								DEFAULT_BODY_H);
	
	// Name that will be displayed in debugDraw.
	public String debugName = "Entity1";
	public Color colorDebugName = Color.BLACK;
	public Color colorDebugBody = Color.BLACK;
	
	/*
	public Entity1(){
		
	}*/
	
	public Entity1_Visible(int x, int y, int w, int h){
		body = new Rect(x, y, w, h);
	}
	
	public Entity1_Visible(int x, int y, int w, int h, String debugName){
		this(x, y, w, h);
		setDebugName(debugName);
	}
	
	/*
	 * Update functions.
	 */
	
	/**
	 *	Update the entity's logic: its position, speed... 
	 */
	public void update(){
		
	}
	
	/*
	 * Draw functions.
	 */
	
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

	/*
	 * Utility functions.
	 */
	
	public void setDebugName(String debugName) {
		this.debugName = debugName;
	}
	

}

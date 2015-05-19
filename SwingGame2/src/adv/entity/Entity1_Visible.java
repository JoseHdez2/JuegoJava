package adv.entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author jose

 *	Contains position and size of the object.
 *	Allows the object to be drawn in "debug" mode.
 */
public abstract class Entity1_Visible extends Entity0 {

	// Used in debugDraw. Spacing between the debug lines.
	public int DEBUG_SPACING = 20;
	
	// Name that will be displayed in debugDraw.
	public String debugName = "Entity1";
	public Color colorDebugName = Color.BLACK;
	public Color colorDebugBody = Color.BLACK;

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
	}

	/**
	 * @param debugName	Name that the entity will show when drawn in debug mode.
	 */
	public void setDebugName(String debugName) {
		this.debugName = debugName;
	}

	/*
	 * Constructor.
	 */
	
	/**
	 * @param x	Entity's initial horizontal position.
	 * @param y Entity's initial vertical position.
	 * @param w Entity's initial width.
	 * @param h Entity's initial height.
	 */
	public Entity1_Visible(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/*
	 * Update and draw.
	 */
	
	public void draw(Graphics2D g){
		drawDebug(g);
	}
}

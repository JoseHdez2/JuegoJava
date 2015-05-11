package better.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EntityVisible {
	
	protected Rectangle body = new Rectangle();
	
	protected String debugName = "ScreenEnt";
	protected Color debugColorName = Color.BLUE;
	protected Color debugColorBody = Color.BLUE;
	
	public EntityVisible(){
		body = new Rectangle(0,0,0,0);
	}
	
	public EntityVisible(Rectangle rect){
		body = rect;
	}
	
	public void update(){
		// To be implemented.
	}
	
	public void draw(Graphics2D g) {
		// To be implemented?
		drawDebug(g);
	}
	
	public void drawDebug(Graphics2D g) {
		
		// We draw the body of the entity.
		g.setColor(debugColorBody);
		g.drawRect(body.x, body.y, body.width, body.height);
		
		// We draw the name of the entity.
		g.setColor(debugColorName);
		g.drawString(debugName, (int)body.getCenterX(), (int)body.getCenterY());
	}
}

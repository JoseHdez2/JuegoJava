package adv.entity;

import java.awt.Graphics2D;

import entity.util.Rect;

/**
 * @author jose
 *	Entity class, which represents objects in the game.
 */
public abstract class Entity0 {

	public Rect body;
	
	/**
	 * @param x	Entity's initial horizontal position.
	 * @param y Entity's initial vertical position.
	 * @param w Entity's initial width.
	 * @param h Entity's initial height.
	 */
	public Entity0(int x, int y, int w, int h){
		body = new Rect(x, y, w, h);
	}
	
	/**
	 * Update the entity's logic.
	 */
	public abstract void update();
	
	/**
	 * Draw the entity.
	 * @param g Graphics object.
	 */
	public abstract void draw(Graphics2D g);
}

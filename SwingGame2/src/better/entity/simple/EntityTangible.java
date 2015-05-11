package better.entity.simple;

import java.awt.Rectangle;

import better.entity.Direction;
import better.entity.EntityVisible;

public class EntityTangible extends EntityVisible {
	
	public Vector<Float> d;
	
	public EntityTangible() {
		super();
	};
	public EntityTangible(Rectangle rect){
		super(rect);
	}
	
	/**
	 * Move in any direction that we are currently moving in.
	 */
	public void update(){
		super.update();
		body.x += d.x;
		body.y += d.y;
	}
	public Vector<Float> getD() {
		return d;
	}
	public void setD(float x, float y) {
		this.d.x = x;
		this.d.y = y;
	}
}

package entity.util;

import java.awt.Point;
import java.awt.Rectangle;


/**
 * @author jose
 *	Standard AWT Rectangle, with some additional functionality.
 */
public class Rect extends Rectangle {
	
	public Rect(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	/**
	 * @return	The four Points that comprise the Rectangle.
	 */
	public Dir4D<Point> getCorners() {
		Dir4D<Point> corners = new Dir4D<Point>(
				new Point((int)getMinX(), (int)getMinY()),	//top left corner
				new Point((int)getMaxX(), (int)getMinY()),	//top right corner
				new Point((int)getMinX(), (int)getMaxY()),	//bottom left corner
				new Point((int)getMaxX(), (int)getMaxY())		//bottom right corner
		);
		return corners;
	}
}

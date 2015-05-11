package better.entity;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class MapObject {
	
	int tileSize;
	
	Rectangle prevPos;
	Rectangle curPos;
	Rectangle nextPos;
	
	Point d;
	
	Point curTilePos;
	
	String debugID = "MapObject";	// Name displayed when drawDebug() is called.
	Color debugColor = Color.BLUE;	// Color used when drawDebug() is called.
	
	/**
	 * @param other	Other MapObject.
	 * @return		Whether both objects intersect.
	 */
	public boolean intersects(MapObject other) {
		return this.curPos.intersects(other.curPos);
	}
	
	/*
	void calcNextPos() {
		nextPos.x = curPos.x + d.x;
		nextPos.y = curPos.y + d.y;
	}
	
	void calcCurTilePos() {
		curTilePos.x = (int)curPos.x / tileSize;
		curTilePos.y = (int)curPos.y / tileSize;
	}*/
	
	public Rectangle getRectangle() {
		return new Rectangle();
	}
	
	public void calculateCorners(double x, double y){
		FourDirs<Integer> tile = new FourDirs<Integer>(0,0,0,0);
		double centerX = curPos.getCenterX();
		double centerY = curPos.getCenterY();
		tile.left = (int)(x - centerX) / tileSize;
		tile.right = (int)(x + centerX - 1) / tileSize;
		tile.up = (int)(y - centerY) / tileSize;
		tile.down = (int)(y + centerY - 1) / tileSize;
	}
	
	public void checkTileMapCollision(){
		//calcCurTilePos();
		//calcNextPos();
		
		if (d.y < 0) {
			
		}
	}
}

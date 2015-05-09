package entity;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class BetterMapObject {
	
	int tileSize;
	
	Rectangle prevPos;
	Rectangle curPos;
	Rectangle nextPos;
	
	Point d;
	
	Point curTilePos;
	
	public boolean intersects(BetterMapObject other) {
		return this.curPos.intersects(other.curPos);
	}
	
	void calcNextPos() {
		nextPos.x = curPos.x + d.x;
		nextPos.y = curPos.y + d.y;
	}
	
	void calcCurTilePos() {
		curTilePos.x = (int)curPos.x / tileSize;
		curTilePos.y = (int)curPos.y / tileSize;
	}
	
	public void checkTileMapCollision(){
		calcCurTilePos();
		calcNextPos();
		
		if (d.y < 0) {
			
		}
	}
}

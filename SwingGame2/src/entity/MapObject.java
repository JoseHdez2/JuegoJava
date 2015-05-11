package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import main.GamePanel;
import tile_map.Tile;
import tile_map.TileMap;

public abstract class MapObject {
	
	// Tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// Position and vector
	protected double p_x;
	protected double p_y;
	protected double d_x;
	protected double d_y;
	
	// Dimensions
	protected int width;
	protected int height;
	
	// Collision box
	protected int colWidth;
	protected int colHeight;
	
	// Collision
	protected int curRow;
	protected int curCol;
	protected double xDest;
	protected double yDest;
	protected double xTemp;
	protected double yTemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// Animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	// Movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	// Movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	// Deceleration speed.
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	// Constructor
	public MapObject (TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersects(MapObject other) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = other.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(
				(int)p_x - colWidth,
				(int)p_y - colHeight,
				colWidth,
				colHeight
			);
	}
	
	Point center() {
		return new Point(colWidth / 2, colHeight / 2);
	}
	
	public void calculateCorners(double x, double y) {
        int leftTile = (int)(x - center().x) / tileSize;
        int rightTile = (int)(x + center().x - 1) / tileSize;
        int topTile = (int)(y - center().y) / tileSize;
        int bottomTile = (int)(y + center().y - 1) / tileSize;
        if(
        		topTile < 0 || 							// 
        		bottomTile >= tileMap.getNumRows() ||	// 
        		leftTile < 0 || 						// 
        		rightTile >= tileMap.getNumCols()) {	// 
        	topLeft = topRight = bottomLeft = bottomRight = false;
        	return;
        }
        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);
        int br = tileMap.getType(bottomTile, rightTile);
        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;
}
	
	public void checkTileMapCollision() {
		
		curCol = (int)p_x / tileSize;
		curRow = (int)p_y / tileSize;
		
		xDest = p_x + d_x;
		yDest = p_y + d_y;
		
		xTemp = p_x;
		yTemp = p_y;
		
		calculateCorners(p_x, yDest);
		// Object is going up.
		if (d_y < 0) {
			// Either topLeft or topRight corners are solid.
			if(topLeft || topRight) {
				// Stop moving up 
				d_y = 0;
				// Put object just below the tile that we hit.
				yTemp = curRow * tileSize + colHeight / 2;
			}
			else {
				// Podemos seguir subiendo.
				yTemp += d_y;
			}
		}
		// Object is going down.
		if (d_y > 0) {
			if(bottomLeft || bottomRight) {
				d_y = 0;
				falling = false;
				yTemp = (curRow + 1) * tileSize - colHeight / 2;
			}
			else {
				// Podemos seguir bajando.
				yTemp += d_y;
			}
		}
		
		calculateCorners(xDest, p_y);
		if(d_x < 0) {
			if (topLeft || bottomLeft) {
				d_x = 0;
				xTemp = curCol * tileSize + colWidth / 2;
			}
			else {
				// Podemos seguir yendo a la izquierda.
				xTemp += d_x;
			}
		}
		if(d_x > 0) {
			if (topRight || bottomRight) {
				d_x = 0;
				xTemp = (curCol + 1) * tileSize - colWidth / 2;
			}
			else {
				// Podemos seguir yendo a la derecha.
				xTemp += d_x;
			}
		}
		
		if(!falling) {
			calculateCorners(p_x, yDest + 1);
			if(!bottomLeft && !bottomRight){
				//No hay suelo debajo; empieza a caer!
				falling = true;
			}
		}
	}

	public double getX() {
		return p_x;
	}

	public double getY() {
		return p_y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getcWidth() {
		return colWidth;
	}

	public int getcHeight() {
		return colHeight;
	}
	
	public void setPosition(double x, double y) {
		this.p_x = x;
		this.p_y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.d_x = dx;
		this.d_y = dy;
	}
	
	public void setMapPosition() {
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	//determine whether to draw the object
	public boolean notOnScreen() {
		boolean beyondLeft = p_x + xmap + width < 0;
		boolean beyondRight = p_x + xmap - width > GamePanel.WIDTH;
		boolean beyondUp = p_y + ymap + width < 0;
		boolean beyondDown = p_y + ymap - width > GamePanel.HEIGHT;
		
		return beyondLeft || beyondRight ||
				beyondUp || beyondDown;
	}
	
	public void draw(Graphics2D g){
		if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(p_x + xmap - width / 2),
				(int)(p_y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(p_x + xmap - width / 2 + width),
				(int)(p_y + ymap - height / 2),
				-width,
				height,
				null
			);
		}
	}
}

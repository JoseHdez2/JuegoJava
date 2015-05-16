package entity.util;

public class Dir4<T> {
	
	public static final Dir4Enum[] ALL_DIRECTIONS = {Dir4Enum.UP, Dir4Enum.DOWN, Dir4Enum.LEFT, Dir4Enum.RIGHT};
	
	public T up, down, left, right;
	
	public Dir4(T up, T down, T left, T right){
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Get any of the four data points.
	 * @param dir	Direction4 enum, for selection.
	 * @return	The chosen data point.
	 */
	public T get(Dir4Enum dir){
		switch(dir){
		case UP: return up;
		case DOWN: return down;
		case LEFT: return left;
		case RIGHT: return right;
		default: return up;//throw new Exception("Weird direction.");
		}
	}
	
	public void set(Dir4Enum dir, T value){
		switch(dir){
		case UP: up = value;
		case DOWN: down = value;
		case LEFT: left = value;
		case RIGHT: right = value;
		}
	}
}

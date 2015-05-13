package newer.entity.util;

/**
 *
 * @author jose
 *
 * @param <T>
 */
public class Dir4D<T> {

	public static final Dir4DEnum[] ALL_DIRECTIONS = 
		{Dir4DEnum.TOP_LEFT, Dir4DEnum.TOP_RIGHT, Dir4DEnum.BOTTOM_LEFT, Dir4DEnum.BOTTOM_RIGHT};
	
	public T topLeft, topRight, bottomLeft, bottomRight;
	
	public Dir4D(T topLeft, T topRight, T bottomLeft, T bottomRight){
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
	
	/**
	 * Get any of the four data points.
	 * @param dir	Direction4 enum, for selection.
	 * @return	The chosen data point.
	 */
	public T get(Dir4DEnum dir){
		switch(dir){
		case TOP_LEFT: return topLeft;
		case TOP_RIGHT: return topRight;
		case BOTTOM_LEFT: return bottomLeft;
		case BOTTOM_RIGHT: return bottomRight;
		default: return topLeft;//throw new Exception("Weird direction.");
		}
	}
	
	public void set(Dir4DEnum dir, T value){
		switch(dir){
		case TOP_LEFT: topLeft = value;
		case TOP_RIGHT: topRight = value;
		case BOTTOM_LEFT: bottomLeft = value;
		case BOTTOM_RIGHT: bottomRight = value;
		}
	}
}

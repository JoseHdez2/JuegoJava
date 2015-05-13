package newer.entity.util;

public class Vect2<T> {
	public T x, y;
	
	// Null vector.
	public static final Vect2<Float> NULL = new Vect2<Float>(0f, 0f);
	
	/*
	 * Orthogonal vectors.
	 * Y values are flipped so that they match the Java coordinates (bigger values mean lower positions).
	 */
	
	// Unitary vectors (orthogonal).
	public static final Vect2<Float> UP = new Vect2<Float>(0f, -1f);
	public static final Vect2<Float> DOWN = new Vect2<Float>(0f, 1f);
	public static final Vect2<Float> LEFT = new Vect2<Float>(-1f, 0f);
	public static final Vect2<Float> RIGHT = new Vect2<Float>(1f, 0f);
	
	// Unitary vectors (diagonal).
	public static final Vect2<Float> TOP_LEFT = new Vect2<Float>(-0.5f, -0.5f);
	public static final Vect2<Float> TOP_RIGHT = new Vect2<Float>(0.5f, -0.5f);
	public static final Vect2<Float> BOTTOM_LEFT = new Vect2<Float>(-0.5f, 0.5f);
	public static final Vect2<Float> BOTTOM_RIGHT = new Vect2<Float>(0.5f, 0.5f);
	
	/**
	 * Get corresponding unitary vector.
	 * For diagonal vectors, use Vect2#get(Dir4DEnum).
	 * @param dir	Direction.
	 * @return	Unitary vector of the chosen direction.
	 */
	public static Vect2<Float> get(Dir4Enum dir){
		switch(dir){
		case UP: return UP;
		case DOWN: return DOWN;
		case LEFT: return LEFT;
		case RIGHT: return RIGHT;
		default: return NULL;
		}
	}
	
	/**
	 * Get corresponding unitary vector.
	 * For orthogonal vectors, use Vect2#get(Dir4DEnum).
	 * @param dir	Direction.
	 * @return	Unitary vector of the chosen direction.
	 */
	public static Vect2<Float> get(Dir4DEnum dir){
		switch(dir){
		case TOP_LEFT: return TOP_LEFT;
		case TOP_RIGHT: return TOP_RIGHT;
		case BOTTOM_LEFT: return BOTTOM_LEFT;
		case BOTTOM_RIGHT: return BOTTOM_RIGHT;
		default: return NULL;
		}
	}
	
	public Vect2(T x, T y){
		this.x = x;
		this.y = y;
	}
}

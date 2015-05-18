package entity.util;

public class Vect2F extends Vect2<Float> {
	
	/*
	 * Static constants.
	 * These are generally useful, ready-made Vect2Fs.
	 * Y values are flipped so that they match the Java coordinates (bigger values mean lower positions).
	 */
	
	// Null vector.
	public static final Vect2F NULL = new Vect2F(0f, 0f);
	
	// Unitary vectors (orthogonal).
	public static final Vect2F UP = new Vect2F(0f, -1f);
	public static final Vect2F DOWN = new Vect2F(0f, 1f);
	public static final Vect2F LEFT = new Vect2F(-1f, 0f);
	public static final Vect2F RIGHT = new Vect2F(1f, 0f);
	
	// Unitary vectors (diagonal).
	public static final Vect2F TOP_LEFT = new Vect2F(-0.5f, -0.5f);
	public static final Vect2F TOP_RIGHT = new Vect2F(0.5f, -0.5f);
	public static final Vect2F BOTTOM_LEFT = new Vect2F(-0.5f, 0.5f);
	public static final Vect2F BOTTOM_RIGHT = new Vect2F(0.5f, 0.5f);
	
	/**
	 * Get corresponding unitary vector.
	 * For diagonal vectors, use Vect2#get(Dir4DEnum).
	 * @param dir	Direction.
	 * @return	Unitary vector of the chosen direction.
	 */
	public static Vect2F get(Dir4Enum dir){
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
	public static Vect2F get(Dir4DEnum dir){
		switch(dir){
		case TOP_LEFT: return TOP_LEFT;
		case TOP_RIGHT: return TOP_RIGHT;
		case BOTTOM_LEFT: return BOTTOM_LEFT;
		case BOTTOM_RIGHT: return BOTTOM_RIGHT;
		default: return NULL;
		}
	}
	
	public Vect2F(float x, float y){
		super(x,y);
	}
	
	/**
	 * Returns the inverse vector to this.
	 * Note that this vector will remain with the same values (use invert() to modify this vector).
	 * @return 
	 */
	public Vect2F inverted(){
		return new Vect2F(-this.x, -this.y);
	}
	
	/**
	 * Inverts the values of the vector, and returns the vector.
	 * This vector will now point in the opposite direction (a change of 180 degrees).
	 * @return 
	 */
	public Vect2F invert(){
		this.x = -x;
		this.y = -y;
		return this;
	}
	
	public String toString(){
		return String.format("(%f,%f)",this.x,this.y);
	}
}

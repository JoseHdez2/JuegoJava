package better.entity;

public class FourDirs<T> {
	public T up;
	public T down;
	public T left;
	public T right;
	
	public FourDirs(T u, T d, T l, T r){
		up = u;
		down = d;
		left = l;
		right = r;
	}
	
	public T get(Direction d){
		switch (d){
		case UP: return up;
		case DOWN: return down;
		case LEFT: return left;
		case RIGHT: return right;
		default: return up;	// TODO throw exception instead
		}
	}
	
	public void set(Direction d, T value){
		switch (d){
		case UP: up = value; break;
		case DOWN: down = value; break;
		case LEFT: left = value; break;
		case RIGHT: right = value; break;
		}
	}
}

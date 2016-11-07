package kdtree;

/** a color (coded by 3 int) combined to an id.
 */
public class RefColor extends PointI {
	private int id;

	RefColor(int x, int y, int z, int id) {
		v = new int[]{x, y, z}; 
		this.id = id;
	}

	RefColor(Point3i p, int id) {
		v = p.cloneValues(); 
		this.id = id;
	}

	int getId() { return id; }
	
	@Override
	public PointI zero() {
		return new RefColor(0,0,0,-1);
	}
}

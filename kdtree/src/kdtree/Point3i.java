package kdtree;

public class Point3i extends PointI {
	Point3i(int x, int y, int z) {
		v = new int[]{x, y, z}; 
	}
	
	@Override
	public PointI zero() {
		return new Point3i(0,0,0);
	}
}

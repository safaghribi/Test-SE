package kdtree;

public class Point2i extends PointI {
	Point2i(int x, int y) {
		v = new int[]{x, y}; 
	}
	
	@Override
	public PointI zero() {
		return new Point2i(0,0);
	}
}

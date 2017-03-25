package leaputils;

public class IntPoint {
	public int x,y;
	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void verticalReflect(int h) {
		this.y = h-y;
	}
}

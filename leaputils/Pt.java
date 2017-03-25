package leaputils;

public class Pt {
	public double x, y, z;
	public Pt(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void scale(double r, Pt zeroImg) {
		x = r*x + zeroImg.x;
		y = r*y + zeroImg.y;
		z = r*z + zeroImg.z;
	}
}

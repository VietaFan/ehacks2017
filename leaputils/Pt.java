package leaputils;
import Jama.*;

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
	public void project(Pt origin, Pt xunit, Pt yunit) {
		Matrix M = new Matrix(new double[][] {new double[] {origin.x, xunit.x, yunit.x}, 
			new double[] {origin.y, xunit.y, yunit.y},
			new double[] {origin.z, xunit.x, yunit.x}
		});
	}
}

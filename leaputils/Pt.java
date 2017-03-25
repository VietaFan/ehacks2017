package leaputils;
import Jama.*;

public class Pt {
	public double x, y, z;
	public Pt(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		//System.out.printf("(%f, %f, %f)\n", x, y, z);
	}
	public Pt(Pt other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	public void scale(double r, Pt zeroImg) {
		x = r*x + zeroImg.x;
		y = r*y + zeroImg.y;
		z = r*z + zeroImg.z;
	}
	public Pt project(Pt origin, Pt xunit, Pt yunit) {
		try {
			//System.out.printf("(%f, %f, %f) -> ", x, y, z);
			Matrix sol = new Matrix(new double[][] {new double[] {origin.x, xunit.x, yunit.x}, 
				new double[] {origin.y, xunit.y, yunit.y},
				new double[] {origin.z, xunit.z, yunit.z}
			}).solve(new Matrix(new double[][] { new double[] {x}, new double[]{y}, new double[] {z} }));
			return new Pt(sol.get(1, 0), sol.get(2, 0), 0);
		} catch (Exception e) {
			Pt p = project(new Pt(origin.x,origin.y,1), xunit, yunit);
			p.scale(1.0, new Pt(0,0,1));
			return p;
		}
	}
	public IntPoint toIntPoint() {
		return new IntPoint((int)x,(int)y);
	}
}

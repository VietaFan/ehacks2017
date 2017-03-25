import Jama.*;
public class Playground {
	public static void main(String[] args) {
		System.out.println("Hello, world\n");
		Matrix M = new Matrix(new double[][] { new double[] {3, 5, 6},
			new double[] {7, 19, 22}});
		M.print(3, 2);
		System.out.println(3^2);
		inHole(325, 158, 20, 331, 370, 331, 389);
		
	}
	public static boolean inHole(int hx, int hy, int d, int fingx1, int fingy1, int fingx2, int fingy2){
		// center = (hx + d/2, hy + d/2)
		
		double cx = hx + d/2.0;
		double cy = hy + d/2.0;
		/**
		double a1 = ((hx-fingx1)*(fingx2-fingx1)+(hy-fingy1)*(fingy2-fingy1))*((hx-fingx1)*(fingx2-fingx1)+(hy-fingy1)*(fingy2-fingy1));
		double a2 = (fingx2-fingx1)*(fingx2-fingx1)+(fingy2-fingy1)*(fingy2-fingy1);
		System.out.println("cx: " + cx);
		System.out.println("cy: " + cy);
		System.out.println("c^2: " + ((hx - fingx1)*(hx-fingx1) + (hy-fingy1)*(hy-fingy1)));
		System.out.println(a1/a2);
		return (hx - fingx1)*(hx-fingx1) + (hy-fingy1)*(hy-fingy1) < (d/2.0)*(d/2.0) + a1/a2;
		*/
		
		return((fingx1-cx)*(fingx1-cx) + (fingy1-cy)*(fingy1-cy) < (d/2.0)*(d/2.0) || 
				(fingx2-cx)*(fingx2-cx) + (fingy2-cy)*(fingy2-cy) < (d/2.0)*(d/2.0))
	}
}
import Jama.*;
public class Playground {
	public static void main(String[] args) {
		System.out.println("Hello, world\n");
		Matrix M = new Matrix(new double[][] { new double[] {3, 5, 6},
			new double[] {7, 19, 22}});
		M.print(3, 2);
	}
}
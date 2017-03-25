package graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneratePolygons {
	Random rand = new Random();
	
	public static void main(String[] args){
		GeneratePolygons genP = new GeneratePolygons();
		
		for(int i = 0; i < 5; i++){
			ArrayList<int[]> alist = genP.gen(5, 500, 500, 0.05);
			for(int[] arr : alist){
				System.out.println("x: " + arr[0] + " y: " + arr[1]);
			}
			System.out.println(genP.area(alist));
			System.out.println("NEXT");
		}

				
	}
	
	public GeneratePolygons(){}
	
	public double area(ArrayList<int[]> co){
		double ans = co.get(co.size()-1)[0] * co.get(0)[1] - co.get(co.size()-1)[1] * co.get(0)[0];
		for(int i = 0; i < co.size() - 1; i++){
			ans += co.get(i)[0] * co.get(i+1)[1] - co.get(i)[1] * co.get(i+1)[0];
		}
		
		return ans;
	}
	// size from 0 to 1; e.g. 0.03
	// Returns coordinates of an n-sided polygon given the width and height of the frame
	public ArrayList<int[]> gen(int n, int w, int h, double size){
		int polW = (int) (w*size);
		int polH = (int) (h*size);

		int cx = rand.nextInt(w - 40) + 20;
		int cy = rand.nextInt(h-40) + 20;

		ArrayList<int[]> coords = new ArrayList<int[]>();
		for(int i = 0; i < n; i++){
			int[] cd = {0, 0};
			cd[0] = (int) (cx + polW*Math.cos(2*Math.PI*i/n));
			cd[1] = (int) (cy + polW*Math.sin(2*Math.PI*i/n));
		
			coords.add(cd);
		
		}
		
		return coords;
		
	}
}

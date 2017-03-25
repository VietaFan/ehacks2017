package graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneratePolygons {
	static Random rand = new Random();
	public static void main(String[] args){
		for(int i = 0; i < 5; i++){
			for(int[] ar : gen(5, 500, 500, 0.03)){
				System.out.println("x: " + ar[0] + " y: " + ar[1]);
			}
			System.out.println("NEXT");
		}

				
	}
	
	// size from 0 to 1
	public static ArrayList<int[]> gen(int n, int w, int h, double size){
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

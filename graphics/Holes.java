package graphics;

import leaputils.*;
import java.awt.*;
import java.util.Random;

public class Holes extends GraphicsBase {
	private Random rand;
	private int delta;
	private long nextSet;
	public Holes(int width, int height, String titleStr) {
		super(width, height, titleStr);
		rand = new Random();
		delta = 3000;
		nextSet = System.currentTimeMillis();
	}

	@Override
	public void update() {
		if(System.currentTimeMillis()-nextSet > delta){

			for(int i = 0; i < 5; i++){
				int cx = rand.nextInt(580)+20;
				int cy = rand.nextInt(420)+20;
				bufWin.setColor(Color.BLUE);
				bufWin.drawOval(cx, cy, 20, 20);
			}
			nextSet = System.currentTimeMillis();
		}
		
	}
	
	public static void main(String[] args) {
		Holes hs = new Holes(640, 480, "Holes");
		hs.run();
		System.exit(0);
	}
}

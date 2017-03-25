package graphics;

import leaputils.*;
import java.awt.*;

public class RandomShapes extends GraphicsBase {
	private LeapReader lr;
	public RandomShapes(int width, int height, String titleStr) {
		super(width, height, titleStr);
	}

	@Override
	public void update() {
		InfoPoint ip = lr.poll();
		ip.scale(1.2, new Pt(320, 0, 0));
		int palmx = (int)ip.palm.x, palmy = (int)ip.palm.y;
		bufWin.setColor(Color.BLUE);
		bufWin.drawOval(palmx, palmy, 3, 3);
		for (int i=0; i<5; ++i) {
			bufWin.drawLine(palmx, palmy, (int)ip.fpts[i][0][0].x, (int)ip.fpts[i][0][0].y);
			for (int j=0; j<4; j++) {
				bufWin.drawLine((int)ip.fpts[i][j][0].x, (int)ip.fpts[i][j][0].y, 
						(int)ip.fpts[i][j][1].x, (int)ip.fpts[i][j][1].y);
			}
		}
	}
	
	public static void main(String[] args) {
		RandomShapes rs = new RandomShapes(640, 480, "Random Shapes");
		rs.run();
		System.exit(0);
	}
}

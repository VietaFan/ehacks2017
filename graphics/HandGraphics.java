package graphics;
import leaputils.*;
import java.awt.*;

public class HandGraphics extends GraphicsBase {
	private LeapReader lr;
	public HandGraphics(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;
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
		HandGraphics hg = new HandGraphics(640, 480, "Hand Representer", new LeapReader());
		hg.run();
		System.exit(0);
	}
}

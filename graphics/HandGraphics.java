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
		//IntInfoPoint proj = ip.intProject(new Pt(0, 200, 0), new Pt(1, 1, 0), new Pt(0, 1, 1));
		IntInfoPoint proj = ip.toIntInfoPoint();
		bufWin.setColor(Color.BLUE);
		bufWin.drawOval(proj.palm.x, proj.palm.y, 3, 3);
		for (int i=0; i<5; ++i) {
			bufWin.drawLine(proj.palm.x, proj.palm.y, proj.fpts[i][0][0].x, proj.fpts[i][0][0].y);
			bufWin.drawLine(proj.wrist.x, proj.wrist.y, proj.fpts[i][0][0].x, proj.fpts[i][0][0].y);
			for (int j=0; j<4; j++) {
				bufWin.drawLine(proj.fpts[i][j][0].x, proj.fpts[i][j][0].y, 
						proj.fpts[i][j][1].x, proj.fpts[i][j][1].y);
			}
		}
	}
	
	public static void main(String[] args) {
		HandGraphics hg = new HandGraphics(640, 480, "Hand Representer", new LeapReader());
		hg.run();
		System.exit(0);
	}
}

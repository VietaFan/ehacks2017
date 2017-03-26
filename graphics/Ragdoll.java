package graphics;

import java.awt.Color;

import leaputils.InfoPoint;
import leaputils.IntInfoPoint;
import leaputils.LeapReader;
import leaputils.Pt;

public class Ragdoll extends GraphicsBase{

	private LeapReader lr;
	

	
	public Ragdoll(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;	
		// TODO Auto-generated constructor stub
	}
	
	public void init1(){
		bkgColor = Color.WHITE;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ragdoll rd = new Ragdoll(640, 480, "Hand Representer", new LeapReader());
		rd.init1();
		
		rd.run();
		System.exit(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		InfoPoint ip = lr.poll();
		ip.scale(1.2, new Pt(320, -180, 0));
		IntInfoPoint proj = ip.toIntInfoPoint();
		proj.verticalReflect(height);
		bufWin.setColor(Color.BLUE);
//		bufWin.drawOval(proj.palm.x, proj.palm.y, 3, 3);
		bufWin.drawLine(500, 100, 500, 200);
		//draw head
		bufWin.drawOval(proj.fpts[2][3][1].x, proj.fpts[2][3][1].y, 30, 30);
		//draw body line
		bufWin.drawLine(proj.fpts[2][3][1].x+15, proj.fpts[2][3][1].y+30, proj.palm.x, proj.palm.y);
		//draw left arm
		bufWin.drawLine(proj.fpts[1][3][1].x, proj.fpts[1][3][1].y, proj.fpts[2][3][1].x, proj.fpts[2][3][1].y+30);
		//draw right arm
		bufWin.drawLine(proj.fpts[3][3][1].x, proj.fpts[3][3][1].y, proj.fpts[2][3][1].x, proj.fpts[2][3][1].y+30);
		//draw left leg
		bufWin.drawLine(proj.fpts[0][3][1].x, proj.fpts[0][3][1].y, proj.fpts[2][3][1].x, proj.fpts[2][3][1].y+75);
		//draw right leg
		bufWin.drawLine(proj.fpts[0][3][1].x, proj.fpts[0][3][1].y, proj.fpts[2][3][1].x, proj.fpts[2][3][1].y+75);
		
//		for (int i=0; i<5; ++i) {
////			bufWin.drawLine(proj.palm.x, proj.palm.y, proj.fpts[i][0][0].x, proj.fpts[i][0][0].y);
////			bufWin.drawLine(proj.wrist.x, proj.wrist.y, proj.fpts[i][0][0].x, proj.fpts[i][0][0].y);
////			for (int j=0; j<4; j++) {
////				bufWin.drawLine(proj.fpts[i][j][0].x, proj.fpts[i][j][0].y, 
////						proj.fpts[i][j][1].x, proj.fpts[i][j][1].y);
////			}
//			
//			
//			
//		}
		
		
		
		
	}

}

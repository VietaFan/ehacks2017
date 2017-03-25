package graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.util.Arrays;


import leaputils.*;

public class Lightsaber extends GraphicsBase {
	private LeapReader lr;
	
	public Lightsaber(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;
	}

	
	public void update() {
		InfoPoint ip = lr.poll();
		Graphics2D gr = buf.createGraphics();
		ip.scale(1.2, new Pt(320, 0, 0));

		double[] direction = new double[3];
		IntInfoPoint proj = ip.toIntInfoPoint();
		bufWin.setColor(Color.BLUE);
		bufWin.drawOval(proj.palm.x, height - proj.palm.y, 3, 3);
		for (int i=0; i<5; ++i) {
			bufWin.drawLine(proj.palm.x, height - proj.palm.y, proj.fpts[i][0][0].x, height - proj.fpts[i][0][0].y);
			bufWin.drawLine(proj.wrist.x, height - proj.wrist.y, proj.fpts[i][0][0].x, height - proj.fpts[i][0][0].y);
			for (int j=0; j<4; j++) {
				bufWin.drawLine(proj.fpts[i][j][0].x, height - proj.fpts[i][j][0].y, 
						proj.fpts[i][j][1].x, height - proj.fpts[i][j][1].y);
				
				if(j != 3){			
					double[] dir1 = {ip.fpts[i][j][0].x - ip.fpts[i][j][1].x, 
							0-proj.fpts[i][j][0].y + ip.fpts[i][j][1].y,
							ip.fpts[i][j][0].z - ip.fpts[i][j][0].z};
					double[] dir2 = {ip.fpts[i][(j+1)%4][0].x - ip.fpts[i][(j+1)%4][1].x, 
							0-ip.fpts[i][(j+1)%4][0].y + ip.fpts[i][(j+1)%4][1].y,
							ip.fpts[i][(j+1)%4][0].z - ip.fpts[i][(j+1)%4][1].z};
					double[] dir = getNormal(dir1, dir2);
					
					System.out.println(Arrays.toString(dir1));
					//System.out.println(Arrays.toString(dir2));
					for(int k = 0; k < 3; k++){
						direction[k] += dir[k];
						//System.out.println(dir[k]);
					}
				}
			}	
		}
		
		double xdir = (direction[0] * 250)/(Math.sqrt(direction[0]*direction[0] + direction[1]*direction[1]));
		double ydir = (direction[1] * 250)/(Math.sqrt(direction[0]*direction[0] + direction[1]*direction[1]));
		bufWin.drawLine(proj.fpts[1][3][1].x, height - proj.fpts[1][3][1].y, (int) (proj.fpts[1][3][1].x-xdir), (int) (height - proj.fpts[1][3][1].y-ydir));
		//System.out.println(direction[0] + " " + direction[1]);
	}
	
	// cross product of v1, v2
	public double[] getNormal(double[] v1, double[] v2){
		double[] normal = new double[3];
		normal[0] = v1[1]*v2[2] - v1[2]*v2[1];
		normal[1] = v1[2]*v2[0] - v1[0]*v2[2];
		normal[2] = v1[0]*v2[1] - v1[1]*v2[0];
		return normal;
	}
	
	public static void main(String[] args) {
		Lightsaber ls = new Lightsaber(640, 480, "Lightsaber Test", new LeapReader());
		ls.run();
		System.exit(0);
	}
}

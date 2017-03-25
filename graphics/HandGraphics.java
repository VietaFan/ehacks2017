package graphics;
import leaputils.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class HandGraphics extends GraphicsBase {
	private LeapReader lr;
	Random rand;
	boolean holes;
	boolean polygons;
	private int deltaHoles;
	private long nextSet;
	private ArrayList<int[]> coords;
	private GameState state;
	
	public HandGraphics(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;
	
	}
	
	public HandGraphics(int width, int height, String titleStr, LeapReader lr, boolean holes, boolean polygons) {
		super(width, height, titleStr);
		this.lr = lr;
		this.holes = holes;
		rand = new Random();
		deltaHoles = 1000;
		nextSet = System.currentTimeMillis();
		coords = new ArrayList<int[]>();
		state = new GameState(3);
	}

	@Override
	public void update() {
		InfoPoint ip = lr.poll();
		ip.scale(1.2, new Pt(320, 0, 0));
		//IntInfoPoint proj = ip.intProject(new Pt(0, 200, 0), new Pt(1, 1, 0), new Pt(0, 1, 1));
		IntInfoPoint proj = ip.toIntInfoPoint();
		bufWin.setColor(Color.BLUE);
		bufWin.drawOval(proj.palm.x, height - proj.palm.y, 3, 3);
		for (int i=0; i<5; ++i) {
			bufWin.drawLine(proj.palm.x, height - proj.palm.y, proj.fpts[i][0][0].x, height - proj.fpts[i][0][0].y);
			bufWin.drawLine(proj.wrist.x, height - proj.wrist.y, proj.fpts[i][0][0].x, height - proj.fpts[i][0][0].y);
			for (int j=0; j<4; j++) {
				bufWin.drawLine(proj.fpts[i][j][0].x, height - proj.fpts[i][j][0].y, 
						proj.fpts[i][j][1].x, height - proj.fpts[i][j][1].y);
			}
		}
		
		if(holes){
			
			int[] cds = {-100, -100};
			if(System.currentTimeMillis() - nextSet > deltaHoles){
					cds[0] = rand.nextInt(580)+20;
					cds[1] = rand.nextInt(420)+20;
					bufWin.drawOval(cds[0], cds[1], 20, 20);
					coords.add(cds);			
				nextSet = System.currentTimeMillis();
			}
			ArrayList<int[]> toRemove = new ArrayList<int[]>();
			for(int[] cdes: coords){
				boolean removed = false;
				for (int i=0; i<5; ++i) {
					for (int j=0; j<4; j++) {
						if(inHole(cdes[0], cdes[1], 20, proj.fpts[i][j][0].x, height - proj.fpts[i][j][0].y, proj.fpts[i][j][1].x, height - proj.fpts[i][j][1].y)){
							toRemove.add(cdes);
							System.out.println(cdes[0] + " " + cdes[1] + " " + proj.fpts[i][j][0].x + " " + (height - proj.fpts[i][j][0].y) + " " + proj.fpts[i][j][1].x + " " +  (height - proj.fpts[i][j][1].y));
							removed = true;
						}
					}					
				}
				if(!removed){
					bufWin.setColor(Color.RED);
					bufWin.drawOval(cdes[0], cdes[1], 20, 20);
				}

			}
			for (int[] cdes: toRemove) {
				coords.remove(cdes);
			}
		}
		
	}
	
	public boolean inHole(int hx, int hy, int d, int fingx1, int fingy1, int fingx2, int fingy2){
		// center = (hx + d/2, hy + d/2)
		double cx = hx + d/2.0;
		double cy = hy + d/2.0;
		/**
		double a1 = ((hx-fingx1)*(fingx2-fingx1)+(hy-fingy1)*(fingy2-fingy1))*((hx-fingx1)*(fingx2-fingx1)+(hy-fingy1)*(fingy2-fingy1));
		double a2 = (fingx2-fingx1)*(fingx2-fingx1)+(fingy2-fingy1)*(fingy2-fingy1);
		return (hx - fingx1)*(hx-fingx1) + (hy-fingy1)*(hy-fingy1) < (d/2.0)*(d/2.0) + a1/a2;
		*/
		return((fingx1-cx)*(fingx1-cx) + (fingy1-cy)*(fingy1-cy) < (d/2.0)*(d/2.0) || 
				(fingx2-cx)*(fingx2-cx) + (fingy2-cy)*(fingy2-cy) < (d/2.0)*(d/2.0));
	}
	
	public static void main(String[] args) {
		HandGraphics hg = new HandGraphics(640, 480, "Hand Representer", new LeapReader(), true, true);
		hg.run();
		System.exit(0);
	}
}

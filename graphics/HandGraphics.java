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
	
	public HandGraphics(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;
	
	}
	
	public HandGraphics(int width, int height, String titleStr, LeapReader lr, boolean holes, boolean polygons) {
		super(width, height, titleStr);
		this.lr = lr;
		this.holes = holes;
		rand = new Random();
		deltaHoles = 3000;
		nextSet = System.currentTimeMillis();
		coords = new ArrayList<int[]>();
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
		
		if(holes){
			
			int[] cds = {-100, -100};
			if(System.currentTimeMillis() - nextSet > deltaHoles){
				for(int i = 0; i < 5; i++){
					cds[0] = rand.nextInt(580)+20;
					cds[1] = rand.nextInt(420)+20;
					bufWin.drawOval(cds[0], cds[1], 20, 20);
					coords.add(cds);
				}
				nextSet = System.currentTimeMillis();
			}
			for(int[] cdes: coords){
				bufWin.setColor(Color.RED);
				bufWin.drawOval(cdes[0], cdes[1], 20, 20);
			}
			
			
		}
		
	}
	
	public static void main(String[] args) {
		HandGraphics hg = new HandGraphics(640, 480, "Hand Representer", new LeapReader(), true, true);
		hg.run();
		System.exit(0);
	}
}

package graphics;
import leaputils.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import gamemech.*;

public class HandGraphics extends GraphicsBase {
	private LeapReader lr;
	Random rand;
	boolean holes;
	boolean polygons;
	private int deltaHoles;
	private long nextSet, nextShapeTime;
	private ArrayList<int[]> coords;
	private GameShape nextShape;
	GameState state;
	private InputMonitor inmtr;
	
	public HandGraphics(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;	
		rand = new Random();
	}
	
	
	public HandGraphics(int width, int height, String titleStr, LeapReader lr, boolean holes, boolean polygons) {
		super(width, height, titleStr);
		this.lr = lr;
		rand = new Random();
		init1(holes, polygons);
	}
	
	public void init1(boolean holes, boolean polygons) {
		this.holes = holes;
		deltaHoles = 1000;
		nextSet = System.currentTimeMillis();
		coords = new ArrayList<int[]>();
		state = new GameState(1, 3);
		inmtr = new InputMonitor(this);
		nextShapeTime = System.currentTimeMillis()+state.getShapeDelay();
		nextShape = GameShape.getNextShape(0, nextShapeTime);
		bkgColor = Color.WHITE;
	}
	
	public void update1() {
		InfoPoint ip = lr.poll();
		ip.scale(1.2, new Pt(320, -180, 0));
		//IntInfoPoint proj = ip.intProject(new Pt(0, 200, 0), new Pt(1, 1, 0), new Pt(0, 1, 1));
		IntInfoPoint proj = ip.toIntInfoPoint();
		proj.verticalReflect(height);
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
		
		bufWin.drawString(String.format("Lives remaining: %d", state.getLives()), 100, 100);
		bufWin.drawString(String.format("Current Score: %d", state.getScore()), 100, 70);
				
		long curTime = System.currentTimeMillis();
		
		int r = Math.min(255,(int)(255.0*nextShape.getFracDone()));
		bufWin.setColor(new Color(r, 255-r, 0));
		Rectangle box = (Rectangle)nextShape.getCurrentPos();
		bufWin.drawRect(box.x, box.y, box.width, box.height);
		
		if (curTime > nextShapeTime) {
			if (nextShape.intersects(proj)) {
				state.die();
			} else if (nextShape.contains(proj)) {
				state.score(nextShape.getValue());
			}
			nextShape = GameShape.getNextShape(curTime-state.getLastRestart(), curTime + state.getShapeDelay());
			nextShapeTime = curTime + state.getShapeDelay();
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
						if(inHole(cdes[0], cdes[1], 20, proj.fpts[i][j][0].x, proj.fpts[i][j][0].y, proj.fpts[i][j][1].x, proj.fpts[i][j][1].y)){
							toRemove.add(cdes);
							// System.out.println(cdes[0] + " " + cdes[1] + " " + proj.fpts[i][j][0].x + " " + (height - proj.fpts[i][j][0].y) + " " + proj.fpts[i][j][1].x + " " +  (height - proj.fpts[i][j][1].y));
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
				state.score(1);
			}
		}
	}
	
	public void update2() {
		InfoPoint ip = lr.poll();
		Graphics2D gr = buf.createGraphics();
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
			Color handColor1 = new Color(Math.min(255, 7*Math.abs(proj.fpts[i][0][0].x - proj.fpts[i][0][1].x)), Math.min(255, 7*Math.abs(proj.fpts[i][1][0].x - proj.fpts[i][1][1].x)),
					Math.min(255, 7*Math.abs(proj.fpts[i][2][0].x - proj.fpts[i][2][1].x)));
			Color handColor2 = new Color(Math.min(255, 7*Math.abs(proj.fpts[i][1][0].x - proj.fpts[i][1][1].x)), Math.min(255, 7*Math.abs(proj.fpts[i][2][0].x - proj.fpts[i][2][1].x)),
					Math.min(255, 7*Math.abs(proj.fpts[i][3][0].x - proj.fpts[i][3][1].x)));
			Color handColor3 = new Color(Math.min(255, 7*Math.abs(proj.fpts[i][2][0].x - proj.fpts[i][2][1].x)), Math.min(255, 7*Math.abs(proj.fpts[i][3][0].x - proj.fpts[i][3][1].x)),
					Math.min(255, 7*Math.abs(proj.fpts[i][0][0].x - proj.fpts[i][0][1].x)));
			//gr.setBackground(handColor1);
			//gr.clearRect(0, 0, width/2, height/2);
			//gr.setBackground(handColor2);
			//gr.clearRect(width/2, 0, width, height/2);
			bufWin.setColor(handColor1);
			bufWin.fillRect(0, 0, width/2, height/2);
			bufWin.setColor(handColor2);
			bufWin.fillRect(width/2, 0, width, height/2);
			bkgColor = handColor3;
		
		}
	}
	
	public void update3() {
		ArrayList<InfoPoint> iplist = lr.pollAll();
		InfoPoint first = iplist.get(0), second = iplist.get(1);
		double theta = Math.atan2(second.palm.y-first.palm.y, second.palm.x-first.palm.x);
		
	}
		
	@Override
	public void update() {
		switch(state.getMode()) {
		case 1:
			if (state.hasRestarted()) {
				init1(true, true);
				state.hasRestarted();
			}
			update1();
			break;
		case 2:
			update2();
			break;
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
		HandGraphics hg = new HandGraphics(640, 480, "Hand Representer", new LeapReader());
		hg.init1(true, true);
		hg.run();
		System.exit(0);
	}
}

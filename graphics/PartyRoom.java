package graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import leaputils.*;

// If the user has an item, then moving around and such will reveal the password
public class PartyRoom {
	private LeapReader lr;
	private int[] rex = {16, 17, 18, 19, 20, 21, 23, 24, 26, 27, 30, 32, 33, 35, 36, 38, 40, 42, 43, 46, 48, 50, 51, 52, 53, 55};
	private static boolean[] fallenOff = new boolean[64];
	private ArrayList<Integer> order = new ArrayList<Integer>();
	private int deltaTime = 200;
	private long current;
	private int counter;
	public boolean hasHammer = true; // should be an instance variable of each game
	
	BufferedImage buf;
	Graphics bufWin;
	int width, height;
	public PartyRoom(int width, int height, String titleStr, LeapReader lr) {
		buf = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		bufWin = buf.getGraphics();
		this.width = width; this.height = height;
		this.lr = lr;
		for(int i = 0; i < 64; i++){
			order.add(i);
		}
		Collections.shuffle(order);
		current = System.currentTimeMillis();
		counter = 0;
	}

	
	public void update() {
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
			
			for(int j = 0; j < 8; j++){
				for(int k = 0; k < 8; k++){
					if(fallenOff[8*k+j]){			
						if(containsInt(rex, 8*k+j)){
							bufWin.setColor(handColor2);
							bufWin.fillRect(width*j/8, height*k/8, width*(j+1)/8, height*(k+1)/8); 
							System.out.println("HANDCOLOR2");
						}else{
							bufWin.setColor(handColor3);
							bufWin.fillRect(width*j/8, height*k/8, width*(j+1)/8, height*(k+1)/8); 
							System.out.println("j: " + j);
						}
					}else{
						bufWin.setColor(handColor1);
						bufWin.fillRect((width*j)/8, (height*k)/8, (width*(j+1))/8, (height*(k+1))/8); 
						//System.out.println("HANDCOLOR1");
					}					
				}
			}
		}

			if(hasHammer && System.currentTimeMillis() - current > deltaTime && counter <64){				
				fallenOff[order.get(counter)] = true;					
				counter++;
				current = System.currentTimeMillis();
			}
			
			//System.out.println(counter);

	}
		
	public boolean containsInt(int[] arr, int v){
		for(int i : arr){
			if(i==v) return true;
		}
		return false;
	}
/*
	public static void main(String[] args) {
		PartyRoom db = new PartyRoom(640, 480, "Party Room", new LeapReader());
		db.run();
		System.exit(0);
	}*/
}

package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


import leaputils.*;

// A room leading to the party room.
public class Limbo {
	private LeapReader lr;
	BufferedImage img = null;
	public boolean hasHammer = true; // should be an instance variable of each game
	private ArrayList<Integer> order = new ArrayList<Integer>();
	BufferedImage buf;
	Graphics bufWin;
	int width, height;
	public Limbo(int width, int height, String titleStr, LeapReader lr) {
		buf = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		bufWin = buf.getGraphics();
		this.width = width; this.height = height;
		this.lr = lr;

		try {
		    img = ImageIO.read(new File("images/limbo.jpg"));
		    System.out.println(0);
		} catch (IOException e) {
			System.out.println(Thread.currentThread().getStackTrace());
		}
	}

	
	public void update() {
		InfoPoint ip = lr.poll();
		Graphics2D gr = buf.createGraphics();
		ip.scale(1.2, new Pt(320, 0, 0));
		//IntInfoPoint proj = ip.intProject(new Pt(0, 200, 0), new Pt(1, 1, 0), new Pt(0, 1, 1));
		IntInfoPoint proj = ip.toIntInfoPoint();
		bufWin.drawImage(img, 0, 0, null);
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
		
		if(250 <= proj.palm.x && proj.palm.x <= 315 && 450 <= proj.palm.y){
			System.out.println(1);
			// Return to restroom
		}
		
	}
/*
	public static void main(String[] args) {
		Limbo lim = new Limbo(640, 480, "Limbo", new LeapReader());
		lim.run();
		System.exit(0);
	}*/
}

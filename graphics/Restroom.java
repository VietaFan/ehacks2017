package graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import leaputils.*;

// A room leading to the party room.
public class Restroom extends GraphicsBase {
	private LeapReader lr;
	BufferedImage img = null;
	public boolean hasHammer = true; // should be an instance variable of each game
	private ArrayList<Integer> order = new ArrayList<Integer>();
	private int deltaTime = 200;
	private long current;
	private int counter;
	private boolean inside;
	
	public Restroom(int width, int height, String titleStr, LeapReader lr) {
		super(width, height, titleStr);
		this.lr = lr;
		for(int i = 0; i < 64; i++){
			order.add(i);
		}
		Collections.shuffle(order);
		current = System.currentTimeMillis();
		counter = 0;
		try {
		    img = ImageIO.read(new File("images/restroom.jpeg"));
		    System.out.println(0);
		} catch (IOException e) {
			
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
			Color handColor1 = new Color(Math.min(255, 7*Math.abs(proj.fpts[i][0][0].x - proj.fpts[i][0][1].x)), Math.min(255, 7*Math.abs(proj.fpts[i][1][0].x - proj.fpts[i][1][1].x)),
					Math.min(255, 7*Math.abs(proj.fpts[i][2][0].x - proj.fpts[i][2][1].x)));
			
			if(proj.palm.x < 475 && 325 < proj.palm.x && 215 < proj.palm.y && proj.palm.y < 285){
				for(int j = 0; j < 8; j++){
					for(int k = 0; k < 8; k++){
						bufWin.setColor(handColor1);
						bufWin.fillRect((width*j)/8, (height*k)/8, (width*(j+1))/8, (height*(k+1))/8);
					}
				}
			}
			
		}
		
	}

	public static void main(String[] args) {
		Restroom rr = new Restroom(640, 480, "Restroom", new LeapReader());
		rr.run();
		System.exit(0);
	}
}

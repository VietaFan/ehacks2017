package gamemech;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.*;
import javax.swing.*;

import leaputils.InfoPoint;
import leaputils.IntInfoPoint;
import leaputils.IntPoint;

public class PaintingGame {
	Color[] colors;
	BufferedImage img;
	Graphics g;
	Random rand;
	ArrayList<Integer> scatterStage, scatterX, scatterY, scatterColor;
	int grabbed;
	public PaintingGame() {
		img = new BufferedImage(620, 390, BufferedImage.TYPE_INT_RGB);
		g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 620, 390);
		colors = new Color[6];
		rand = new Random();
		for (int i=0; i<5; ++i) {
			colors[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		}
		colors[5] = Color.WHITE;
		grabbed = -1;
		scatterStage = new ArrayList<Integer>();
		scatterX = new ArrayList<Integer>();
		scatterY = new ArrayList<Integer>();
		scatterColor = new ArrayList<Integer>();
	}
	public void draw(Graphics win, Component comp) {
		for (int i=0; i<6; i++) {
			win.setColor(Color.BLACK);
			win.drawOval(8+80*i, 8, 64, 64);
			win.setColor(colors[i]);
			win.fillOval(10+80*i, 10, 60, 60);
		}
		win.setColor(new Color(128, 128, 128));
		win.fillRect(500, 10, 60, 60);
		win.setColor(new Color(0, 0, 0));
		win.drawRect(500, 10, 60, 60);
		win.drawString("Reset", 510, 40);
		win.setColor(new Color(128, 128, 128));
		win.fillRect(570, 10, 60, 60);
		win.setColor(new Color(0, 0, 0));
		win.drawRect(570, 10, 60, 60);
		win.drawString("New Colors", 575, 40);
		
		win.drawImage(img, 10, 80, comp);
	}
	public boolean isGrabbing(IntInfoPoint ip) {
		double chisq = 0.0;
		for (int i=1; i<5; ++i)
			for (int j=0; j<i; ++j) 
				chisq += Math.pow(ip.fpts[i][3][1].x-ip.fpts[j][3][1].x, 2)
					  +  Math.pow(ip.fpts[i][3][1].y-ip.fpts[j][3][1].y, 2);
		return chisq < 30000;
	}
	public boolean isReleased(IntInfoPoint ip) {
		double chisq = 0.0;
		for (int i=1; i<5; ++i)
			for (int j=0; j<i; ++j) 
				chisq += Math.pow(ip.fpts[i][3][1].x-ip.fpts[j][3][1].x, 2)
					  +  Math.pow(ip.fpts[i][3][1].y-ip.fpts[j][3][1].y, 2);
		return chisq > 80000;
	}
	public IntPoint getHandPoint(IntInfoPoint pt) {
		double x = 0, y = 0;
		for (int i=0; i<5; ++i) {
			x += pt.fpts[i][3][1].x;
			y += pt.fpts[i][3][1].y;
		}
		x /= 5;
		y /= 5;
		return new IntPoint((int)x,(int)y);
	}
	public void grabPoint(IntInfoPoint pt) {
		IntPoint p = getHandPoint(pt);
		for (int i=0; i<6; i++) {
			if (Math.pow(p.x-40-80*i,2)+Math.pow(p.y-40, 2) < 1000) {
				grabbed = i;
				return;
			}		
		}
		if (p.x > 500 && p.x < 560 && p.y > 10 && p.y < 70) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 620, 390);
		}
		if (p.x > 570 && p.x < 630 && p.y > 10 && p.y < 70) {
			for (int i=0; i<5; ++i) {
				colors[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			}
		}
	}
	public void grab(int color) {
		grabbed = color;
	}
	public int getGrabbed() {
		return grabbed;
	}
	public void scatter(IntPoint p, double rad, int n) {
		scatter(p.x,p.y,rad,n);
	}
	public void scatter(int x, int y, double rad, int n) {
		g.setColor(colors[grabbed]);
		for (int i=0; i<n; ++i) {
			double r = Math.sqrt(Math.random())*rad;
			double t = Math.random()*2*Math.PI;
			if (grabbed == 5) {
				g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			}
			g.drawRect((int)(x+r*Math.cos(t)), (int)(y+r*Math.sin(t)), 1, 1);
		}
	}
	public void initScatterSequence(IntPoint p) {
		scatterStage.add(0);
		scatterX.add(p.x);
		scatterY.add(p.y);
		scatterColor.add(grabbed);
	}
	public void doScatters() {
		int k = -1;
		int temp = grabbed;
		for (int i=0; i<scatterStage.size(); ++i) {
			grabbed = scatterColor.get(i);
			scatter(scatterX.get(i), scatterY.get(i), (scatterStage.get(i)+2)*15, 60);
			scatterStage.set(i, scatterStage.get(i)+1);
			if (scatterStage.get(i) > 5) {
				k = i+1;
			}
		}
		grabbed = temp;
		if (k < 0)
			return;
		for (int i=k; i<scatterStage.size(); ++i) {
			scatterStage.set(i-k, scatterStage.get(i));
			scatterColor.set(i-k, scatterColor.get(i));
			scatterX.set(i-k, scatterX.get(i));
			scatterY.set(i-k, scatterY.get(i));
		}
		for (int i=0; i<k; ++i) {
			scatterStage.remove(scatterStage.size()-1);
			scatterColor.remove(scatterColor.size()-1);
			scatterY.remove(scatterY.size()-1);
			scatterX.remove(scatterX.size()-1);
		}
	}
}

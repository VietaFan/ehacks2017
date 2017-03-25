package gamemech;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import leaputils.*;

public class GameShape {
	final int maxValue = 1000000;
	final int nTrials = 100;
	final double initFrac = 0.7;
	public long startTime, finishTime;
	private Shape region;
	private int value;
	private static Random rand = new Random();
	GameShape(Shape shape, long finishTime) {
		this.startTime = System.currentTimeMillis();
		this.finishTime = finishTime;
		this.region = shape;
		Rectangle bounds = this.region.getBounds();
		double area = bounds.getWidth()*bounds.getHeight();
		double w = bounds.getWidth(), h=bounds.getHeight(), a=bounds.getX(), b=bounds.getY();
		int n = 0;
		for (int i=0; i<nTrials; i++) {
			if (bounds.contains(w*Math.random()+a, h*Math.random()+b)) ++n;
		}
		area *= n;
		area /= nTrials;
		value = (int)(maxValue/area);
	}	
	public int getValue() {
		return this.value;
	}
	public Shape getCurrentPos() {
		long now = System.currentTimeMillis();
		double r = (initFrac * (now-startTime)/(finishTime-startTime))*0.4+0.6;
		Rectangle rect = (Rectangle)region;
		return (Shape)(new Rectangle((int)(rect.x+rect.width*(1-r)/2), (int)(rect.y+rect.height*(1-r)/2), (int)(rect.width*r), (int)(rect.height*r)));
	}
	public boolean contains(IntInfoPoint p) {
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					if (!region.contains(p.fpts[i][j][k].x, p.fpts[i][j][k].y))
						return false;
		return true;
	}
	public boolean intersects(IntInfoPoint p) {
		int mask = 0;
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					if (region.contains(p.fpts[i][j][k].x, p.fpts[i][j][k].y)) {
						mask |= 1;
					} else {
						mask |= 2;
					}
		return mask == 3;
	}
	static TreeSet<Point> getConvexHull(TreeSet<Point> pointSet) {
		return null;
	}
	public static GameShape getNextShape(long tdur, long tfin) {
		int w = rand.nextInt(200)+100, h = rand.nextInt(200)+100;
		Shape s = new Rectangle(rand.nextInt(640-w), rand.nextInt(480-h), w, h);
		return new GameShape(s, tfin);
	}
	public double getFracDone() {
		return (1.0*(System.currentTimeMillis()-startTime))/(finishTime-startTime);
	}
	public Shape getShape() {
		return region;
	}
}

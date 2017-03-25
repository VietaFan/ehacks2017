import java.awt.*;
import java.awt.geom.*;
import java.util.TreeSet;

import leaputils.*;

public class GameShape {
	final int maxValue = 100000;
	final int nTrials = 100;
	final double initFrac = 0.7;
	private long startTime, finishTime;
	private Shape region;
	private int value;
	public GameShape(Shape shape, long finishTime) {
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
		double r = initFrac * (now-startTime)/(finishTime-startTime);
		return (Shape)region.getPathIterator(AffineTransform.getScaleInstance(r, r));
	}
	public boolean contains(InfoPoint p) {
		return false;
	}
	public boolean intersects(InfoPoint p) {
		return false;
	}
	static TreeSet<Point> getConvexHull(TreeSet<Point> pointSet) {
		return null;
	}
}

package gamemech;
import java.util.*;
import java.awt.*;
import leaputils.Pt;

public class ObstacleGame {
	ArrayList<Rectangle> obstacles;
	ArrayList<Pt> positions, velocities, projectiles;
	ArrayList<Color> colors;
	Random rand;
	public double playerx;
	public int prad;
	public final int projRad = 6;
	public final static double maxPlayerSpeed = 8.0;
	public ArrayDeque<Double> oldy;
	public ObstacleGame() {
		obstacles = new ArrayList<Rectangle>();
		velocities = new ArrayList<Pt>();
		projectiles = new ArrayList<Pt>();
		rand = new Random();
		colors = new ArrayList<Color>();
		positions = new ArrayList<Pt>();
		oldy = new ArrayDeque<Double>();
		playerx = 320;
		prad = 30;
	}
	public void addPoint(double y) {
		oldy.add(y);
		if (oldy.size() > 16)
			oldy.remove();
	}
	public double getAvg() {
		double avg = 0;
		for (Double d: oldy) avg += d;
		return avg/oldy.size();
	}
	public void addObstacle() {
		int x = rand.nextInt(540);
		int w = rand.nextInt(40)+60, h = rand.nextInt(40)+60;
		obstacles.add(new Rectangle(x,0,w,h));
		positions.add(new Pt(x,0,0));
		Pt v = new Pt(rand.nextDouble()*10-5, rand.nextDouble()*6+5, 0);
		velocities.add(v);
		colors.add(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}
	public void move() {
		for (int i=0; i<positions.size(); ++i) {
			positions.get(i).x += velocities.get(i).x;
			positions.get(i).y += velocities.get(i).y;
		}
		for (int i=0; i<projectiles.size(); ++i)
			projectiles.get(i).y -= 10;
	}
	public void draw(Graphics g) {
		for (int i=0; i<positions.size(); ++i) {
			g.setColor(colors.get(i));
			g.fillRect((int)positions.get(i).x, (int)positions.get(i).y, obstacles.get(i).width, obstacles.get(i).height);
		}
		for (int i=0; i<projectiles.size(); ++i) {
			g.setColor(Color.CYAN);
			g.fillOval((int)projectiles.get(i).x, (int)projectiles.get(i).y, projRad, projRad);
		}	
	}
	public void removeExtras() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i=0; i<positions.size(); ++i) {
			if (positions.get(i).y > 480) {
				a.add(i);
			}
		}
		for (int i=0; i<a.size(); i++) {
			obstacles.remove(a.get(i)-i);
			positions.remove(a.get(i)-i);
			velocities.remove(a.get(i)-i);
			colors.remove(a.get(i)-i);
		}
		for (int i=0; i<projectiles.size(); ++i) {
			if (projectiles.get(i).y > 480) {
				projectiles.remove(i--);
			}
		}
	}
	public boolean hit(double x, double y, int rad) {
		boolean retVal = false;
		for (int i=0; i<positions.size(); ++i) {
			if (x > positions.get(i).x - rad && y > positions.get(i).y - rad && x < rad + positions.get(i).x+obstacles.get(i).width && y < rad + positions.get(i).y+obstacles.get(i).height){
				positions.get(i).y = 9000;
				retVal = true;
			}
			for (int j=0; j<projectiles.size(); ++j) {
				if (projectiles.get(j).x > positions.get(i).x - projRad && projectiles.get(j).y > positions.get(i).y - projRad && projectiles.get(j).x < projRad + positions.get(i).x+obstacles.get(i).width && projectiles.get(j).y < projRad + positions.get(i).y+obstacles.get(i).height){
					positions.get(i).y = 9000;
					projectiles.get(j).y = 9000;
					prad += 8;
					retVal = true;
				}
			}
		}
		return retVal;
	}
	public void addProjectile() {
		projectiles.add(new Pt(playerx, 360, 0));
	}
}

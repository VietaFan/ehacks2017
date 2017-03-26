package gamemech;

public class PointCounter {
	int nPoints;
	public PointCounter() {
		nPoints = 0;
	}
	public void addPoints(int n) {
		nPoints += n;
	}
	public int getTotal(int n) {
		return nPoints;
	}
}

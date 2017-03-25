package graphics;
public class GameState {
	final int maxDelay = 10000, delayConst = 100000000;
	private int points, lives;
	private long lastRestart;
	public GameState(int nlives) {
		lives = nlives;
		lastRestart = System.currentTimeMillis();
	}
	public boolean die() {
		--lives;
		if (lives == 0)
			return true;
		lastRestart = System.currentTimeMillis();
		return false;
	}
	public void score(int numPts) {
		points += numPts;
	}
	public int getScore() {
		return points;
	}
	public int getLives() {
		return lives;
	}
	public int getShapeDelay() {
		return (int)Math.min(maxDelay, 1.0*delayConst/lastRestart);
	}
}

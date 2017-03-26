package gamemech;
public class GameState {
	final int maxDelay = 5000, delayConst = 50000000;
	public final static int NUM_STATES = 11;
	final int[] defaultLives = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
	private int points, lives, gamemode;
	public long lastRestart;
	public boolean restarted;
	public GameState(int mode, int nlives) {		
		gamemode = mode;
		lives = nlives;
		lastRestart = System.currentTimeMillis();
		restarted = true;
	}
	public int getMode() {
		return gamemode;
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
		return (int)Math.min(maxDelay, 1.0*delayConst/(System.currentTimeMillis()-lastRestart+1));
	}
	public long getLastRestart() {
		return lastRestart;
	}
	public void startNew(int mode) {
		gamemode = mode;
		lives = defaultLives[mode];
		points = 0;
		lastRestart = System.currentTimeMillis();
		restarted = true;
	}
	public boolean hasRestarted() {
		boolean temp = restarted;
		restarted = false;
		return temp;
	}
}

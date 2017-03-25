package leaputils;
import com.leapmotion.leap.*;

public class LeapReader {
	private Controller cont;
	public InfoPoint poll() {
		return new InfoPoint(cont.frame().hand(0));
	}
	public LeapReader() {
		cont = new Controller();
	}
}

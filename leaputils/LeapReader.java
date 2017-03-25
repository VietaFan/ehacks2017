package leaputils;
import com.leapmotion.leap.*;
import java.util.*;

public class LeapReader {
	private Controller cont;
	public ArrayList<InfoPoint> pollAll() {
		ArrayList<InfoPoint> A = new ArrayList<InfoPoint>();
		for (Hand h: cont.frame().hands()) {
			A.add(new InfoPoint(h));
		}
		return A;
	}
	public InfoPoint poll() {
		return new InfoPoint(cont.frame().hands().get(0));
	}
	public LeapReader() {
		cont = new Controller();
	}
}

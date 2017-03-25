package leaputils;
import com.leapmotion.leap.*;

public class InfoPoint {
	public Point fpts[][][];
	public Point wrist, elbow, armdir;
	public InfoPoint(Hand hand) {
		fpts = new Point[5][4][2];
		int i=0,j,k;
		Vector vec;
		for (Finger finger: hand.fingers()) {
			j = 0;
			for (Bone.Type boneType : Bone.Type.values()) {
				Bone bone = finger.bone(boneType);
				vec = bone.prevJoint();
				fpts[i][j][0] = new Point(vec.getX(), vec.getY(), vec.getZ());
				vec = bone.nextJoint();
				fpts[i][j][1] = new Point(vec.getX(), vec.getY(), vec.getZ());
				++j;
			}
			++i;
		}	
		vec = hand.arm().wristPosition();
		wrist = new Point(vec.getX(), vec.getY(), vec.getZ());
		vec = hand.arm().elbowPosition();
		elbow = new Point(vec.getX(), vec.getY(), vec.getZ());
		vec = hand.arm().direction();
		armdir = new Point(vec.getX(), vec.getY(), vec.getZ());
	}
}

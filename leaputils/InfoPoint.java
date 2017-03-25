package leaputils;
import com.leapmotion.leap.*;

public class InfoPoint {
	public Vector fingerPts[][][];
	public InfoPoint(Hand hand) {
		fingerPts = new Vector[5][4][2];
		int i=0,j,k;
		for (Finger finger: hand.fingers()) {
			j = 0;
			for (Bone.Type boneType : Bone.Type.values()) {
				Bone bone = finger.bone(boneType);
				fingerPts[i][j][0] = bone.prevJoint();
				fingerPts[i][j][1] = bone.nextJoint();
				++j;
			}
			++i;
		}
	}
	
}

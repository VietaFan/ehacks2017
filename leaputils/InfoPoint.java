package leaputils;
import com.leapmotion.leap.*;

public class InfoPoint {
	public Pt fpts[][][];
	public Pt wrist, elbow, armdir, palm;
	public InfoPoint(InfoPoint other) {
		fpts = new Pt[5][4][2];
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					fpts[i][j][k] = new Pt(other.fpts[i][j][k]);
		wrist = new Pt(other.wrist);
		elbow = new Pt(other.elbow);
		armdir = new Pt(other.armdir);
		palm = new Pt(other.palm);
	}
	public InfoPoint(Hand hand) {
		fpts = new Pt[5][4][2];
		for (int i=0; i<5; i++)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					fpts[i][j][k] = new Pt(0,0,0);
		int i=0,j,k;
		Vector vec;
		for (Finger finger: hand.fingers()) {
			j = 0;
			for (Bone.Type boneType : Bone.Type.values()) {
				Bone bone = finger.bone(boneType);
				vec = bone.prevJoint();
				fpts[i][j][0] = new Pt(vec.getX(), vec.getY(), vec.getZ());
				vec = bone.nextJoint();
				fpts[i][j][1] = new Pt(vec.getX(), vec.getY(), vec.getZ());
				++j;
			}
			++i;
		}	
		vec = hand.arm().wristPosition();
		wrist = new Pt(vec.getX(), vec.getY(), vec.getZ());
		vec = hand.arm().elbowPosition();
		elbow = new Pt(vec.getX(), vec.getY(), vec.getZ());
		vec = hand.arm().direction();
		armdir = new Pt(vec.getX(), vec.getY(), vec.getZ());
		vec = hand.palmPosition();
		palm = new Pt(vec.getX(), vec.getY(), vec.getZ());
	}
	public InfoPoint() {
		fpts = new Pt[5][4][2];
		for (int i=0; i<5; i++)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					fpts[i][j][k] = new Pt(0,0,0);
	}
	public void scale(double r, Pt zeroImg) {
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k) {
					fpts[i][j][k].scale(r, zeroImg);
				}
		wrist.scale(r, zeroImg);
		elbow.scale(r, zeroImg);
		palm.scale(r, zeroImg);
	}
	public InfoPoint project(Pt origin, Pt xunit, Pt yunit) {
		InfoPoint p = new InfoPoint(this);
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k) {
					p.fpts[i][j][k] = p.fpts[i][j][k].project(origin, xunit, yunit);
				}
		p.wrist = wrist.project(origin, xunit, yunit);
		p.elbow = elbow.project(origin, xunit, yunit);
		p.palm = palm.project(origin, xunit, yunit);
		return p;
	}
	public IntInfoPoint toIntInfoPoint() {
		return new IntInfoPoint(this);
	}
	public IntInfoPoint intProject(Pt origin, Pt xunit, Pt yunit) {
		return project(origin, xunit, yunit).toIntInfoPoint();
	}
	
}

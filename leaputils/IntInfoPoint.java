package leaputils;

public class IntInfoPoint {
	public IntPoint fpts[][][];
	public IntPoint wrist, elbow, armdir, palm;
	public IntInfoPoint(InfoPoint ip) {
		fpts = new IntPoint[5][4][2];
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					fpts[i][j][k] = new IntPoint((int)ip.fpts[i][j][k].x, (int)ip.fpts[i][j][k].y);
		wrist = new IntPoint((int)ip.wrist.x, (int)ip.wrist.y);
		elbow = new IntPoint((int)ip.elbow.x, (int)ip.elbow.y);
		armdir = new IntPoint((int)ip.armdir.x, (int)ip.armdir.y);
		palm = new IntPoint((int)ip.palm.x, (int)ip.palm.y);
	}
	public void verticalReflect(int h) {
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					fpts[i][j][k].verticalReflect(h);
		wrist.verticalReflect(h);
		elbow.verticalReflect(h);
		palm.verticalReflect(h);
	}
}

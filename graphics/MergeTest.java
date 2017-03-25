package graphics;

import leaputils.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MergeTest extends GraphicsBase {

	private HandGraphics hg;
	private Holes holes;
	public MergeTest(int width, int height, String titleStr) {
		super(width, height, titleStr);
		holes = new Holes(width, height, titleStr);
		hg = new HandGraphics(width, height, titleStr, new LeapReader());
		buf = new BufferedImage(hg.buf.getWidth(), hg.buf.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}

	

	@Override
	public void update() {
		bufWin.drawImage(hg.buf, 0, 0, null);
		bufWin.drawImage(holes.buf, 0, 0, null);
	}
	
	public static void main(String[] args) {
		MergeTest mt = new MergeTest(640, 480, "Merge Test");
		mt.run();
		System.exit(0);
	}
}

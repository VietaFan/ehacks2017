package graphics;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public abstract class GraphicsBase extends JFrame {
	private BufferedImage buf;
	protected Graphics win, bufWin;
	private Insets insets;
	protected int width, height, fps, delta;
	protected long nextUpdate;
	protected Color bkgColor;
	
	public GraphicsBase(int width, int height, String titleStr) {
		this.width = width;
		this.height = height;
		setTitle(titleStr);
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		win = getGraphics();
		buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bufWin = buf.getGraphics();
		insets = getInsets(); 
		setSize(insets.left + width + insets.right, 
		                insets.top + height + insets.bottom); 
		fps = 30;
		bkgColor = new Color(255, 255, 255);
		delta = 1000/fps;
		nextUpdate = System.currentTimeMillis();
	}
	public void refreshInit() {
		bufWin.setColor(bkgColor);
		bufWin.fillRect(0, 0, width, height);
	}
	public abstract void update();
	public void run() {
		while (true) {
			if (System.currentTimeMillis() > nextUpdate) {
				refreshInit();
				update();
				win.drawImage(buf, insets.left, insets.top, this);
				nextUpdate = System.currentTimeMillis()+delta;
			}
		}
	}
	

}

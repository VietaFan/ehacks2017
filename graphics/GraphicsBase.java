package graphics;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public abstract class GraphicsBase extends JFrame {
	BufferedImage buf;
	Graphics win, bufWin;
	Insets insets;
	int width, height;
	int fps, delta;
	long nextUpdate;
	
	public GraphicsBase(int width, int height, String titleStr) {
		this.width = width;
		this.height = height;
		setTitle(titleStr);
		setSize(width, height);
		setResizable(false);
		setVisible(true);
		win = getGraphics();
		buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bufWin = buf.getGraphics();
		insets = getInsets(); 
		setSize(insets.left + width + insets.right, 
		                insets.top + height + insets.bottom); 
		fps = 30;
		delta = 1000/fps;
		nextUpdate = System.currentTimeMillis();
	}
	
	public void run() {
		while (true) {
			if (System.currentTimeMillis() > nextUpdate) {
				
				win.drawImage(buf, insets.left, insets.top, this);
				nextUpdate = System.currentTimeMillis()+delta;
			}
		}
	}
	

}

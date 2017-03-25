import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class GraphicsTest extends JFrame {
	BufferedImage buf;
	Graphics win, bufWin;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsTest x = new GraphicsTest();
		x.run();
		System.exit(0);
	}
	
	public void run() {
		setTitle("Hello, World!");
		setSize(640, 480);
		setResizable(false);
		setVisible(true);
		win = getGraphics();
		buf = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		bufWin = buf.getGraphics();
		int x = 0;
		while (true) {
			x += 5; 
			x %= 640;
			bufWin.setColor(new Color(255, 255, 255));
			bufWin.fillRect(0, 0, 640, 480);
			bufWin.setColor(new Color(0, 255, 0));
			bufWin.drawRect(x, 200, 40, 40);
			win.drawImage(buf, 0, 0, this);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

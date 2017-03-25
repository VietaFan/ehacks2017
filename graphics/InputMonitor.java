package graphics;

import java.awt.*;
import java.awt.event.*;

public class InputMonitor implements KeyListener, MouseListener {
	public boolean pressed[] = new boolean[256];
	public int mousex, mousey;
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mousex = arg0.getX();
		mousey = arg0.getY();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mousex = arg0.getX();
		mousey = arg0.getY();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mousex = arg0.getX();
		mousey = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mousex = arg0.getX();
		mousey = arg0.getY();
	}
	
}

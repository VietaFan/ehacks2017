package graphics;

import java.awt.*;
import java.awt.event.*;

import gamemech.GameState;

public class InputMonitor implements KeyListener {
	private GameState state;
	
	public InputMonitor(HandGraphics comp) {
		this.state = comp.state;
		comp.addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (state.getMode()) {
		case 8:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				state.startNew(6);
				break;
			}
			break;
		case 3:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				state.startNew(5);
				break;
			case KeyEvent.VK_DOWN:
				state.startNew(6);
				break;
			}
			break;
		case 5:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				state.startNew(4);
				break;
			case KeyEvent.VK_DOWN:
				state.startNew(3);
				break;
			}
			break;
		case 4:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				state.startNew(5);
				break;
			}
			break;
		case 1:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				state.startNew(6);
				break;
			}
			break;
		case 6:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				state.startNew(8);
				break;
			case KeyEvent.VK_RIGHT:
				state.startNew(1);
				break;
			case KeyEvent.VK_DOWN:
				state.startNew(7);
				break;
			case KeyEvent.VK_P:
				if (arg0.isControlDown()) {
					state.startNew(0);
				}
				break;
			}
			break;
		case 7:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				state.startNew(6);
				break;
			case KeyEvent.VK_LEFT:
				state.startNew(9);
				break;
			case KeyEvent.VK_RIGHT:
				state.startNew(10);
				break;
			}
			break;
		case 9:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				state.startNew(7);
				break;
			}
			break;
		case 10:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				state.startNew(7);
				break;
			}
			break;
		/*default:
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_R:
				if (arg0.isShiftDown()) 
					state.startNew(state.getMode());
				break;				
			case KeyEvent.VK_RIGHT:
				state.startNew((state.getMode()+1)%GameState.NUM_STATES);
				break;
			case KeyEvent.VK_LEFT:
				state.startNew((state.getMode()+GameState.NUM_STATES-1)%GameState.NUM_STATES);
				break;
			}*/
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

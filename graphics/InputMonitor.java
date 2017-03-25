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
		default:
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
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

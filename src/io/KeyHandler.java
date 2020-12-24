package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import infrastructure.*;

public class KeyHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		Main.surface.cube.addRotationYAxis(7);
		Main.surface.cube1.addRotationYAxis(7);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}

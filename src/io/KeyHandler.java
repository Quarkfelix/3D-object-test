package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import infrastructure.*;

public class KeyHandler implements KeyListener{
	public static boolean schalter = true;
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(KeyHandler.schalter == true) {
			KeyHandler.schalter = false;
			Main.surface.cube.rotate(7,0);
			Main.surface.cube1.rotate(7,0);
			System.out.println("gedreht an der y achse");
		} else {
			KeyHandler.schalter = true;
			System.out.println("gedreht an der X achse");
//			Main.surface.cube.rotate(0,7);
//			Main.surface.cube1.rotate(0,7);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}

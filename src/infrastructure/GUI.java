package infrastructure;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import io.*;

public class GUI {
	public JFrame jf;
	public Draw draw;

// ======================================== CONSTRUCTOR ========================================	
	
	public GUI() {
		createJFrame();
		setupJFrame();
	}

// ======================================== METHODS ============================================
	private void createJFrame() {
		// give JFrame monitor on which the JFrame should be displayer
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		GraphicsDevice gd = gs[0];

		jf = new JFrame(gd.getDefaultConfiguration());
		draw = new Draw();
	}

	private void setupJFrame() {
		jf.setSize(1200, 800);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(draw);
		jf.addKeyListener(new KeyHandler());
		jf.addMouseListener(new MouseHandler());
		jf.addMouseMotionListener(new MouseMotionHandler());
		jf.addMouseWheelListener(new MouseWheelHandler());
		jf.setVisible(true);
	}
}

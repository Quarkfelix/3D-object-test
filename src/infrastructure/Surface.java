package infrastructure;

import java.awt.Color;
import java.awt.Graphics2D;

public class Surface {
	public Cube cube;
	public Cube cube1;
	
// ======================================== CONSTRUCTOR ========================================
	public Surface() {
		cube = new Cube(270, 400, 200, 100, 100, 100, false);
		cube1 = new Cube(870, 400, 400, 100, 100, 100, true);
	}
// ======================================== RUN-METHOD =========================================	
	
// ======================================== METHODS ============================================
	
// ======================================== GET/SET METHODS ====================================
	
// ======================================== PAINT-METHODS ======================================
	public void paint(Graphics2D g) {
		cube.paint(g);
		cube1.paint(g);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Press enter for 7 degree spin. You can also hold enter", 470, 100);
		g.drawString("Front View", 290, 200);
		g.drawString("Top View", 890, 200);
		g.drawLine(600, 0, 600, 2000);
	}
}

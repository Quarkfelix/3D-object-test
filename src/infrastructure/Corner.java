package infrastructure;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Corner {
	public int x,y,z;
	public int lastX,lastY,lastZ;
	public double angleToMiddleY = 0; //grad
	public double angleToMiddleX = 0; //grad
	public double angleToMiddleZ = 0; //grad
	public double lengthtomiddleXrot;
	public double lengthtomiddleYrot;
	public double lengthtomiddleZrot;
	public ArrayList<Corner> connCorners = new ArrayList<>();
	
// ======================================== CONSTRUCTOR ========================================
	public Corner(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	
// ======================================== METHODS ============================================

// ======================================== GET/SET METHODS ====================================
	public void setX(int x) {
		lastX = this.x;
		this.x = x;
	}
	
	public void setY(int y) {
		lastY = this.y;
		this.y = y;
	}
	
	public void setZ(int z) {
		lastZ = this.z;
		this.z = z;
	}
// ======================================== PAINT-METHODS ======================================
	public void paint(Graphics2D g, boolean topview) {
		g.setColor(Color.CYAN);
		if(topview) {
			g.fillOval(x-3, z-3, 6, 6);
		} else {
			g.fillOval(x-3, y-3, 6, 6);
		}
		
	}
}





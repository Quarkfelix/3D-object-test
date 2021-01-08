package infrastructure;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Cube {
	private int x, y, z;
	private int lengthX, lengthY, lengthZ;
	private double middleX, middleY, middleZ;
	
	private boolean topview = false;

	private ArrayList<Corner> corners = new ArrayList<>();

// ======================================== CONSTRUCTOR ========================================
	public Cube(int x, int y, int z, int lengthX, int lengthY, int lengthZ, boolean topview) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.lengthZ = lengthZ;
		this.topview = topview;
		createCorners();

		double vectoraddX = 0;
		double vectoraddY = 0;
		double vectoraddZ = 0;
		for (Corner corner : corners) {
			vectoraddX += corner.x;
			vectoraddY += corner.y;
			vectoraddZ += corner.z;
		}

		middleX = (1.0 / corners.size()) * vectoraddX;
		middleY = (1.0 / corners.size()) * vectoraddY;
		middleZ = (1.0 / corners.size()) * vectoraddZ;

		
		for (Corner corner : corners) {
			corner.lengthtomiddleYrot = Math.sqrt(Math.pow(Math.abs(corner.x-middleX), 2) + Math.pow(Math.abs(corner.z-middleZ), 2));
			corner.lengthtomiddleXrot = Math.sqrt(Math.pow(Math.abs(corner.z-middleZ), 2) + Math.pow(Math.abs(corner.y-middleY), 2));
			
			//for y axis
			corner.angleToMiddleY = Math.atan(Math.abs(corner.z-middleZ)/Math.abs(corner.x-middleX));
			corner.angleToMiddleY = Math.toDegrees(corner.angleToMiddleY);
			if(corner.x < middleX) {
				if(corner.z>middleZ) {
					//180-winkel
					System.out.println("hinten links");
					corner.angleToMiddleY = 180-corner.angleToMiddleY;
				} else {
					System.out.println("vorne links");
					corner.angleToMiddleY = 180+corner.angleToMiddleY;
				}
			} else {
				if(corner.z>middleZ) {
					System.out.println("hinten rechts");
					//passt schon
				} else {
					System.out.println("vorne rechts");
					corner.angleToMiddleY = 360-corner.angleToMiddleY;
				}
			}
			
			//for x axis
			corner.angleToMiddleX = Math.atan(Math.abs(corner.y-middleY)/Math.abs(corner.z-middleZ));
			corner.angleToMiddleX = Math.toDegrees(corner.angleToMiddleX);
			if(corner.z < middleZ) {
				if(corner.y>middleY) {
					//180-winkel
					System.out.println("hinten links");
					corner.angleToMiddleX = 180-corner.angleToMiddleX;
				} else {
					System.out.println("vorne links");
					corner.angleToMiddleX = 180+corner.angleToMiddleX;
				}
			} else {
				if(corner.y>middleY) {
					System.out.println("hinten rechts");
					//passt schon
				} else {
					System.out.println("vorne rechts");
					corner.angleToMiddleX = 360-corner.angleToMiddleX;
				}
			}
		}

	}

	private void createCorners() {
		corners.add(new Corner(x, y, z)); // 0
		corners.add(new Corner(x + lengthX, y, z)); // 1
		corners.add(new Corner(x, y + lengthY, z)); // 2
		corners.add(new Corner(x + lengthX, y + lengthY, z)); // 3
		corners.add(new Corner(x, y, z + lengthZ)); // 4
		corners.add(new Corner(x + lengthX, y, z + lengthZ)); // 5
		corners.add(new Corner(x, y + lengthY, z + lengthZ)); // 6
		corners.add(new Corner(x + lengthX, y + lengthY, z + lengthZ)); // 7
	}

// ======================================== RUN-METHOD =========================================	

// ======================================== METHODS ============================================
	public void rotate(double rotationToAddX, double rotationToAddY) {
		double xfactor;
		double yfactor;
		double zfactor;
		double tempX = 0, tempY = 0, tempZ = 0;
		
		for (Corner c : corners) {
			//rotate on X axis
			c.angleToMiddleX = c.angleToMiddleX+rotationToAddX;
			if(c.angleToMiddleX >= 360) {
				c.angleToMiddleX = c.angleToMiddleX-360;
			}
			zfactor = Math.cos(Math.toRadians(c.angleToMiddleX));
			yfactor = Math.sin(Math.toRadians(c.angleToMiddleX));
			
			tempZ = middleZ + zfactor*c.lengthtomiddleYrot;
			tempY = middleY + yfactor*c.lengthtomiddleYrot;
			
			//rotate on y axis
			c.angleToMiddleY = c.angleToMiddleY+rotationToAddY;
			if(c.angleToMiddleY >= 360) {
				c.angleToMiddleY = c.angleToMiddleY-360;
			}
			xfactor = Math.cos(Math.toRadians(c.angleToMiddleY));
			zfactor = Math.sin(Math.toRadians(c.angleToMiddleY));
			
			tempX = middleX + xfactor*c.lengthtomiddleXrot;
			tempZ = (tempZ + middleZ + zfactor*c.lengthtomiddleXrot)/2;
//			c.setZ((int) (middleZ + zfactor*c.lengthtomiddle));
//			c.setY((int) (middleY + yfactor*c.lengthtomiddle));
			
			c.setX((int)(tempX));
			c.setY((int)(tempY));
			c.setZ((int)(tempZ));
			
		}	
		
		System.out.println(corners.get(0).angleToMiddleX);
		System.out.println(corners.get(0).angleToMiddleY);
	}

// ======================================== GET/SET METHODS ====================================

// ======================================== PAINT-METHODS ======================================
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		for (Corner corner : corners) {
			corner.paint(g, topview);
//			g.setColor(Color.red);
//			g.drawLine((int)middleX, (int)middleY, corner.x, corner.y);
		}
		
		drawConnection(g, corners.get(0), corners.get(1));
		drawConnection(g, corners.get(0), corners.get(2));
		drawConnection(g, corners.get(0), corners.get(4));
		drawConnection(g, corners.get(2), corners.get(3));
		drawConnection(g, corners.get(2), corners.get(6));
		drawConnection(g, corners.get(3), corners.get(1));
		drawConnection(g, corners.get(3), corners.get(7));
		drawConnection(g, corners.get(5), corners.get(4));
		drawConnection(g, corners.get(5), corners.get(1));
		drawConnection(g, corners.get(5), corners.get(7));
		drawConnection(g, corners.get(7), corners.get(6));
		drawConnection(g, corners.get(6), corners.get(4));
	}
	
	public void drawConnection(Graphics2D g, Corner c1, Corner c2) {
		g.setColor(Color.RED);
		if(this.topview) {
			g.drawLine(c1.x, c1.z, c2.x, c2.z);
		} else {
			g.drawLine(c1.x, c1.y, c2.x, c2.y);
		}
	}
}

package graphical_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Star {
	
	private double x;
	
	private double y;
	
	private int diameter;
	
	private double velocity = .05; // pixels per second
	
	public Star(double x, double y, int diameter) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // recovers Graphics2D
		
		g2.setColor(Color.white);
		Ellipse2D.Double star = new Ellipse2D.Double(x, y, diameter, diameter);
		g2.fill(star);
	}
	
	public void update(long diff) {
		x-=velocity*diff;
	}
	
	public void setX(int x) {this.x = x;}
	
	public double getX() {return x;}
}

package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Asteroid {
	
	private double x;
	
	private double y;
	
	private int width;
	
	private int height;
	
	private Rectangle2D.Double rect;
	
	private BufferedImage asteroid;
	
	private double velocity = Math.random()*.3 + .15; // pixels per second
	
	public Asteroid(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle2D.Double(x, y, width / 1.5, height / 1.5);
		try {
			asteroid = ImageIO.read(new File("images/asteroid.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		// recovers Graphics2D
		Graphics2D g2 = (Graphics2D) g; 
		
		// scales the instance of the image and draws it 
		g2.drawImage(asteroid.getScaledInstance(width, height, Image.SCALE_DEFAULT), (int) x - width / 6, (int) y - height / 6, null);	
	}
	
	public void update(long diff) {
		x -= velocity * diff;
		rect.x=x;
	}
	
	public void setX(int x) {this.x = x; rect.x = x;}
	
	public double getX() {return x;}
	
	public void setY(double y) {this.y = y; rect.y = y;}
	
	public double getY() {return y;}
	
	public void setWidth(int width) {this.width = width;}

	public void setHeight(int height) {this.height = height;}
	
	public int getWidth() {return width;}
	
	public int getHeight() {return height;}
	
	public Rectangle2D.Double getHitBox() {return rect;}
}

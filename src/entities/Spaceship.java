package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import main.Main;

public class Spaceship extends JComponent{
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage spaceship;
	
	private int x;
	
	private int y;
	
	private int dx;
	
	private int dy;
	
	private int width = 198;
	
	private int height = 104;
	
	private Rectangle2D.Double rect;
	
	public Spaceship(int x, int y) {
		this.x = x;
		this.y = y;
		rect = new Rectangle2D.Double(x, y, width / 2.1, height / 2.3);
		try {
			spaceship = ImageIO.read(new File("images/spaceship.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		// recovers Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		
		// draws the image
		g2.drawImage(spaceship.getScaledInstance(width, height, Image.SCALE_DEFAULT), x - width / 3 + 5, y - height / 3 + 5, null);
		g2.draw(rect);
	}
	
	public void update() {
		if(x >= Main.WINSIZE - width) {
			x = Main.WINSIZE - width;
			x += dx;
			rect.x = x;
		} else {
			x += dx;
			rect.x = x;
		}
		if(y >= Main.WINSIZE - height - 36) {
			y = Main.WINSIZE - height - 36;
			y += dy;
		} else {
			y += dy;
		}
		if(x <= 0) {
			x = 0;
			x += dx;
			rect.x = x;
		} else {
			rect.x = x;
			x += dx;
		}
		if(y <= 0) {
			y = 0;
			y += dy;
			rect.y = y;
		} else {
			y += dy;
			rect.y = y;
		}
	}
	
	public void setDx(int dx) {this.dx = dx;}
	
	public void setDy(int dy) {this.dy = dy;}
	
	public void setX(int x) {this.x = x; rect.x = x;}
	
	public void setY(int y) {this.y = y; rect.y = y;}
	
	public int getDx() {return dx;}
	
	public int getDy() {return dy;}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public Rectangle2D.Double getHitBox() {return rect;}
}
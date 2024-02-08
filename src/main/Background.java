package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background {
	
	private int x;
	
	private int y;
	
	private BufferedImage portal;
	
	private int width = 320;
	
	private int height = 320;
	
	public Background(int x, int y) {
		this.x = x;
		this.y = y;
		try {
			portal = ImageIO.read(new File("images/portal.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	// recovers Graphics2D
		
		// draws the portal
		g2.drawImage(portal.getScaledInstance(width, height, Image.SCALE_DEFAULT), x + ((Main.WINSIZE / 2) - (width / 2)) - 10, y + ((Main.WINSIZE / 2) - (height / 2)) - 15, null);
	}
}

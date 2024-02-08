package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Overlay {
	
	private int x;
	
	private int y;
	
	private int width = 64;
	
	private int height = 64;
	
	public Overlay(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // recovers Graphics2D
		
		// creates the border
		int offset = 32;
		g2.setColor(Color.orange);
		g2.fillRect(x, y, Main.WINSIZE, offset);
		g2.fillRect(x + (Main.WINSIZE - offset), y + offset, offset, Main.WINSIZE - offset);
		g2.fillRect(x, y + offset, offset, Main.WINSIZE - offset);
		g2.fillRect(x + offset, y + (Main.WINSIZE - offset * 2) , Main.WINSIZE - offset, offset * 2);
		
		// curves the corners of the border
		try {
			BufferedImage top_left = ImageIO.read(new File("images/top_left.png"));
			BufferedImage top_right = ImageIO.read(new File("images/top_right.png"));
			BufferedImage bottom_left = ImageIO.read(new File("images/bottom_left.png"));
			BufferedImage bottom_right = ImageIO.read(new File("images/bottom_right.png"));
			g2.drawImage(top_left.getScaledInstance(width, height, Image.SCALE_DEFAULT), offset, offset, null);
			g2.drawImage(top_right.getScaledInstance(width, height, Image.SCALE_DEFAULT), Main.WINSIZE - offset * 3, offset, null);
			g2.drawImage(bottom_left.getScaledInstance(width, height, Image.SCALE_DEFAULT), offset, Main.WINSIZE - offset * 4, null);
			g2.drawImage(bottom_right.getScaledInstance(width, height, Image.SCALE_DEFAULT), Main.WINSIZE - offset * 3, Main.WINSIZE - offset * 4, null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

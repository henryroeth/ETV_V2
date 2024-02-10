package graphical_components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import file_handling.ScoreHandler;
import main.Component;
import main.Main;

public class Overlay {
	
	private int x;
	
	private int y;
	
	private int width = 64;
	
	private int height = 64;
	
	private boolean pausedState;
	
	private boolean startState;
	
	private boolean endState;
	
	public Overlay(int x, int y) {
		this.x = x;
		this.y = y;
		pausedState = false;
		startState = true;
		endState = false;
	}
	
	public void draw(Graphics g) {
		// recovers Graphics2D
		Graphics2D g2 = (Graphics2D) g; 
		
		// colors
		Color pausedBC = new Color(150, 150, 150, 150);
		Color blue = Color.blue;
		Color white = Color.white;
		Color black = Color.black;
		
		// fonts
		Font monospacedBold100 = new Font("Monospaced", Font.BOLD, 100);
		Font monospacedBold85 = new Font("Monospaced", Font.BOLD, 85);
		Font monospacedBold65 = new Font("Monospaced", Font.BOLD, 65);
		Font monospacedBold45 = new Font("Monospaced", Font.BOLD, 45);
		Font monospacedBold20 = new Font("Monospaced", Font.BOLD, 20);
		
		// strings
		String paused = "GAME PAUSED";
		String title = "Escape The Void!";
		String control1 = "Start: \"Enter\"";
		String control2 = "Movement: \"Arrow Keys\"";
		String gameOver = "GAME OVER!";
		String restart = "\"ENTER\" TO RESTART";
		String escape = "\"ESCAPE\" TO QUIT";
		
		// overlay when on the start screen
		if(startState) {
			g2.setColor(blue);
			g2.setFont(monospacedBold85);
			g2.drawString(title, x + (Main.WINSIZE + Main.WINSIZE / 2) - (title.length() * 88), y + Main.WINSIZE / 5);
			g2.setFont(monospacedBold65);
			g2.drawString(control1, x + (Main.WINSIZE + Main.WINSIZE / 2) - (control1.length() * 90), y + Main.WINSIZE - 250);
			g2.drawString(control2, x + (Main.WINSIZE + Main.WINSIZE / 2) - (control2.length() * 65), y + Main.WINSIZE - 150);
		}
		
		// overlay when paused
		if(pausedState) {
			g2.setFont(monospacedBold100);
			g2.drawString(paused, x + (Main.WINSIZE + Main.WINSIZE / 2) - (paused.length() * 121), y + Main.WINSIZE / 2);
			g2.setColor(pausedBC);
			g2.fillRect(x, y, Main.WINSIZE, Main.WINSIZE);
		}
		
		// overlay when on the end screen
		if(endState) {
			g2.setColor(blue);
			g2.fillRect(x, y, Main.WINSIZE, Main.WINSIZE);
			int timeAlive = Component.timeAlive;
			if(Component.timeAlive > ScoreHandler.readNumberFromFile()) ScoreHandler.saveNumberToFile(timeAlive); // saves high score if possible
			int seconds = timeAlive / 1000;
			int minutes = seconds / 60;
			int highScore = ScoreHandler.readNumberFromFile();
			int seconds1 = highScore / 1000;
			int minutes1 = seconds1 / 60;
			g2.setColor(white);
			g2.setFont(monospacedBold85);
			g2.drawString(gameOver, x + Main.WINSIZE / 4, y + Main.WINSIZE / 5);
			g2.setFont(monospacedBold45);
			g2.drawString(restart, x + Main.WINSIZE / 4, y + Main.WINSIZE / 2);
			g2.drawString(escape, x + Main.WINSIZE / 4 + 20, y + Main.WINSIZE / 2 + 64);
			g2.setFont(monospacedBold45);
			g2.drawString("Time Alive: " + minutes + "m:" + seconds % 60 + "s", Main.WINSIZE / 4 + 15, Main.WINSIZE - Main.WINSIZE / 3);
			g2.drawString("High Score: " + minutes1 + "m:" + seconds1 % 60 + "s", Main.WINSIZE / 4 + 15, Main.WINSIZE - Main.WINSIZE / 4);
		}
		
		// creates the border of the overlay
		int offset = 32;
		g2.setColor(Color.orange);
		g2.fillRect(x, y, Main.WINSIZE, offset);
		g2.fillRect(x + (Main.WINSIZE - offset), y + offset, offset, Main.WINSIZE - offset);
		g2.fillRect(x, y + offset, offset, Main.WINSIZE - offset);
		g2.fillRect(x + offset, y + (Main.WINSIZE - offset * 2) , Main.WINSIZE - offset, offset * 2);
		int timeAlive = Component.timeAlive;
		int seconds = timeAlive / 1000;
		int minutes = seconds / 60;
		g2.setColor(black);
		g2.setFont(monospacedBold20);
		g2.drawString("Time Alive: " + minutes + "m:" + seconds % 60 + "s", x + Main.WINSIZE - 225, y + 25);
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
	
	public void setPausedState(boolean pausedState) {this.pausedState = pausedState;}
	
	public boolean pausedState() {return this.pausedState;}
	
	public void setStartState(boolean startState) {this.startState = startState;}
	
	public boolean startState() {return this.startState;}
	
	public void setEndState(boolean endState) {this.endState = endState;}
	
	public boolean endState() {return this.endState;}
}

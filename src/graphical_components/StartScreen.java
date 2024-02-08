package graphical_components;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class StartScreen {
	
	private int x;
	
	private int y;
	
	private int screenState;
	
	public StartScreen(int x, int y) {
		this.x = x;
		this.y = y;
		screenState = 0;
	}
	
	public void draw(Graphics g) {
		// recovers Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		
		// draws the start screen with start options
		switch(screenState) {
			case 0:
				// not implemented yet
				break;
			case 1:
				// not implemented yet
				break;	
		}
	}
	
	public void setScreenState(int screenState) {this.screenState = screenState;}
	
	public void setX(int x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	
	public int getScreenState() {return screenState;}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
}

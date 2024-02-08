package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

import entities.Asteroid;
import entities.Spaceship;
import entities.Star;

public class Component extends JComponent implements KeyListener, ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private Spaceship spaceship;
	
	private final int SPACESHIP_SPEED = 2;
	
	private final int NUM_ASTEROIDS = 8;
	
	private final int ASTEROID_WIDTH = 123;
	
	private final int ASTEROID_HEIGHT = 113;
	
	private ArrayList<Star> stars = new ArrayList<Star>();
	
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	private Star newStar;
	
	private Asteroid newAsteroid;
	
	private Overlay overlay;
	
	private Background background;

	public Component() {
		// sets up the spaceship 
		Timer t = new Timer(10, this);
		addKeyListener(this);
		setFocusable(true);
		spaceship = new Spaceship(34, 250);
		t.start();
		
		// creates the background
		background = new Background(0, 0);
		
		// procedurally generates stars for a parallax effect
		int verticalCounter = -16;
		int diameter = 5;
		for(int i = 0; i < 13; i++) {
			for(int j = 16; j <= Main.WINSIZE; j += 32) {
				newStar = new Star(j + Math.random() * 32, Math.random() * 32 + verticalCounter, diameter);
				stars.add(newStar);
			}
			verticalCounter += 64;
		}
		
		int xPos = Main.WINSIZE + ASTEROID_WIDTH;
		for(int i = 0; i < NUM_ASTEROIDS; i++) {
			newAsteroid = new Asteroid(xPos, (Math.random() * Main.WINSIZE - ASTEROID_HEIGHT) + ASTEROID_HEIGHT, ASTEROID_WIDTH, ASTEROID_HEIGHT);
			asteroids.add(newAsteroid);
			xPos += 50;
		}
		
		// creates the overlay
		overlay = new Overlay(0, 0);
	}
	
	public void update(long diff) {
		// updates the location of the stars
		for(int i = 0; i < stars.size(); i++) {
			stars.get(i).update(diff);
			if(stars.get(i).getX() < -5) {
				stars.get(i).setX(Main.WINSIZE + 5);
			}
		}
		// updates the location of the asteroids
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update(diff);
			if(asteroids.get(i).getX() < -ASTEROID_WIDTH) {
				asteroids.get(i).setX(Main.WINSIZE + ASTEROID_WIDTH);
				asteroids.get(i).setY((int)(Math.random() * Main.WINSIZE - ASTEROID_WIDTH));
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		// recovers Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		
		// fills in the frame with space
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Main.WINSIZE, Main.WINSIZE);
		
		// draws the stars
		for(int i = 0; i < stars.size(); i++) {
			stars.get(i).draw(g2);
		}
		
		// draws the background 
		background.draw(g2);
		
		// draws the spaceship
		spaceship.draw(g2); 
		
		// draws all of the asteroids
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g2);
		}
		
		// draws the game overlay
		overlay.draw(g2);
	}
	
	public boolean asteroidCollision() {
		boolean collided = false;
		for(int i = 0; i < asteroids.size(); i++) {
			if(spaceship.getHitBox().intersects(asteroids.get(i).getHitBox())) {
				collided = true;
				System.out.println("Asteroid collision was detected!");
			}
		}
		return collided;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		spaceship.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				spaceship.setDy(-(SPACESHIP_SPEED));
				break;
			case KeyEvent.VK_LEFT:
				spaceship.setDx(-(SPACESHIP_SPEED));
				break;
			case KeyEvent.VK_DOWN:
				spaceship.setDy(SPACESHIP_SPEED);
				break;
			case KeyEvent.VK_RIGHT:
				spaceship.setDx(SPACESHIP_SPEED);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				spaceship.setDy(0);
				break;
			case KeyEvent.VK_LEFT:
				spaceship.setDx(0);
				break;
			case KeyEvent.VK_DOWN:
				spaceship.setDy(0);
				break;
			case KeyEvent.VK_RIGHT:
				spaceship.setDx(0);
				break;
		}
	}

}

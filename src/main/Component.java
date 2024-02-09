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
import file_handling.Sound;
import graphical_components.Overlay;
import graphical_components.Portal;
import graphical_components.Star;
import graphical_components.StartScreen;

public class Component extends JComponent implements KeyListener, ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private Spaceship spaceship;
	
	private Sound COLLISION = new Sound("sounds/boom.wav");
	
	private final int SPACESHIP_SPEED = 2;
	
	private final int NUM_ASTEROIDS = 8;
	
	private final int ASTEROID_WIDTH = 123;
	
	private final int ASTEROID_HEIGHT = 113;
	
	private boolean isLooping;
	
	private ArrayList<Star> stars = new ArrayList<Star>();
	
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	private Star newStar;
	
	private Asteroid newAsteroid;
	
	private Overlay overlay;
	
	private Portal portal;
	
	private StartScreen startScreen;
	
	private Timer t = new Timer(10, this);

	public Component() {
		// initializes game state
		isLooping = false;
		
		// sets up the spaceship 
		addKeyListener(this);
		setFocusable(true);
		spaceship = new Spaceship(34, 250);
		t.start();
		
		// creates the background
		portal = new Portal(0, 0);
		
		// creates the start screen
		startScreen = new StartScreen(0, 0);
		
		// procedurally initializes stars for a parallax effect to be implemented
		int verticalCounter = -16;
		int diameter = 5;
		for(int i = 0; i < Main.WINSIZE / 64; i++) {
			for(int j = 16; j <= Main.WINSIZE; j += 32) {
				newStar = new Star(j + Math.random() * 32, Math.random() * 32 + verticalCounter, diameter);
				stars.add(newStar);
			}
			verticalCounter += 64;
		}
		
		// initializes the asteroids
		int xPos = (Main.WINSIZE * 2) + ASTEROID_WIDTH;
		for(int i = 0; i < NUM_ASTEROIDS; i++) {
			newAsteroid = new Asteroid(xPos, (Math.random() * Main.WINSIZE - ASTEROID_HEIGHT) + ASTEROID_HEIGHT, ASTEROID_WIDTH, ASTEROID_HEIGHT);
			asteroids.add(newAsteroid);
			xPos += 50;
		}
		
		// creates the overlay
		overlay = new Overlay(0, 0);
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
		portal.draw(g2);
		
		// draws the spaceship
		spaceship.draw(g2); 
		
		// draws all of the asteroids
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g2);
		}
		
		// draws the start screen
		startScreen.draw(g2);
		
		// draws the game overlay
		overlay.draw(g2);
	}
	
	public void update(long diff) {
		if(asteroidCollision()) isLooping = false;
		if(isLooping) {
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
	}
	
	private boolean asteroidCollision() {
		boolean collided = false;
		for(int i = 0; i < asteroids.size(); i++) {
			if(spaceship.getHitBox().intersects(asteroids.get(i).getHitBox())) {
				t.stop();
				collided = true;
				System.out.println("Asteroid collision was detected!");
			}
		}
		return collided;
	}
	
	public boolean isLooping() {return isLooping;}

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
				if(isLooping) spaceship.setDy(-(SPACESHIP_SPEED));
				break;
			case KeyEvent.VK_LEFT:
				if(isLooping) spaceship.setDx(-(SPACESHIP_SPEED));
				break;
			case KeyEvent.VK_DOWN:
				if(isLooping) spaceship.setDy(SPACESHIP_SPEED);
				break;
			case KeyEvent.VK_RIGHT:
				if(isLooping) spaceship.setDx(SPACESHIP_SPEED);
				break;
			case KeyEvent.VK_ENTER:
				isLooping = true;
				break;
			case KeyEvent.VK_ESCAPE:
				if(!asteroidCollision() && isLooping) {
					isLooping = false;
					Main.MUSIC.stop();
				} else {
					isLooping = true;
					Main.MUSIC.play();
				}
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

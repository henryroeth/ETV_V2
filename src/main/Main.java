package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import file_handling.Sound;

public class Main {
	
	public static final int DELAY = 1;
	
	public static final int WINSIZE = 800;
	
	public static final String TITLE = "Escape The Void!";
	
	public static void main(String[] args) {
		// initializes the frame 
		JFrame frame = new JFrame();
		frame.setSize(WINSIZE, WINSIZE);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("images/portal.png");
		frame.setIconImage(img.getImage());
		
		// adds the game component
		Component component = new Component();
		frame.add(component);
		frame.setVisible(true);
		
		// initiates the game loop
		Sound sound = new Sound();
		sound.playMusic("sounds/bg_music.wav");
		long oldTime = System.currentTimeMillis();
		while(!component.asteroidCollision()) {
			try {
				long currentTime = System.currentTimeMillis();
				long diff = currentTime - oldTime;
				component.update(diff);
				oldTime = currentTime;
				frame.repaint();
				Thread.sleep(DELAY);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Program has been terminated.");
		System.exit(0);
	}
}

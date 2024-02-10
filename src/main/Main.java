package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import file_handling.Sound;

public class Main {
	
	public static final int DELAY = 1;
	
	public static final int WINSIZE = 1000;
	
	public static final String TITLE = "Escape The Void! (ETV 2.0)";
	
	public static final Sound MUSIC = new Sound("sounds/bg_music.wav");
	
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
		
		// initiates the game loop and displays the game itself
		MUSIC.play();
		long oldTime = System.currentTimeMillis();
		while(true) {
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
	}
}

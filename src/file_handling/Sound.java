package file_handling;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * Sound class with methods to call wav files to be play in the main program.
 * @author henry
 *
 */
public class Sound {

	private File path;
	
	private Clip clip;
	
	public Sound(String path) {
		try {
			this.path = new File(path);
			if(this.path.exists()) {
				this.path.setExecutable(true);
				AudioInputStream audioInput=AudioSystem.getAudioInputStream(this.path);
				clip=AudioSystem.getClip();
				clip.open(audioInput);
			} else {
				System.out.println("Error!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		clip.stop();
	}
}

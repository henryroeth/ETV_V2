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
	/**
	 * Method to play the the wav file from the specified file path that is to be fed into the method.
	 * @param musicLocation The file path of the wav file.
	 */
	public void playMusic(String musicLocation) {
		try {
			File filePath=new File(musicLocation);
			if(filePath.exists()) {
					filePath.setExecutable(true);
					AudioInputStream audioInput=AudioSystem.getAudioInputStream(filePath);
					Clip clip=AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				System.out.println("Error");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

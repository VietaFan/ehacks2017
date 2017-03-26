package soundplay;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.embed.swing.JFXPanel;

public class SoundPlayer {

	private Clip clip;
	
	
	public SoundPlayer(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("soundFiles/" +filename));
		this.clip= AudioSystem.getClip();
		clip.open(audioIn);
	}

	static{
	    JFXPanel fxPanel = new JFXPanel();
	}
	
	public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		this.clip.start();
	}
	
	public void stop(){
		clip.stop();
		
	}
	public static void main(String[] args) throws Exception {
//		SoundPlayer sp = new SoundPlayer("applause_y.wav");
//		sp.play();
		
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("soundFiles/applause_y.wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}
}


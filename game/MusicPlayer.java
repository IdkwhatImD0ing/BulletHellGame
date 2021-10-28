package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class MusicPlayer extends Thread{
    private Clip clip;
    private AudioInputStream audioInputStream;
    private String filePath;

    public MusicPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.filePath = filePath;
        
    }

    @Override
    public void run() {
        try{
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }

        catch (Exception e){
            System.out.println("Error when playing music.");
            e.printStackTrace();
        }
        
        
    }
    
}

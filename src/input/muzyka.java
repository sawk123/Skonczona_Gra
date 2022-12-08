package input;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class muzyka {

    public static void grana_muzyka(String sciezka){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(sciezka));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e){
            e.printStackTrace();
        }

    }
}

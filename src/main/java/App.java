import io.github.mwttg.ezacoustics.*;
import org.joml.Vector3f;

public class App {

    public static void main(String[] args) {
        SoundDevice.initialize();
        SoundListener.create(new Vector3f());

        final var sound = Sound.create("./data/jump.wav");
        sound.play();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EzCleanUp.purge();
    }
}

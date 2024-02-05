import io.github.mwttg.ezacoustics.*;
import org.joml.Vector3f;

public class App {

    public static void main(String[] args) {
        SoundDevice.initialize();
        SoundListener.create(new Vector3f(0.0f, 0.0f, 20.0f));

        final var sound = Sound.create("./data/jump.wav", true);
        var position = new Vector3f(-10.0f, 0.0f, 0.0f);

        sound.play();
        while (position.x < 10.0f) {
            position.add(0.2f, 0.0f, 0.0f);
            sound.updatePosition(position);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




        EzCleanUp.purge();
    }
}

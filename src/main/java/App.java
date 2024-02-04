import io.github.mwttg.ezacoustics.*;
import org.joml.Vector3f;

public class App {

    public static void main(String[] args) {
        SoundDevice.create();
        SoundListener.create(new Vector3f());
        final var id = SoundBuffer.create("./data/jump.wav");
        final var source = SoundSource.create(id, false, new Vector3f());

        SoundSource.play(source);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        EzCleanUp.purge();
    }
}

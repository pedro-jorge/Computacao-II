import java.util.Random;

public class Dado implements Sorteador {
    private Random random;

    public Dado() {
        this.random = new Random();
    }

    @Override
    public int sortear() {
        return this.random.nextInt(6+1);
    }
}

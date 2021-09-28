import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Random;

public class DadoGenerico<T> implements Sorteador<T> {

    Map<T, Integer> frequenciaByResultado;

    public DadoGenerico(Map<T, Integer> frequenciaByResultado) {
        this.frequenciaByResultado = frequenciaByResultado;
    }

    @Override
    public T sortear() {
        Random random = new Random();
        Map<T, Integer> frequenciasAcumuladas = new HashMap<>();

        SortedSet<T> keys = new TreeSet<>(frequenciaByResultado.keySet());
        
        Integer value = 0;
        for(T key : keys) {
            value += frequenciaByResultado.get(key);
            frequenciasAcumuladas.put(key, value);
        }

        float x = random.nextFloat() * value;
        for(T key : keys) {
            if(x<frequenciasAcumuladas.get(key))
                return key;
        }

        return null;
    }
}

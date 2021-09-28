import java.util.List; 
import java.util.ArrayList;

public class SorteadorViaTrio implements Sorteador<Integer> {
    DadoComum dado;

    public SorteadorViaTrio(DadoComum dadoComum) {
        this.dado = dadoComum;
    }

    @Override
    public Integer sortear() {
        List<Object> results = new ArrayList<>();

        for(int i=0; i<3; i++)
            results.add(this.dado.sortear());
        
        if(results.get(0).equals(results.get(1)) && results.get(1).equals(results.get(2)))
            return 1;
        
        return 0;
    }
}

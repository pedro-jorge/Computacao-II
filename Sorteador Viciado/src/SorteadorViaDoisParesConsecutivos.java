import java.util.ArrayList;
import java.util.List;

public class SorteadorViaDoisParesConsecutivos implements Sorteador<Integer> {
    DadoGenerico<Object> dado;

    public SorteadorViaDoisParesConsecutivos(DadoGenerico<Object> dadoGenerico) {
        this.dado = dadoGenerico;
    }

    @Override
    public Integer sortear() {
        List<Object> results = new ArrayList<>();

        for(int i=0; i<4; i++)
            results.add(this.dado.sortear());
        
        if(results.get(0).equals(results.get(1)) && results.get(2).equals(results.get(3)))
            return 1;
        
        return 0;
    }
}
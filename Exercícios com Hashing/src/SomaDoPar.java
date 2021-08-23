import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class SomaDoPar {

    /**
     * Percorre a lista dada para encontrar um par de elementos
     * cuja soma seja o valor desejado.
     *
     * @param numeros      uma lista de inteiros
     * @param somaDesejada a soma desejada
     * @return O menor dos elementos do par encontrado;
     * ou null, caso nenhum par de elementos some o valor desejado
     */
    public static Integer encontrarParComSomaDada(ArrayList<Integer> numeros, int somaDesejada) {
        // pré-processa: joga toda a lista num hash set
        HashSet<Integer> hashSet = new HashSet<>(numeros);

        // variáveis auxiliares
        Integer resultado = null;
        Integer candidato = (Integer) Collections.max(hashSet);

        // para cada elemento da lista
        for (Integer i : numeros) {
            // computa o complemento dele
            // pergunta pro hash se ele contém o elemento
            if (somaDesejada - i < candidato && hashSet.contains(somaDesejada - i)) {
                // se sim, temos um novo candidato
                candidato = somaDesejada - i;
                // e o resultado por hora é o atual candidato
                resultado = candidato;
            }
        }

        return resultado;
    }

    public static Integer encontrarParComSomaDadaIngenuo(ArrayList<Integer> numeros, int somaDesejada) {

        // Algoritmo ineficiente (quadrático):

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);
                if (x + y == somaDesejada) {
                    return Math.min(x, y);
                }
            }
        }

        return null;
    }
}

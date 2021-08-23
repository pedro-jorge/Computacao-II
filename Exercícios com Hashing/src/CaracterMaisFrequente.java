import java.util.HashMap;

public class CaracterMaisFrequente {

    public static char encontrarCaracterMaisFrequente(String texto) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int max = 0;
        char charMaisFrequente = ' ';

        for (Character c : texto.toCharArray()) {
            Integer count = 1;

            if (hashMap.containsKey(c)) {
                count = hashMap.get(c);
                count += 1;
            }

            if (count > max) {
                max = count;
                charMaisFrequente = c;
            }

            hashMap.put(c, count);
        }

        return charMaisFrequente;
    }

    public static char encontrarCarecterMaisFrequenteIngenuo(String texto) {
        // Algoritmo ineficiente (quadrático):

        char maisFrequente = texto.charAt(0);
        int ocorrenciasDoMaisFrequente = 1;

        for (int i = 0; i < texto.length(); i++) {
            char caracterDaVez = texto.charAt(i);
            int contOcorrencias = 1;
            for (int j = i + 1; j < texto.length(); j++) {
                if (texto.charAt(j) == caracterDaVez) {
                    contOcorrencias++;
                }
            }

            if (contOcorrencias > ocorrenciasDoMaisFrequente) {
                maisFrequente = caracterDaVez;
                ocorrenciasDoMaisFrequente = contOcorrencias;
            }
        }
        return maisFrequente;
    }
}

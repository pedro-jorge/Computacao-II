import org.junit.Test;
import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {

    @Test
    public void testarCaracterMaisFrequente() {
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequente("arara"));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequente("a r a r a 345 sdf hhh"));
    }

    @Test
    public void testarPerformanceDosMetodos() {
        String texto = "";

        /*
            gerei uma string aleatoria enorme de caracteres usando python, coloquei no arquvio "string.txt"
            e usei arquivos em java pra poder ler essa string e realizar um teste mais robusto com uma string grande
            aqui está dando 72 para o metodo normal e 17641 pro metodo ingenuo :o
         */
        try {
            URL url = getClass().getResource("string.txt");
            File file = new File(url.getPath());
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine())
            {
                texto += scanner.nextLine();
            }

            scanner.close();
        } catch(FileNotFoundException e) {e.printStackTrace();}

        long inicio = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCaracterMaisFrequente(texto);
        long duracaoLinear = System.currentTimeMillis() - inicio;

        inicio = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCarecterMaisFrequenteIngenuo(texto);
        long duracaoQuadratica = System.currentTimeMillis() - inicio;

        System.out.println(duracaoLinear);
        System.out.println(duracaoQuadratica);

        assertTrue(duracaoLinear < duracaoQuadratica);
    }
}
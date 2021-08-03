import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom; // encontrei essa forma interessante de gerar aleatorios

public class Main {
    public static int obterTamanhoIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
        Collections.sort(lista1);
        Collections.sort(lista2);
        
        System.out.println(lista1);
        System.out.println(lista2);

        ArrayList<Integer> l = new ArrayList<>();

        for(int i=0; i<lista1.size(); i++) {
            // armazenando em uma variavel para não precisar chamar duas vezes o método
            int lista1_i_value = lista1.get(i);

            for(int j=0; j<lista2.size(); j++) {
                // armazenando em uma variavel para não precisar chamar duas vezes o método
                int lista2_j_value = lista2.get(j);
                
                //como as listas estão ordenadas, se lista2[j] > lista1[i], então lista1[i] não está na lista
                if(lista2_j_value > lista1_i_value) break;

                if(lista1_i_value == lista2_j_value && !l.contains(lista2_j_value)) 
                    l.add(lista1_i_value);
            }
        }

        return l.size();
    }

    public static int getRandom(int max) {
        return ThreadLocalRandom.current().nextInt(1, max);
    }

    public static void main(String[] args) {
       // Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o tamanho da primeira lista: ");
        int n1 = scanner.nextInt();
        System.out.println("Insira o tamanho da segunda lista: ");
        int n2 = scanner.nextInt();

        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();

        for(int i=0; i<n1; i++)
            lista1.add(getRandom(n1*10));
        for(int i =0; i<n2; i++)
            lista2.add(getRandom(n2*10));

        System.out.println(obterTamanhoIntersecao(lista1, lista2));

        scanner.close();
    }
}
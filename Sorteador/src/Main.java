public class Main {
    public static void main(String args[]) {
        String nomeJogo = "JOGO MALUCO";
        String nomeJogador1 = "pedro jorge";
        String nomeJogador2 = "vinicius gusmao";
        int numeroDeRodadas = 10000;

        Sorteador sorteador1 = new DadosDeGamao(new Dado(), new Dado());
        Sorteador sorteador2 = new DadosTriplos(new Dado());

        JogoMalucoComSorteadores jogo = new JogoMalucoComSorteadores(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas, sorteador1, sorteador2);
        
        System.out.println(jogo.jogar());
    }
}

/* 
QUESTAO 8

Se o objetivo empírico é obter o maior resultado possível dentro do espaço de amostra, então o DadosTriplos é o melhor sorteador.
Executando 4 vezes o método main, obtive que o jogador2 venceu todas, com as respectivas pontuações:
6635 a 2680;
6639 a 2700;
6595 a 2732;
6711 a 2653.
Antes de rodar o programa eu já diria que o DadosTriplos é o melhor, mas não com 100% de certeza. Enquanto eu escrevia, já tinha essa ideia 
pois o DadosDeGamao só retorna um número com chances consideráveis de ser maior que o DadosTriplos quando os resultados dos dados são iguais (e isso é de uma probabilidade
de 1/12), que é o dobro da soma. Como nos outros casos é sempre dado1+dado2 contra dado1.sortear()+dado1.sortear()+dado1.sortear(), as chances de DadosTriplos retornar
um valor maior já parecia clara. 
*/

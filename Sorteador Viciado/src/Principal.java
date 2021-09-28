import java.util.Map;
import java.util.HashMap;

public class Principal {

    private static final int CONT_REPETICOES_POR_SIMULACAO = 1000;

    private static DadoComum dado = new DadoComum();
    private static SorteadorViaTrio sorteadorViaTrio = new SorteadorViaTrio(dado);

    private static void obterPercentuaisEmpiricos(int numeroDeRodadasPorPartida, int viciado) {
        
        Map<Integer, Integer> f = new HashMap<Integer, Integer>();

        f.put(1,1);
        f.put(2, viciado);
        f.put(3, 1);
        f.put(4, 1);
        f.put(5, 1);
        f.put(6, 1);

        DadoGenerico<Object> dadoViciado = new DadoGenerico(f);
        SorteadorViaDoisParesConsecutivos sorteadorViaDoisParesConsecutivos = new SorteadorViaDoisParesConsecutivos(dadoViciado);

        JogoMalucoComSorteadores<SorteadorViaDoisParesConsecutivos, SorteadorViaTrio> jogoMaluco;

        jogoMaluco = new JogoMalucoComSorteadores<>(
                "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                numeroDeRodadasPorPartida, sorteadorViaDoisParesConsecutivos, sorteadorViaTrio);

        for (int i = 0; i < CONT_REPETICOES_POR_SIMULACAO; i++) {
            jogoMaluco.jogar();
        }

        System.out.println(String.format("\n\n" +
                "      Para partidas com %d rodada(s):\n" +
                "      Vitorias do Jogador 1: %f%%\n" +
                "      Vitorias do Jogador 2: %f%%\n" +
                "      Empates: %f%%",
                numeroDeRodadasPorPartida,
                jogoMaluco.getPercentualVitoriasJogador1(),
                jogoMaluco.getPercentualVitoriasJogador2(),
                jogoMaluco.getPercentualEmpates()));
    }

    public static void main(String[] args) {
        /*
        QUESTAO 4
        quando o dado é honesto, há uma tendencia para 37,5% para os jogadores e 25% para o empate
        quando o dado é viciado (no caso escolhi o pares consecutivos), a taxa de vitória do jogador injusto tende a 100%.
        */
        // 1 para um dado honesto, 6 para um dado viciado com 90% para o 2
        int viciado = 6;
        for (int numeroDeRodadas = 1; numeroDeRodadas <= 1000; numeroDeRodadas++) {
            obterPercentuaisEmpiricos(numeroDeRodadas, viciado);
        }
    }
}

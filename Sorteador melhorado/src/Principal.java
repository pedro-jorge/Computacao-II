public class Principal {

    public static void main(String[] args) {

        DadosDeGamao dadosDeGamao = new DadosDeGamao();
        DadosTriplos dadosTriplos = new DadosTriplos();

        for (int numeroDeRodadas = 1; numeroDeRodadas <= 10000; numeroDeRodadas*=2) {

            JogoMalucoComSorteadores jogoMaluco = new JogoMalucoComSorteadores(
                    "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                    numeroDeRodadas, dadosDeGamao, dadosTriplos);
            
            for(int i=0; i<1000; i++)
                jogoMaluco.jogar();

            if(numeroDeRodadas > 1)
                System.out.println(String.format("Para partidas com %d rodadas:", numeroDeRodadas));
            else 
                System.out.println("Para partidas com 1 rodada:");
            
            System.out.println(String.format("Vitorias do Jogador 1: %.1f", jogoMaluco.getPercentualVitoriasJogador1()) + "%");
            System.out.println(String.format("Vitorias do jogador 2: %.1f", jogoMaluco.getPercentualVitoriasJogador2()) + "%");
            System.out.println(String.format("Empates: %.1f", jogoMaluco.getPercentualEmpates()) + "%");
            System.out.print("\n");

            //System.out.println(jogoMaluco.obterResultadoUltimoJogo());
        }
    }
}

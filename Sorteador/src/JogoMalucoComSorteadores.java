public class JogoMalucoComSorteadores extends JogoDeDoisJogadores {
    Sorteador sorteador1, sorteador2;

    public JogoMalucoComSorteadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas, Sorteador sorteador1, Sorteador sorteador2) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }

    @Override 
    public int executarRodadaDoJogo() {
        int numeroSorteadoJogador1 = sorteador1.sortear();
        int numeroSorteadoJogador2 = sorteador2.sortear();
        
        if(numeroSorteadoJogador1 > numeroSorteadoJogador2)
            return 1;
        if(numeroSorteadoJogador2 > numeroSorteadoJogador1)
            return 2;
        
        return 0;
    }
}

import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {
    String nomeJogo, nomeJogador1, nomeJogador2;
    int numeroDeRodadas;
    ArrayList<Integer> historicoResultados;

    public JogoDeDoisJogadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
    }

    public String getNomeJogo() {
        return this.nomeJogo;
    }
    
    public String getNomeJogador1() {
        return this.nomeJogador1;
    }

    public String getNomeJogador2() {
        return this.nomeJogador2;
    }

    public int getNumeroDeRodadas() { 
        return this.getNumeroDeRodadas();
    }

    protected abstract int executarRodadaDoJogo();

    public String jogar() {
        int vitoriasJogador1 = 0, vitoriasJogador2 = 0;
        
        for(int i=0; i<numeroDeRodadas; i++) {
            int result = executarRodadaDoJogo();

            if(result == 1)
                vitoriasJogador1++;
            else if(result == 2)
                vitoriasJogador2++;
        }

        if(vitoriasJogador1 == vitoriasJogador2) {
            return String.format("O jogo terminou em empate após %d rodadas", this.numeroDeRodadas);
        }

        String s = "O jogador %s venceu o jogo por %d a %d";
        
        if(vitoriasJogador1 > vitoriasJogador2) 
            return String.format(s, this.nomeJogador1, vitoriasJogador1, vitoriasJogador2);
        
        return String.format(s, this.nomeJogador2, vitoriasJogador2, vitoriasJogador1);
    }
}
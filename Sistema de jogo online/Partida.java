import java.util.ArrayList;

public class Partida {
    private ArrayList<Jogador> jogadoresDaPartida;
    private int resultado;

    public Partida(Jogador jogador1, Jogador jogador2) {
        jogadoresDaPartida = new ArrayList<Jogador>();

        this.jogadoresDaPartida.add(jogador1);
        this.jogadoresDaPartida.add(jogador2);
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadoresDaPartida;
    }
}

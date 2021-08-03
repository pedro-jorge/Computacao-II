import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JogoOnline {
    private final ArrayList<Jogador> jogadores;

    public JogoOnline() {
        jogadores = new ArrayList<>();
    }

    public boolean cadastrarJogador(String username, String senha) {
        for(Jogador jogador : jogadores) {
            if(jogador.getUsername().equals(username)) {
                return false;
            }
        }

        this.jogadores.add(new Jogador(username, senha));
        return true;
    }

    public Jogador fazerLogin(String username, String senha) {
        for(Jogador jogador : jogadores) {
            if(jogador.getUsername().equals(username) && jogador.getSenha().equals(senha)) {
                jogador.setOnline();
                return jogador;
            }
        }

        return null;
    }

    public void fazerLogout(String username) {
        for(Jogador jogador : jogadores) {
            if(jogador.getUsername().equals(username)) {
                jogador.setOffline();
            }
        }
    }

    public Partida iniciarPartida(Jogador jogador1, Jogador jogador2) {
        if(jogador1.getIsOnline() && !jogador1.getIsPlaying() && jogador2.getIsOnline() && !jogador2.getIsPlaying()) {
            jogador1.changePlayingStatus();
            jogador2.changePlayingStatus();

            return new Partida(jogador1, jogador2);
        }

        return null;
    }

    public Jogador escolherAdversario(Jogador solicitante) {
        ArrayList<Jogador> jogadoresOnline = new ArrayList<>();

        for(Jogador j : jogadores) {
            if(!j.getUsername().equals(solicitante.getUsername()) && j.getIsOnline() && !j.getIsPlaying())
                jogadoresOnline.add(j);
        }

        int adversarioIndex = ThreadLocalRandom.current().nextInt(0, jogadoresOnline.size());

        return jogadoresOnline.get(adversarioIndex);
    }

    // resultado será 1, 2 ou 0
    public void encerrarPartida(Partida partida, int resultado) {
        ArrayList<Jogador> jogadoresDaPartida = partida.getJogadores();

        Jogador jogador1 = jogadores.get(jogadores.indexOf((jogadoresDaPartida.get(0))));
        Jogador jogador2 = jogadores.get(jogadores.indexOf((jogadoresDaPartida.get(1))));

        partida.setResultado(resultado);

        jogador1.changePlayingStatus();
        jogador2.changePlayingStatus();

        jogador1.setPartida(partida);
        jogador2.setPartida(partida);

        if(partida.getResultado() == 1) {
            jogador1.setPoints(1);
            jogador2.setPoints(-1);
        }
        else if(partida.getResultado() == 2) {
            jogador1.setPoints(-1);
            jogador2.setPoints(1);
        }
    }
}

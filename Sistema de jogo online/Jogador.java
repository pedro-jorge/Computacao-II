import java.util.ArrayList;

public class Jogador {
    private final String username;
    private String senha;
    private int points;
    private ArrayList<Partida> partidas;
    private boolean is_online, is_playing;

    public Jogador(String username, String senha) {
        this.username = username;
        this.senha = senha;
        this.partidas = new ArrayList<Partida>();
        this.points = 1000;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSenha() {
        return this.senha;
    }

    public boolean getIsOnline() {
        return this.is_online;
    }

    public boolean getIsPlaying() {
        return this.is_playing;
    }

    public int getPartidasJogadas() { return this.partidas.size(); }

    public int getPoints() { return this.points; }

    public void setOnline() {
        this.is_online = true;
    }

    public void setOffline() {
        this.is_online = false;
    }

    public void changePlayingStatus() {
        this.is_playing = !this.is_playing;
    }

    public void setPartida(Partida partida) { this.partidas.add(partida); }

    public void setPoints(int p) {
        this.points += p;
    }
}

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JogoOnlineTest {
    Jogador pedro, jorge;
    JogoOnline jogo;

    @Before
    public void setUp() {
        jogo = new JogoOnline();

        jogo.cadastrarJogador("pedro", "123");
        pedro = jogo.fazerLogin("pedro", "123");
        jogo.cadastrarJogador("jorge", "123");
        jorge = jogo.fazerLogin("jorge", "123");
    }

    @Test
    public void testarCadastrarJogador() {
        Assert.assertTrue(jogo.cadastrarJogador("vinicius", "gusmao"));
    }

    @Test
    public void testarFazerLogin() {
        Assert.assertTrue(pedro.getIsOnline());
    }

    @Test
    public void testarFazerLogout() {
        jogo.fazerLogout(pedro.getUsername());

        Assert.assertFalse(pedro.getIsOnline());
    }

    @Test
    public void testarIniciarPartida() {
        Partida partida = jogo.iniciarPartida(pedro, jorge);

        Assert.assertNotNull(partida);

        Assert.assertTrue(pedro.getIsPlaying());
        Assert.assertTrue(jorge.getIsPlaying());
    }

    @Test
    public void testarIniciarPartidaInvalida() {
        jorge.setOffline();
        Partida partida = jogo.iniciarPartida(pedro, jorge);

        Assert.assertFalse(pedro.getIsPlaying());
        Assert.assertNull(partida);
    }

    @Test
    public void testarEscolherAdversario() {
        Jogador adversario = jogo.escolherAdversario(pedro);

        // como só há dois jogadores, o único adversário possível é jorge
        assertEquals(adversario.getUsername(), "jorge");
    }

    @Test
    public void testarEncerrarPartida() {
        Partida partida = jogo.iniciarPartida(pedro, jorge);
        // pedro venceu
        jogo.encerrarPartida(partida, 1);

        Assert.assertFalse(pedro.getIsPlaying());
        Assert.assertFalse(jorge.getIsPlaying());

        assertEquals(pedro.getPartidasJogadas(), 1);
        assertEquals(jorge.getPartidasJogadas(), 1);

        assertEquals(pedro.getPoints(), 1001);
        assertEquals(jorge.getPoints(), 999);
    }
}

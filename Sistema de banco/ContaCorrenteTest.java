import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

// FixMethodOrder pois estava crashando devido ao testGetQuantidadeDeTransacoesDeTodasAsContas()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaCorrenteTest {

    // para cobrir pequenos erros de precisão do tipo float
    private final float FLOAT_DELTA = 0.00001f;

    private ContaCorrente contaDoJoao, contaDaMaria;
    private Correntista joao, maria;
    private float saldoInicialJoao, saldoInicialMaria;

    @Before
    public void setUp() {
        joao = new Correntista(1234, "joao");
        maria = new Correntista(1235, "maria");
        contaDoJoao = new ContaCorrente(1, joao);
        contaDaMaria = new ContaCorrente(2, maria);
        saldoInicialJoao = contaDoJoao.getSaldoEmReais();
        saldoInicialMaria = contaDaMaria.getSaldoEmReais();
    }

    @Test
    public void testarsaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.",
                10.0,
                saldoInicialJoao,
                FLOAT_DELTA);
    }

    @Test
    public void testGetQuantidadeDeTransacoesDeTodasAsContas() {
        ContaCorrente conta1 = new ContaCorrente(10, joao);
        ContaCorrente conta2 = new ContaCorrente(11, maria);

        conta1.efetuarTransferecia(conta2, 5);
        assertEquals(
                1,
                ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito",
                saldoInicialJoao + 50,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados",
                saldoInicialJoao,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados",
                saldoInicialJoao,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato",
                extratoAntes, extratoDepois);
    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta",
                saldoInicialJoao - 2,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos",
                saldoInicialJoao,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferencia() {
        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("O saldo da conta destino deve ser somado com o valor da transferência.",
                saldoInicialMaria + 3,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("O saldo da conta de origem deve ser subtraído com o valor da transferência.",
                saldoInicialJoao - 3,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test(expected=IllegalArgumentException.class)
    public void testarTransferenciaSemFundos() {
        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals(
                "O saldo da conta destino deve continuar o mesmo se o valor da transferência é maior que o saldo da conta de origem. Uma exceção deve ser lançada.",
                saldoInicialMaria,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals(
                "O saldo da conta de origem não deve mudar se o valor da transferência for maior que o saldo atual. Uma exceção deve ser lançada.",
                saldoInicialJoao,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testGetCpfDoCorrentista() {
        assertEquals(
                joao.cpf,
                contaDoJoao.getCpfDoCorrentista());
    }
}
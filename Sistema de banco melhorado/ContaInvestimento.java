public class ContaInvestimento extends Conta {
    private String tipoInvestimento;
    private float taxaJuros;

        
    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaJuros) {
        super(numeroDaConta, correntista);

        this.verificarCorrentista();

        this.tipoInvestimento = tipoInvestimento;
        this.taxaJuros = taxaJuros;
    }

    public void verificarCorrentista() {
        if(this.correntista.getConta().equals(null)) {
            throw new RuntimeException("Correntista sem conta corrente!");
        }
    }

    public void aplicarJuros() {
        this.saldoEmReais *= (1 + taxaJuros);
    }

    public void resgatarTotal() {
        this.efetuarTransferecia(this.correntista.getConta(), this.saldoEmReais);
    }
}

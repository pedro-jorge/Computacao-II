public class Correntista {

    public final long cpf;
    private String nome;

    public Correntista(long cpf, String nome) {
        setNome(nome);
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

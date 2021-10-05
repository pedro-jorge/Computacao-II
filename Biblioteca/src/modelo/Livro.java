package modelo;

// import java.util.Objects;

public class Livro {

    private final String titulo;

    private final String autor;

    private final int anodePublicacao;

    public Livro(String titulo, String autor, int anoDePublicacao) {

        this.titulo = titulo;

        this.autor = autor;

        this.anodePublicacao = anoDePublicacao;
    }

    public String getTitulo() {
        return this.titulo;
    }
/*
    public String getAutor() {
        return this.autor;
    }

    public int getAnoDePublicacao() {
        return this.anodePublicacao;
    }
*/
}

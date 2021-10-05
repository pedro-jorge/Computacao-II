package modelo;

import java.util.*;

public class Usuario {

    private String nome;

    private final Long cpf;

    private String endereco;

    private ArrayList<Livro> objetosADevolver = new ArrayList<>();

    public Usuario(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }


    public long getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void devolverLivro( Livro livro) {
        objetosADevolver.remove(livro);
    }

    public void emprestarLivro (Livro livro) {
        objetosADevolver.add(livro);
    }

    public boolean possuiObjeto (Object obj){
        if(objetosADevolver.contains(obj))
            return true;
        return false;
    }

    public int getQuantidadeDeObjetosDevidos() {
        return objetosADevolver.size();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Usuario))
            return false;

        Usuario u = (Usuario) o;
        return u.cpf.equals(this.cpf);
    }

    @Override
    public int hashCode() {
        return this.cpf.hashCode();
    }
}

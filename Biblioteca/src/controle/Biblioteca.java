package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;

import java.util.ArrayList;

public class Biblioteca {

    /** quantidade mínima de unidades de um livro que precisam existir nas estantes da biblioteca para
     que o livro possa ser emprestado */
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;

    /** quantidade máxima de livros da biblioteca que podem estar emprestados, a qualquer tempo, a um mesmo usuário */
    public static final int MAX_LIVROS_DEVIDOS = 3;

    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livrosDoAcervo, livrosNaEstante, livrosDevidos;

    public Biblioteca() {
        usuarios = new ArrayList<>();
        livrosDoAcervo = new ArrayList<>();
        livrosNaEstante = new ArrayList<>();
        livrosDevidos = new ArrayList<>();
    }

    /**
     * Cadastra um usuário. Caso o usuário já exista, atualiza seu nome e seu endereço.
     *
     * @param usuario o usuário a ser inserido/atualizado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        if(!(getUsuario(usuario.getCpf())==null)) {
            Usuario u = getUsuario(usuario.getCpf());
            u.setNome(usuario.getNome());
            u.setEndereco(usuario.getEndereco());

            return;
        }
        
        this.usuarios.add(usuario);
    }

    /**
     * Retorna um usuário previamente cadastrado, a partir do CPF informado.
     *
     * @param cpf o CPF do usuário desejado
     * @return o usuário, caso exista; ou null, caso não exista nenhum usuário cadastrado com aquele CPF
     */
    public Usuario getUsuario(long cpf) {
        for(Usuario u : this.usuarios) {
            if(u.getCpf() == cpf) {
                return u;
            }
        }
        return null;
    }

    /**
     * @return o número total de usuários cadastrados na biblioteca
     */
    public int getTotalDeUsuariosCadastrados() {
        return this.usuarios.size();
    }

    /**
     * Adquire `quantidade` cópias do livro informado e as inclui no acervo da biblioteca.
     *
     * @param livro o livro sendo adquirido
     * @param quantidade o número de cópias do livro sendo adquiridas
     */
    public void incluirLivroNoAcervo(Livro livro, int quantidade) {
        for(int i=0; i<quantidade; i++) {
            this.livrosDoAcervo.add(livro);
            this.livrosNaEstante.add(livro);
        }
    }

    /**
     * Empresta um livro para um usuário da biblioteca.
     *
     * @param livro o livro a ser emprestado
     * @param usuario o usuário que está pegando emprestado o livro
     *
     * @return true, se o empréstimo foi bem-sucedido;
     *         false, se o livro não está disponível para empréstimo
     *
     *         (IMPORTANTE: um livro é considerado disponível para empréstimo se há pelo menos
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR cópias daquele livro nas estantes da biblioteca;
     *                      isto é, as estantes da biblioteca jamais poderão ficar com menos do que
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR-1 cópias de qualquer livro de seu acervo)
     *
     * @throws UsuarioNaoCadastradoException se o usuário informado não está cadastrado na biblioteca
     * @throws LimiteEmprestimosExcedidoException se o usuário já está com muitos livros emprestados no momento
     */
    public boolean emprestarLivro(Livro livro, Usuario usuario)
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {

            int quantidadeDisponivel = 0;
            for(Livro l : this.livrosNaEstante) {
                if(l.getTitulo().equals(livro.getTitulo()))
                    quantidadeDisponivel++;
            }

            if(quantidadeDisponivel<this.MIN_COPIAS_PARA_PODER_EMPRESTAR)
                return false;
            
            if(this.getUsuario(usuario.getCpf())==null)
                throw new UsuarioNaoCadastradoException();
            
            if(usuario.getQuantidadeDeObjetosDevidos()==Biblioteca.MAX_LIVROS_DEVIDOS)
            {throw new LimiteEmprestimosExcedidoException();}

            usuario.emprestarLivro(livro);
            this.livrosDevidos.add(livro);
            this.livrosNaEstante.remove(livro);

            return true;
    }

    /**
     * Recebe de volta um livro que havia sido emprestado.
     *
     * @param livro o livro sendo devolvido
     * @param usuario o usuário que havia tomado emprestado aquele livro
     *
     * @throws DevolucaoInvalidaException se o livro informado não se encontra emprestado para o usuário informado
     */
    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {
        if(!usuario.possuiObjeto(livro))
            throw new DevolucaoInvalidaException();
        
        usuario.devolverLivro(livro);
        this.livrosDevidos.remove(livro);
        this.livrosNaEstante.add(livro);
    }

    /**
     * Retorna a quantidade de livros emprestados ao usuário informado.
     *
     * @param usuario o usuário desejado
     * @return a quantidade de livros emprestados àquele usuário; caso o usuário não esteja devendo nenhum livro,
     *         ou não seja nem mesmo um usuário cadastrado na biblioteca, retorna zero, não deve nada
     */
    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {
        return usuario.getQuantidadeDeObjetosDevidos();
    }

    /**
     * @return a quantidade total de livros nas estantes da biblioteca (isto é, a soma das quantidades de cópias
     *         não-emprestadas de todos os livros do acervo da biblioteca).
     */
    public int getQuantidadeDeLivrosNaEstante() {
        return livrosNaEstante.size();
    }

    /**
     * Retorna o número de cópias do livro informado que existem na estante da biblioteca
     * (isto é, que não estão emprestados).
     *
     * @param livro o livro desejado
     * @return o número de cópias não-emprestadas daquele livro
     */
    public int getQuantidadeDeLivrosNaEstante(Livro livro) {
        int count = 0;
        for(Livro l : this.livrosNaEstante) {
            if(l.getTitulo().equals(livro.getTitulo()))
                count++;
        }

        return count;
    }
}

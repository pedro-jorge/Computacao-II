import java.util.ArrayList;
import java.util.HashMap;
/**
 * Esta classe implementa um álbum (de figurinhas, selos, etc.) online, permitindo
 * colecionar itens que possuem uma posição específica no álbum.
 *
 * Itens são acrescentados ao álbum por meio de "pacotinhos" contendo uma quantidade
 * fixa, pré-feterminada de itens.
 *
 * Itens já existentes no álbum e recebidos novamente em pacotinhos posteriores são
 * armazenados para eventuais trocas com outro usuários.
 *
 * @param <T> o tipo de objeto colecionável que o álbum irá armazenar
 */
public class Album<T extends Colecionavel> {

    private int tamanho;
    private int quantItensPorPacotinho;
    private HashMap<Integer, T> itens;
    private ArrayList<T> itensRepetidos;
    /**
     * Construtor.
     *
     * @param tamanhoDoAlbum O tamanho do álbum sendo criado (note que os itens que serão colecionados
     *                       terão posições entre 1 e o tamanho do álbum)
     *
     * @param quantItensPorPacotinho A quantidade de itens em cada pacotinho adquirido para este álbum
     */
    public Album(int tamanhoDoAlbum, int quantItensPorPacotinho) {
        this.tamanho = tamanhoDoAlbum;
        this.quantItensPorPacotinho = quantItensPorPacotinho;
        this.itens = new HashMap<>();
        this.itensRepetidos = new ArrayList<>();
    }

    /**
     * Recebe novos itens que serão imediatamente "colados" ao álbum, se inéditos,
     * ou guardados para troca, se repetidos.
     *
     * @param pacotinho Um pacotinho de itens, que poderão ser inéditos ou repetidos
     *
     * @throws PacotinhoInvalidoException se o pacotinho contiver uma quantidade errada de itens,
     *                                    ou se contiver algum item que não pertença ao álbum
     *                                    (por indicar uma posição menor que 1 ou maior que seu tamanho)
     */
    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {
        if(pacotinho.size() != this.quantItensPorPacotinho) {
            throw new PacotinhoInvalidoException();
        }

        for(T c : pacotinho) {
            if(c.getPosicao()<1 || c.getPosicao()>this.tamanho)
                throw new PacotinhoInvalidoException();
        }

        for(T c : pacotinho) {
            if(!this.itens.containsKey(c.getPosicao())) {
                this.itens.put(c.getPosicao(), c);
            }

            else {
                this.itensRepetidos.add(c);
            }
        }
        // ToDo IMPLEMENT ME!!
    }

    /**
     * @return a quantidade total de figurinhas que este álbum apresenta quando se encontra completo
     */
    public int getTamanho() {
        return this.tamanho;  // ToDo IMPLEMENT ME!!
    }

    /**
     * @return a quantidade total de itens que estão "colados" no álbum,
     * isto é, o total de itens distintos que foram recebidos até o momento
     */
    public int getQuantItensColados() {
        return this.itens.size();  // ToDo IMPLEMENT ME!!
    }

    /**
     * @return o total de itens repetidos que foram acumulados até o momento
     */
    public int getQuantItensRepetidos() {
        return this.itensRepetidos.size();  // ToDo IMPLEMENT ME!!
    }

    public boolean possuiItemColado(int posicao) {
        return this.itens.containsKey(posicao);
    }

    public boolean possuiItemRepetido(int posicao) {
        for(T c : this.itensRepetidos) {
            if (c.getPosicao() == posicao) 
                return true;
        }

        return false;
    }

    /**
     * @return a quantidade de itens que faltam para o álbum ficar completo
     */
    public int getQuantItensFaltantes() {
        return this.tamanho - this.itens.size();  // ToDo IMPLEMENT ME!!
    }

    /**
     * @param posicao a posição do iten desejado
     * @return o item que está colado na posição especificada, se houver; null, caso contrário
     */
    public T getItemColado(int posicao) {
        if(this.itens.containsKey(posicao)) {
            return this.itens.get(posicao);
        }
        return null;  // ToDo IMPLEMENT ME!!
    }
}

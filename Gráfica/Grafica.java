import java.util.ArrayList;

public class Grafica {
    ArrayList<Impressora> impressoras;
    ArrayList<Documento> documentosNaFila;
    float precoPorPaginaEmCores, precoPorPagina;
    int impressoraAtualIndex;

    public Grafica() {
        impressoras = new ArrayList<Impressora>();
        documentosNaFila = new ArrayList<>();
        this.impressoraAtualIndex = 0;
    }

    public void imprimirDocumento(Documento documento) {
        impressoras.get(impressoraAtualIndex).imprimirDocumento(documento);
        impressoraAtualIndex+=1;

        if(impressoraAtualIndex == impressoras.size()) {
            impressoraAtualIndex = 0;
        }
    }

    public float orcarImpressao(Documento documento) {
        if(documento.isEmCores())
            return precoPorPaginaEmCores*documento.getQuantPaginas();

        return precoPorPagina*documento.getQuantPaginas();
    }

    public void adicionarImpressora(Impressora impressora) {
        this.impressoras.add(impressora);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if(emCores) {
            this.precoPorPaginaEmCores = precoPorPagina;
            return;
        }

        this.precoPorPagina = precoPorPagina;
    }
}

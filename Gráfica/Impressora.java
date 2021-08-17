public abstract class Impressora {
    protected int numeroDeFolhas;
    protected int numerodeDocumentosImpressos;
    protected boolean imprimindo;

    public Impressora() {
        this.numeroDeFolhas = 0;
        this.numerodeDocumentosImpressos = 0;
        this.imprimindo = false;
    }

    public boolean imprimirDocumento(Documento documento) {
        // verifica se há papel suficiente (se não houver, não imprime)
        if(documento.getQuantPaginas() > this.numeroDeFolhas) {
            return false;
        }

        // incrementa o número de documentos impressos
        this.numerodeDocumentosImpressos+=1;
        this.numeroDeFolhas -= documento.getQuantPaginas();
        this.imprimindo = true;

        // para cada página, manda imprimir de fato
        for (int i = 1; i <= documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
        }

        return true;
    }

    public void carregarPapel(int numeroDeFolhas) {
        if(numeroDeFolhas > 0) {
            this.numeroDeFolhas += numeroDeFolhas;
        }
    }

    public int getQuantidadeFolhasRestantes() {
        return this.numeroDeFolhas;
    }

    public int getQuantidadeDocumentosImpressos() {
        return this.numerodeDocumentosImpressos;
    }

    public boolean isImprimindo() { return this.imprimindo; }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}

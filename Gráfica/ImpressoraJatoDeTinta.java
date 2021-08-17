public class ImpressoraJatoDeTinta extends Impressora {
    private int tintaPreta, tintaAmarela, tintaMagenta, tintaCiano;

    public ImpressoraJatoDeTinta() {
        this.tintaPreta = 100;
        this.tintaAmarela = 100;
        this.tintaMagenta = 100;
        this.tintaCiano = 100;
    }

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando bicos de impressão e verificando tintas...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando jatos de tinta...");
    }

    public int getNivelTintaPreta() {
        return this.tintaPreta;
    }

    public int getNivelTintaAmarela() {
        return this.tintaAmarela;
    }

    public int getNivelTintaMagenta() {
        return this.tintaMagenta;
    }

    public int getNivelTintaCiano() {
        return this.tintaCiano;
    }
}

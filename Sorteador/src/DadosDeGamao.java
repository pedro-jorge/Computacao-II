public class DadosDeGamao implements Sorteador { 
    Dado dado1, dado2;

    public DadosDeGamao(Dado dado1, Dado dado2) {
        this.dado1 = dado1;
        this.dado2 = dado2;
    }

    @Override
    public int sortear() {
        int valorDado1 = this.dado1.sortear();
        int valorDado2 = this.dado2.sortear();

        if(valorDado1 == valorDado2) {
            return 2*(valorDado1+valorDado2);
        }

        return valorDado1+valorDado2;
    }
}
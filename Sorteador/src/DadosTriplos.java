public class DadosTriplos implements Sorteador{
    Dado dado;

    public DadosTriplos(Dado dado) {
        this.dado = dado;
    }

    @Override
    public int sortear() {
        int result = 0;

        for(int i=0; i<3; i++) {
            result += this.dado.sortear();
        }

        return result;
    }
}

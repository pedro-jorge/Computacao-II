import java.util.ArrayList;

public class Documento {
    ArrayList<String> paginas;
    boolean emCores;

    public Documento(ArrayList<String> paginas, boolean emCores) {
        this.paginas = paginas;
        this.emCores = emCores;
    }

    public boolean isEmCores() {
        return this.emCores;
    }

    public int getQuantPaginas() {
        return this.paginas.size();
    }

    public String getPagina(int numeroDaPagina) {
        for(int i=0; i<this.getQuantPaginas(); i++) {
            if (i == numeroDaPagina - 1)
                return paginas.get(i);
        }
        return "";
    }
}

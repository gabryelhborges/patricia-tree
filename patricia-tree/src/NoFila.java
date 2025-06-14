public class NoFila {
    private No info;
    private int nivel;
    private NoFila proximo;

    public NoFila(No info, int nivel) {
        this.info = info;
        this.nivel = nivel;
        this.proximo = null;
    }

    public No getInfo() {
        return info;
    }

    public int getNivel() {
        return nivel;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}
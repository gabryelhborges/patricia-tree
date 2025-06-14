public class Fila {
    private NoFila inicio;

    public Fila() {}

    public void iniciar() {
        inicio = null;
    }

    public void enfileirar(No info, int nivel) {
        NoFila novo = new NoFila(info, nivel);
        if(inicio == null)
            inicio = novo;
        else {
            NoFila aux = inicio;
            while(aux.getProximo() != null)
                aux = aux.getProximo();
            aux.setProximo(novo);
        }
    }

    public NoFila desenfileirar() {
        if(inicio != null) {
            NoFila aux = inicio;
            inicio = inicio.getProximo();
            return aux;
        }
        return null;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
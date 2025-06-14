public class Pilha {
    private Pilha proximo;
    private No no;

    public void iniciar(){
        this.proximo = null;
        this.no = null;
    }

    public void empilhar(No no){
        Pilha novaPilha = new Pilha();
        novaPilha.no = no;
        novaPilha.proximo = this.proximo;
        this.proximo = novaPilha;
    }

    public No desempilhar(){
        No no = this.proximo.no;
        this.proximo = this.proximo.proximo;
        return no;
    }

    public boolean estaVazia(){
        return this.proximo == null;
    }
}
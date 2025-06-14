public class No {
    private char[] letras;
    private No[] ligacoes;
    private int indice;
    private String palavra;
    private int contadorDeLetras;

    public No(String palavra) {
        this.letras = new char[26];
        this.ligacoes = new No[26];
        this.indice = -1;
        this.palavra = palavra;
        this.contadorDeLetras = 0;
    }

    public No() {
        this.letras = new char[26];
        this.ligacoes = new No[26];
        this.indice = -1;
        this.palavra = "";
        this.contadorDeLetras = 0;
    }

    public int buscarLetra(char letra) {
        int indiceEncontrado = -1;
        int j = 0;
        while (j < contadorDeLetras && indiceEncontrado == -1) {
            if (letras[j] == letra) {
                indiceEncontrado = j;
            }
            j++;
        }
        return indiceEncontrado;
    }

    public void inserirLetra(char letra, No link) {
        int i = contadorDeLetras - 1;
        while (i >= 0 && letras[i] > letra) {
            letras[i + 1] = letras[i];
            ligacoes[i + 1] = ligacoes[i];
            i--;
        }
        letras[i + 1] = letra;
        ligacoes[i + 1] = link;
        contadorDeLetras++;
    }

    public char[] getLetras() {
        return letras;
    }

    public void setLetra(char letra, int pos) {
        this.letras[pos] = letra;
    }

    public No getLigacoes(int pos) {
        return ligacoes[pos];
    }

    public No[] getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(No lig, int pos) {
        this.ligacoes[pos] = lig;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int getContadorDeLetras() {
        return contadorDeLetras;
    }

    public void setContadorDeLetras(int contadorDeLetras) {
        this.contadorDeLetras = contadorDeLetras;
    }
}
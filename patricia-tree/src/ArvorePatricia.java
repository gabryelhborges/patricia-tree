public class ArvorePatricia {
    private No raiz;

    public ArvorePatricia() {
        this.raiz = null;
    }

    public void inserir(String palavra) {
        No novoNo = new No(palavra);

        if (raiz == null) {
            raiz = new No();
            tratarCasoRaizNula(novoNo);
        } else {
            int pos = raiz.buscarLetra(palavra.charAt(0));
            if (pos == -1) {
                raiz.inserirLetra(palavra.charAt(0), novoNo);
            } else {
                inserirNaArvore(novoNo, palavra, pos);
            }
        }
    }

    private void inserirNaArvore(No novoNo, String palavra, int pos) {
        No noAnterior = raiz;
        No noAtual = raiz.getLigacoes(pos);
        int i = 1;

        No noIntermediario = new No();
        boolean inserido = false;
        int posLetra = pos;

        while (noAtual.getLigacoes(0) != null && noAtual.getIndice() <= palavra.length() && !inserido) {
            if (noAtual.getIndice() == i) {
                int letraPos = noAtual.buscarLetra(palavra.charAt(i - 1));
                if (letraPos == -1) {
                    noAtual.inserirLetra(palavra.charAt(i - 1), novoNo);
                    inserido = true;
                } else {
                    noAnterior = noAtual;
                    noAtual = noAtual.getLigacoes(letraPos);
                    i++;
                }

                if (!inserido) {
                    posLetra = noAtual.buscarLetra(palavra.charAt(i - 1));
                }
            } else {
                String filhoParaComparar = buscarFilhoParaComparar(noAtual, palavra, noAtual.getIndice() - 1);
                int diff = encontrarCaractereDiferente(filhoParaComparar, palavra);
                if (diff < noAtual.getIndice() - 1) {
                    inserirNoIntermediario(noAnterior, noAtual, noIntermediario, filhoParaComparar, palavra, diff, posLetra, novoNo);
                    inserido = true;
                } else if (diff >= noAtual.getIndice() - 1) {
                    posLetra = noAtual.buscarLetra(palavra.charAt(noAtual.getIndice() - 1));
                    if (posLetra == -1) {
                        noAtual.inserirLetra(palavra.charAt(noAtual.getIndice() - 1), novoNo);
                        inserido = true;
                    } else {
                        if (noAtual.getLigacoes(0) == null) {
                            dividirPalavra(noAtual, novoNo, posLetra);
                            inserido = true;
                        } else {
                            noAnterior = noAtual;
                            noAtual = noAtual.getLigacoes(posLetra);
                        }
                    }
                }
            }
        }
        finalizarInsercao(novoNo, palavra, noAnterior, noAtual, noIntermediario, inserido);
    }

    private void inserirNoIntermediario(No noAnterior, No noAtual, No noIntermediario, String filhoParaComparar, String palavra, int diferenca, int posLetra, No novoNo) {
        noIntermediario.setIndice(diferenca + 1);
        noIntermediario.inserirLetra(filhoParaComparar.charAt(diferenca), noAtual);
        noIntermediario.inserirLetra(palavra.charAt(diferenca), novoNo);
        noAnterior.setLigacoes(noIntermediario, posLetra);
    }

    private void finalizarInsercao(No novoNo, String palavra, No noAnterior, No noAtual, No noIntermediario, boolean inserido) {
        if (noAtual.getLigacoes(0) == null && !inserido) {
            dividirPalavra(noAnterior, novoNo, noAnterior.buscarLetra(palavra.charAt(noAnterior.getIndice() - 1)));
        } else if (noAtual.getIndice() > palavra.length() && !inserido) {
            noIntermediario.inserirLetra(palavra.charAt(palavra.length() - 1), noAtual);
            noIntermediario.setIndice(palavra.length());
            noAtual.setPalavra(palavra);
            int pos = noAnterior.buscarLetra(palavra.charAt(noAnterior.getIndice() - 1));
            noAnterior.setLigacoes(noIntermediario, pos);
            noIntermediario.setLigacoes(noAtual, noIntermediario.buscarLetra(palavra.charAt(palavra.length() - 1)));
        }
    }

    private String buscarFilhoParaComparar(No no, String palavra, int j) {
        if (j < palavra.length()) {
            char origem = palavra.charAt(j);
            while (no.getLigacoes(0) != null && no.buscarLetra(origem) >= 0) {
                no = no.getLigacoes(no.buscarLetra(origem));
                origem = palavra.charAt(j);
                j++;
            }
        }
        while (no.getLigacoes(0) != null) {
            no = no.getLigacoes(0);
        }
        return no.getPalavra();
    }

    private void dividirPalavra(No no, No novoNo, int pos) {
        No noAtual = no.getLigacoes(pos);
        int posDiferente = encontrarCaractereDiferente(novoNo.getPalavra(), noAtual.getPalavra());
        No noIntermediario = new No();

        if (posDiferente == novoNo.getPalavra().length()) {
            noIntermediario.setPalavra(novoNo.getPalavra());
            noIntermediario.inserirLetra(noAtual.getPalavra().charAt(posDiferente), noAtual);
        } else if (posDiferente == noAtual.getPalavra().length()) {
            noIntermediario.setPalavra(noAtual.getPalavra());
            noIntermediario.inserirLetra(novoNo.getPalavra().charAt(posDiferente), novoNo);
        } else {
            noIntermediario.inserirLetra(novoNo.getPalavra().charAt(posDiferente), novoNo);
            noIntermediario.inserirLetra(noAtual.getPalavra().charAt(posDiferente), noAtual);
        }
        noIntermediario.setIndice(posDiferente + 1);
        no.setLigacoes(noIntermediario, pos);
    }

    private int encontrarCaractereDiferente(String primeiraPalavra, String segundaPalavra) {
        int k = 0;
        while (k < primeiraPalavra.length() && k < segundaPalavra.length() && primeiraPalavra.charAt(k) == segundaPalavra.charAt(k))
            k++;
        return k;
    }

    private void tratarCasoRaizNula(No novoNo) {
        raiz.setLigacoes(novoNo, 0);
        raiz.setIndice(1);
        raiz.setLetra(novoNo.getPalavra().charAt(0), 0);
        raiz.setContadorDeLetras(1);
    }

    public boolean buscarPalavra(String palavra) {
        Pilha pilha = new Pilha();
        pilha.iniciar();
        pilha.empilhar(raiz);
        boolean encontrada = false;

        while (!pilha.estaVazia() && !encontrada) {
            No noAtual = pilha.desempilhar();
            if (noAtual != null) {
                if (noAtual.getPalavra().equals(palavra)) {
                    encontrada = true;
                } else {
                    for (int i = 0; i < noAtual.getContadorDeLetras(); i++) {
                        pilha.empilhar(noAtual.getLigacoes(i));
                    }
                }
            }
        }
        return encontrada;
    }

    public void exibirTodasAsPalavras() {
        Pilha pilha = new Pilha();
        pilha.iniciar();
        pilha.empilhar(raiz);

        while (!pilha.estaVazia()) {
            No noAtual = pilha.desempilhar();
            if (noAtual != null && !noAtual.getPalavra().isEmpty()) {
                System.out.println(noAtual.getPalavra());
            }
            if (noAtual != null) {
                for (No filho : noAtual.getLigacoes()) {
                    if (filho != null) {
                        pilha.empilhar(filho);
                    }
                }
            }
        }
    }

    public void exibirNosNivelPorNivel() {
        Fila fila = new Fila();
        fila.iniciar();
        fila.enfileirar(raiz, 0);

        while (!fila.estaVazia()) {
            NoFila noFila = fila.desenfileirar();
            No noAtual = noFila.getInfo();
            int nivel = noFila.getNivel();

            System.out.println("Nível " + nivel + ": " + construirStringDeLetras(noAtual.getLetras()) + " Palavra: " + noAtual.getPalavra() + " Índice: " + noAtual.getIndice());

            for (No filho : noAtual.getLigacoes()) {
                if (filho != null) {
                    fila.enfileirar(filho, nivel + 1);
                }
            }
        }
    }

    private String construirStringDeLetras(char[] letras) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (char letra : letras) {
            if (letra != '\u0000') {
                sb.append(letra).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
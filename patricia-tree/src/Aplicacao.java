public class Aplicacao {

    public static void main(String[] args) {
        ArvorePatricia arvore = new ArvorePatricia();

        System.out.println("Trabalho desenvolvido por:\nGabryel Borges -- RA: 102215626\nLeon Ronchi -- RA: 102215340");

        String[] palavras = {
                "casa", "casamento", "bola", "bolo", "dado", "dedo",
                "faca", "foca", "gato", "galo", "lua", "luz", "marte",
                "marta", "navio", "nadar", "porta", "porto", "sol", "solda",
                "soldado", "porteiro", "gaita", "abacaxi", "zebra",
                "carro", "carreta", "dente", "dedal", "porte"
        };

        String[] palavrasNaoInseridas = {
                "casar", "bolacha", "navegar", "historia", "arvore", "patricia", "tree"
        };

        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            arvore.inserir(palavra);
        }

        System.out.println("------------------------ Palavras Inseridas ------------------------");
        arvore.exibirTodasAsPalavras();

        System.out.println("\n------------------------ Buscando Palavras Inseridas ------------------------");
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            String resultado = arvore.buscarPalavra(palavra) ? "Encontrado" : "Não encontrado";
            System.out.println("Busca de: '" + palavra + "' --> " + resultado);
        }

        System.out.println("\n------------------------ Buscando Palavras Não Inseridas ------------------------");
        for (int i = 0; i < palavrasNaoInseridas.length; i++) {
            String palavra = palavrasNaoInseridas[i];
            String resultado = arvore.buscarPalavra(palavra) ? "Encontrado" : "Não encontrado";
            System.out.println("Busca de: '" + palavra + "' --> " + resultado);
        }

        System.out.println("\n------------------------ Exibindo Estrutura da Árvore (Nível a Nível) ------------------------");
        arvore.exibirNosNivelPorNivel();
    }
}
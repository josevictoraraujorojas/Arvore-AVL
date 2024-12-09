import java.io.IOException;

public class TestaArvoreAvl {
    /*
    Alunos: José Victor Araújo Rojas, Leonan Damasceno e Maysa Santos
    Disciplina: Estrutura de Dados 2
    Data de Codificação: 08/12/2024
    Objetivo da classe: A classe TestaArvoreAvl é responsável por realizar testes de desempenho em uma estrutura de dados do tipo
    Árvore AVL. Ela avalia as operações de inserção, busca e exclusão, além de medir o tempo de criação da árvore com dados
    provenientes de arquivos.

    Funcionalidades principais:
    - Criar árvores AVL com base em arquivos contendo dados de clientes.
    - Medir o tempo de execução para criação das árvores.
    - Avaliar o desempenho das operações de inserção, busca e exclusão de clientes na árvore.
    - Repetir os testes várias vezes para calcular médias mais precisas.
    - Exibir os resultados para diferentes tamanhos de arquivos (100, 1000 e 10000 registros).
*/
    public static void main(String[] args) throws IOException {
        // Definindo os caminhos dos arquivos
        String[] caminhosArquivos = {
                "C:\\Users\\Jose\\IdeaProjects\\Arvore-AVL\\src\\registros_clientes_100.txt",
                "C:\\Users\\Jose\\IdeaProjects\\Arvore-AVL\\src\\registros_clientes_1000.txt",
                "C:\\Users\\Jose\\IdeaProjects\\Arvore-AVL\\src\\registros_clientes_10000.txt"
        };

        int repeticoes = 30; // Número de repetições para média

        for (String caminho : caminhosArquivos) {
            System.out.println("Testando com arquivo: " + caminho);

            // Criar árvore e medir tempo de criação
            long inicioCriarArvore = System.nanoTime();
            ArvoreAvl arvore = new ArvoreAvl(caminho);
            long fimCriarArvore = System.nanoTime();
            System.out.println("Tempo de criação da árvore: " + (fimCriarArvore - inicioCriarArvore) + " ns");

            // Teste de inserção, busca e exclusão
            realizarTestes(arvore, repeticoes);
            System.out.println();
        }
    }

    public static void realizarTestes(ArvoreAvl arvore, int repeticoes) throws IOException {
        long somaTempoInsercao = 0, somaTempoBusca = 0, somaTempoExclusao = 0;
        Cliente clienteTeste = new Cliente(1, "João Silva", "01/01/1990", "123456789", "Rua A");

        for (int i = 0; i < repeticoes; i++) {
            // Medindo tempo de inserção
            long inicioInsercao = System.nanoTime();
            arvore.inserir(clienteTeste, false);
            long fimInsercao = System.nanoTime();
            somaTempoInsercao += (fimInsercao - inicioInsercao);

            // Medindo tempo de busca
            long inicioBusca = System.nanoTime();
            arvore.busca(1);
            long fimBusca = System.nanoTime();
            somaTempoBusca += (fimBusca - inicioBusca);

            // Medindo tempo de exclusão
            long inicioExclusao = System.nanoTime();
            arvore.retirar(1, false);
            long fimExclusao = System.nanoTime();
            somaTempoExclusao += (fimExclusao - inicioExclusao);

            // Reinserir cliente para próximas medições
            arvore.inserir(clienteTeste, false);
        }

        // Calculando médias
        long mediaTempoInsercao = somaTempoInsercao / repeticoes;
        long mediaTempoBusca = somaTempoBusca / repeticoes;
        long mediaTempoExclusao = somaTempoExclusao / repeticoes;

        // Exibindo resultados
        System.out.println("Tempo médio de inserção: " + mediaTempoInsercao + " ns");
        System.out.println("Tempo médio de busca: " + mediaTempoBusca + " ns");
        System.out.println("Tempo médio de exclusão: " + mediaTempoExclusao + " ns");
    }
}
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo txt dos seus clientes:");
        String caminho = ler.nextLine();
        ArvoreAvl arvore = new ArvoreAvl(caminho);
        System.out.println("arvore criada com sucesso");
        arvore.imprimiArvoreOrdem();

        String resposta = "";

        do {
            resposta="";
            System.out.println("""
                    Digite como vc quer manipular sua base de clientes:
                    (1) Adicionar um novo cliente
                    (2) Remover um cliente
                    (3) Mostrar todos os clientes
                    (4) Buscar cliente
                    (5) Sair do programa
                    """);
            resposta = ler.nextLine();

            switch (resposta){
                case "1"-> {
                    Cliente novoCliente = new Cliente();

                    try {

                        System.out.println("Digite o codigo do cliente:");
                        novoCliente.setCodigo(Integer.parseInt(ler.nextLine()));
                        System.out.println("Digite o nome do cliente:");
                        novoCliente.setNome(ler.nextLine());
                        System.out.println("Digite a data de nascimento do cliente:");
                        novoCliente.setData(ler.nextLine());
                        System.out.println("Digite o telefone do cliente:");
                        novoCliente.setTelefone(ler.nextLine());
                        System.out.println("Digite o endereço do cliente:");
                        novoCliente.setEndereco(ler.nextLine());

                        arvore.inserir(novoCliente, true);

                        System.out.println("Cliente adicionado com sucesso");

                    }catch (Exception e){
                        System.out.println("falha ao adicionar um novo cliente");
                    }

                }

                case "2" ->{
                    try {
                        System.out.println("Digite o codigo do cliente:");
                        int codigo = Integer.parseInt(ler.nextLine());
                        arvore.retirar(codigo);
                        System.out.println("Cliente removido com sucesso");
                    }catch (Exception e){
                        System.out.println("falha ao remover o cliente");
                    }
                }
                case "3" ->{
                    try {
                        arvore.imprimiArvoreOrdem();

                    }catch (Exception e){
                        System.out.println("falha ao mostrar todos os clientes");
                    }

                }
                case "4" ->{
                    try {
                        System.out.println("Digite o codigo do cliente:");
                        int codigo = Integer.parseInt(ler.nextLine());
                        Cliente cliente= (Cliente) arvore.busca(codigo);
                        if (cliente != null){
                            System.out.println(cliente);
                        }else {
                            System.out.println("Cliente não encontrado");
                        }

                    }catch (Exception e){
                        System.out.println("falha ao mostrar todos os clientes");
                    }
                }
                case "5" ->{
                    System.out.println("Saindo do programa");
                }

            }
        }while (!resposta.equals("5"));
    }

}

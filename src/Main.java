
public class Main {
    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();
        Cliente cliente = new Cliente(10,"jose","24","34618262","rua 2");
        Cliente cliente1 = new Cliente(11,"joao","24","34618262","rua 2");
        Cliente cliente2 = new Cliente(12,"dany","24","34618262","rua 2");

        arvore.inserir(cliente);
        arvore.inserir(cliente1);
        arvore.inserir(cliente2);

        Cliente cli = (Cliente) arvore.busca(11);

        System.out.println(cli.nome);


        System.out.println("-------------------------------------");
        arvore.buscaLargura();
    }

}

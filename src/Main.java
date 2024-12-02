import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArvoreAvl arvore = new ArvoreAvl("D:\\Usuario\\Documents\\teste.txt");





//        Cliente cliente1 = new Cliente(11,"joao","24","34618262","rua 2");
//        Cliente cliente2 = new Cliente(12,"dany","24","34618262","rua 2");
//        Cliente cliente3 = new Cliente(1,"dany","24","34618262","rua 2");
//        Cliente cliente4 = new Cliente(2,"dany","24","34618262","rua 2");
//        Cliente cliente5 = new Cliente(9,"dany","24","34618262","rua 2");
////
//

        arvore.imprimiArvoreOrdem();
        arvore.retirar(12);
        System.out.println("______________________________________");
        arvore.imprimiArvoreOrdem();
//        arvore.inserir(cliente1);
//        arvore.inserir(cliente2);
//        arvore.inserir(cliente3);
//        arvore.inserir(cliente4);
//        arvore.inserir(cliente5);





//        Cliente cli = (Cliente) arvore.busca(11);
//
//        System.out.println(cli.nome);
//
//
//        System.out.println("-------------------------------------");
//        arvore.buscaLargura();
    }

}

public class Cliente {
    /*
    Alunos: José Victor Araújo Rojas, Leonan Damasceno e Maysa Santos
    Disciplina: Estrutura de Dados 2
    Data de Codificação: 08/12/2024
    Objetivo da classe: A classe Cliente representa um modelo para armazenar e manipular as informações dos clientes
    em um sistema. Cada cliente possui os seguintes atributos:
    - código: Identificador único do cliente (int).
    - nome: Nome completo do cliente (String).
    - data: Data de nascimento do cliente no formato "dd/mm/aaaa" (String).
    - telefone: Número de telefone do cliente (String).
    - endereço: Endereço residencial do cliente (String).
    A classe oferece métodos para:
    - Construir objetos Cliente com ou sem dados iniciais.
    - Acessar e modificar os atributos individuais por meio de getters e setters.
    - Representar o cliente como uma String formatada, por meio do método toString(),
     para facilitar a exibição das informações.
    Essa classe é essencial para o gerenciamento de dados no sistema baseado em Árvore AVL,
    pois permite armazenar e manipular os dados de forma estruturada e padronizada.
     */

int codigo;
String nome;
String data;
String telefone;
String endereco;

    public Cliente(int codigo, String nome, String data, String telefone, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    public Cliente() {}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", data='" + data + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}

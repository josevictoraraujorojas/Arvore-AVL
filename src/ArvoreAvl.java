
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAvl {
    public class Nodo{
        Cliente cliente;
        Nodo pai;
        Nodo esquerda;
        Nodo direita;
        int fatorBalanceamento;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
        }

        public Object getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public Nodo getEsquerda() {
            return esquerda;
        }

        public void setEsquerda(Nodo esquerda) {
            this.esquerda = esquerda;
        }

        public Nodo getDireita() {
            return direita;
        }

        public void setDireita(Nodo direita) {
            this.direita = direita;
        }

        public Nodo getPai() {
            return pai;
        }

        public void setPai(Nodo pai) {
            this.pai = pai;
        }

        public int getFatorBalanceamento() {
            return fatorBalanceamento;
        }

        public void setFatorBalanceamento(int fatorBalanceamento) {
            this.fatorBalanceamento = fatorBalanceamento;
        }
    }
    private Nodo raiz;
    public ArvoreAvl() {
        this.raiz = null;
    }

    public void inserir(Cliente cliente) {
        this.raiz = insere(cliente,null ,raiz);
    }

    private Nodo insere(Cliente cliente,Nodo pai, Nodo p) {

        if (p == null) {
            // Cria um novo Nodo e o retorna
            Nodo novo = new Nodo(cliente);
            novo.setPai(pai);
            novo.setEsquerda(null);
            novo.setDireita(null);
            return novo;
        } else if ((int)cliente.getCodigo() <(int) p.cliente.getCodigo()) {
            // Insere na subárvore esquerda

            p.esquerda = insere(cliente, p,p.esquerda);


        } else if ((int)cliente.getCodigo() >(int) p.cliente.getCodigo()) {
            // Insere na subárvore direita

            p.direita = insere(cliente, p,p.direita);


        }

        if(p!=null){
            defineFB(p);
            p = balanceia(p);
        }

        // Retorna o nó atual para que as referências dos filhos possam ser atualizadas corretamente
        return p;
    }

    public void retirar(int codigo) {
        this.raiz = retirar(codigo, raiz);
    }

    private Nodo retirar(int codigo, Nodo p) {
        if (p == null) {
            return null; // Nó não encontrado.
        }

        if (codigo < (int) p.cliente.getCodigo()) {
            // O item está na subárvore esquerda.
            p.esquerda = retirar(codigo, p.esquerda);
        } else if (codigo > (int) p.cliente.getCodigo()) {
            // O item está na subárvore direita.
            p.direita = retirar(codigo, p.direita);
        } else {
            // Nó encontrado.
            if (p.esquerda == null && p.direita == null) {
                // Caso 1: Nó sem filhos.
                return null;
            } else if (p.esquerda == null) {
                // Caso 2: Nó com um filho à direita.
                return p.direita;
            } else if (p.direita == null) {
                // Caso 2: Nó com um filho à esquerda.
                return p.esquerda;
            } else {
                // Caso 3: Nó com dois filhos.
                Nodo antecessor = encontrarAntecessor(p.esquerda);
                p.cliente = antecessor.cliente; // Substitui pelo antecessor.
                p.esquerda = retirar((int) antecessor.cliente.getCodigo(), p.esquerda);
            }
        }

        // Atualiza o fator de balanceamento e aplica o balanceamento.
        if (p != null) {
            defineFB(p);
            p = balanceia(p);
        }
        return p;
    }
    private Nodo encontrarAntecessor(Nodo atual) {
        // O antecessor é o maior valor na subárvore esquerda.
        while (atual.direita != null) {
            atual = atual.direita;
        }
        return atual; // Retorna o maior valor.
    }


    public void imprimiArvoreOrdem(){
        imprimiArvoreOrdem(this.raiz);
    }
    private void imprimiArvoreOrdem(Nodo p) {
        if (p == null) {
            return;
        }
        imprimiArvoreOrdem(p.esquerda); // Visita a subárvore esquerda
        System.out.println("valor item:"+p.cliente.getNome() + " fb:"+p.fatorBalanceamento); // Imprime o item do nó atual
        imprimiArvoreOrdem(p.direita); // Visita a subárvore direita
    }

    public void imprimiArvorePosfixa(){
        imprimiArvorePosfixa(this.raiz);
    }

    private void imprimiArvorePosfixa(Nodo p) {
        if (p == null) {
            return;
        }
        imprimiArvorePosfixa(p.esquerda); // Visita a subárvore esquerda
        imprimiArvorePosfixa(p.direita); // Visita a subárvore direita
        System.out.println(p.cliente.getCodigo()); // Imprime o item do nó atual
    }

    public Object busca(int codigo){
        return  busca(codigo, this.raiz);
    }
    private Object busca(int codigo, Nodo p) {
        if (p == null) {
            return null; // Item não encontrado
        } else if ((int)codigo <(int) p.cliente.getCodigo()) {
            // Item é menor, buscar na subárvore esquerda
            return busca(codigo, p.esquerda);
        } else if ((int)codigo >(int) p.cliente.getCodigo()) {
            // Item é maior, buscar na subárvore direita
            return busca(codigo, p.direita);
        } else {
            // Item encontrado
            return p.cliente;
        }
    }

    public void buscaLargura() {
        if (raiz == null) return;

        Queue<Nodo> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            Nodo n = fila.poll();
            System.out.print(n.cliente.getCodigo()+ " ");

            if (n.esquerda != null) {
                fila.add(n.esquerda);
            }
            if (n.direita != null) {
                fila.add(n.direita);
            }
        }
        System.out.println();
    }

    public int altura(Nodo atual) {//Verifica a altura de um determinado nó
        if (atual == null) {//Se o nó for nulo, sua altura será 0

            return 0;
        }
        if (atual.getDireita() == null && atual.getEsquerda() == null) {//Se ele não tiver nenhum filho, sua altura será 1
            return 1;
        } else if (atual.getEsquerda() == null) {//Se o nó tiver apenas um filho, sua altura será 1 + a altura do nó filho
            System.out.println(atual.cliente.nome);
            return 1 + altura(atual.getDireita());
        } else if (atual.getDireita() == null) { //Mesma do passo anterior aqui
            return 1 + altura(atual.getEsquerda());
        } else { //Se ele tiver dois filhos, temos q verificar qual filho é mais "alto"
            if (altura(atual.getEsquerda()) > altura(atual.getDireita())) {//a altura do nó será a soma de 1+ a altura do filho mais alto
                return 1 + altura(atual.getEsquerda());
            } else {
                return 1 + altura(atual.getDireita());
            }
        }//e assim recursivamente, até chegar nas folhas q não terão filhos, a altura será 0 e assim a recursão para.
    }

    public void defineFB(Nodo atual) {//Define o valor de balanceamento de cada nó com base na altura (adicionei um atributo pra armazenar o balanceamento na classe nó)
        atual.setFatorBalanceamento(altura(atual.getEsquerda()) - altura(atual.getDireita()));//O valor do balanceamendo será a altura do filho da esquerda menos o da direita
        if (atual.getDireita() != null) {//verifica todos os nós
            defineFB(atual.getDireita());
        }
        if (atual.getEsquerda() != null) {//verifica todos os nós
            defineFB(atual.getEsquerda());
        }
    }

    public Nodo rotacaoADireita(Nodo atual) {
        Nodo aux = atual.getEsquerda(); //Armazena o valor do nó da esquerda do atual
        aux.setPai(atual.getPai());
        if (aux.getDireita() != null) {//tratamento para quando a árvore é degenerada
            aux.getDireita().setPai(atual);
        }
        atual.setPai(aux);
        atual.setEsquerda(aux.getDireita());//Joga o valor da direita do nó da esquerda do atual, na esquerda do atual
        aux.setDireita(atual);//troca o valor da direita do nó da esquerda pelo atual
        if (aux.getPai() != null) {//Se aux não for a raiz principal, configura o pai pra apontar pro novo nó
            if (aux.getPai().getDireita() == atual) {
                aux.getPai().setDireita(aux);
            } else if (aux.getPai().getEsquerda() == atual) {
                aux.getPai().setEsquerda(aux);
            }
        }
        defineFB(aux);//atualiza o valor do balanceamento
        return aux; //retorna o valor do nó da esquerda q é o novo pai
    }

    //mesma coisa do rotação a direita, só que invertido pra esquerda
    public Nodo rotacaoAEsquerda(Nodo atual) {
        Nodo aux = atual.getDireita();
        aux.setPai(atual.getPai());
        if (aux.getEsquerda() != null) {//tratamento para quando a árvore é degenerada
            aux.getEsquerda().setPai(atual);
        }

        atual.setPai(aux);
        atual.setDireita(aux.getEsquerda());
        aux.setEsquerda(atual);
        if (aux.getPai() != null) {
            if (aux.getPai().getDireita() == atual) {
                aux.getPai().setDireita(aux);
            } else if (aux.getPai().getEsquerda() == atual) {
                aux.getPai().setEsquerda(aux);
            }
        }
        defineFB(aux);//atualiza o valor do balanceamento
        return aux;
    }

    public Nodo rotacaoDuplaDireita(Nodo atual) {
        Nodo aux = atual.getEsquerda();//Armazena o valor do filho da esquerda
        atual.setEsquerda(rotacaoAEsquerda(aux));//faz uma rotação para a esquerda no filho da esquerda
        Nodo aux2 = rotacaoADireita(atual); //Faz uma rotação para a direita no atual/pai com o filho da esquerda já rodado
        return aux2; //retorna o nó q será o novo pai com seus filhos
    }

    //mesma coisa do de rotação dupla pra direita, só que invertido pra esquerda
    public Nodo rotacaoDuplaEsquerda(Nodo atual) {
        Nodo aux = atual.getDireita();
        atual.setDireita(rotacaoADireita(aux));
        Nodo aux2 = rotacaoAEsquerda(atual);
        return aux2;
    }

    public Nodo balanceia(Nodo atual) {
        // Caso 1: O nó está desbalanceado para a esquerda (FB = 2)
        // E o filho esquerdo do nó também está balanceado ou ligeiramente desbalanceado para a esquerda (FB >= 0)
        if (atual.getFatorBalanceamento() == 2 && atual.getEsquerda().getFatorBalanceamento() >= 0) {
            // Realiza uma rotação simples à direita para balancear o nó
            atual = rotacaoADireita(atual);
        }
        // Caso 2: O nó está desbalanceado para a direita (FB = -2)
        // E o filho direito do nó também está balanceado ou ligeiramente desbalanceado para a direita (FB <= 0)
        else if (atual.getFatorBalanceamento() == -2 && atual.getDireita().getFatorBalanceamento() <= 0) {
            // Realiza uma rotação simples à esquerda para balancear o nó
            atual = rotacaoAEsquerda(atual);
        }
        // Caso 3: O nó está desbalanceado para a esquerda (FB = 2)
        // E o filho esquerdo está desbalanceado para a direita (FB < 0), o que exige uma rotação dupla
        else if (atual.getFatorBalanceamento() == 2 && atual.getEsquerda().getFatorBalanceamento() < 0) {
            // Realiza uma rotação dupla à direita (primeiro à esquerda, depois à direita) para balancear o nó
            atual = rotacaoDuplaDireita(atual);
        }
        // Caso 4: O nó está desbalanceado para a direita (FB = -2)
        // E o filho direito está desbalanceado para a esquerda (FB > 0), o que exige uma rotação dupla
        else if (atual.getFatorBalanceamento() == -2 && atual.getDireita().getFatorBalanceamento() > 0) {
            // Realiza uma rotação dupla à esquerda (primeiro à direita, depois à esquerda) para balancear o nó
            atual = rotacaoDuplaEsquerda(atual);
        }

        // Após ajustar o nó atual, verificamos os filhos (recursivamente) para garantir que toda a árvore esteja balanceada
        // Se o nó atual tiver um filho à direita, balanceamos recursivamente a subárvore à direita
        if (atual.getDireita() != null) {
            atual.setDireita(balanceia(atual.getDireita()));
        }

        // Se o nó atual tiver um filho à esquerda, balanceamos recursivamente a subárvore à esquerda
        if (atual.getEsquerda() != null) {
            atual.setEsquerda(balanceia(atual.getEsquerda()));
        }

        // Retorna o nó atual balanceado
        return atual;
    }


    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

}

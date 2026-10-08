public class Cliente implements Observador {

    private final String nome;
    private final Produto produto;
    private double ultimoPrecoVisto;
    private int quantidadeNotificacoes;

    public Cliente(String nome, Produto produto){
        this.nome = nome;
        this.produto = produto;
        this.ultimoPrecoVisto = produto.getPreco();
    }

    @Override
    public void atualizar(){
        this.ultimoPrecoVisto = produto.getPreco();
        this.quantidadeNotificacoes++;
    }

    public String getNome(){
        return this.nome;
    }

    public double getUltimoPrecoVisto(){
        return this.ultimoPrecoVisto;
    }

    public int getQuantidadeNotificacoes(){
        return this.quantidadeNotificacoes;
    }

}

import java.util.Locale;

public class PainelDePrecos implements Observador {

    private final Produto produto;
    private String textoExibido;

    public PainelDePrecos(Produto produto){
        this.produto = produto;
        atualizar();
    }

    @Override
    public void atualizar(){
        this.textoExibido = String.format(Locale.forLanguageTag("pt-BR"),
                "%s: R$ %.2f", produto.getNome(), produto.getPreco());
    }

    public String getTextoExibido(){
        return this.textoExibido;
    }

}

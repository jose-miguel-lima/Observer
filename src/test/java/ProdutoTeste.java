import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoTeste {

    Produto produto;
    Cliente cliente;
    PainelDePrecos painel;

    @BeforeEach
    public void setUp(){
        produto = new Produto("Notebook", 3500.00);
        cliente = new Cliente("Maria", produto);
        painel = new PainelDePrecos(produto);
    }

    // Cadastro de observadores

    @Test
    public void produtoNovoNaoDeveTerObservadores(){
        assertEquals(0, produto.getQuantidadeObservadores());
    }

    @Test
    public void deveAdicionarObservador(){
        produto.adicionarObservador(cliente);
        assertEquals(1, produto.getQuantidadeObservadores());
    }

    @Test
    public void deveAdicionarVariosObservadores(){
        produto.adicionarObservador(cliente);
        produto.adicionarObservador(painel);
        assertEquals(2, produto.getQuantidadeObservadores());
    }

    @Test
    public void naoDeveAdicionarMesmoObservadorDuasVezes(){
        produto.adicionarObservador(cliente);
        produto.adicionarObservador(cliente);
        assertEquals(1, produto.getQuantidadeObservadores());
    }

    @Test
    public void naoDeveAdicionarObservadorNulo(){
        produto.adicionarObservador(null);
        assertEquals(0, produto.getQuantidadeObservadores());
    }

    @Test
    public void deveRemoverObservador(){
        produto.adicionarObservador(cliente);
        produto.removerObservador(cliente);
        assertEquals(0, produto.getQuantidadeObservadores());
    }

    @Test
    public void naoDeveFalharAoRemoverObservadorNaoCadastrado(){
        assertDoesNotThrow(() -> produto.removerObservador(cliente));
        assertEquals(0, produto.getQuantidadeObservadores());
    }

    // Notificações

    @Test
    public void naoDeveFalharAoMudarPrecoSemObservadores(){
        assertDoesNotThrow(() -> produto.setPreco(3000.00));
        assertEquals(3000.00, produto.getPreco());
    }

    @Test
    public void deveNotificarObservadorQuandoPrecoMudar(){
        produto.adicionarObservador(cliente);
        produto.setPreco(3000.00);
        assertEquals(1, cliente.getQuantidadeNotificacoes());
    }

    @Test
    public void deveNotificarObservadorACadaMudanca(){
        produto.adicionarObservador(cliente);
        produto.setPreco(3000.00);
        produto.setPreco(2800.00);
        assertEquals(2, cliente.getQuantidadeNotificacoes());
    }

    @Test
    public void deveNotificarTodosOsObservadores(){
        produto.adicionarObservador(cliente);
        produto.adicionarObservador(painel);
        produto.setPreco(3000.00);
        assertEquals(1, cliente.getQuantidadeNotificacoes());
        assertEquals("Notebook: R$ 3000,00", painel.getTextoExibido());
    }

    @Test
    public void observadorDeveConsultarONovoPrecoAoSerNotificado(){
        produto.adicionarObservador(cliente);
        produto.setPreco(2999.90);
        assertEquals(2999.90, cliente.getUltimoPrecoVisto());
    }

    @Test
    public void observadorNaoCadastradoNaoDeveSerNotificado(){
        produto.setPreco(3000.00);
        assertEquals(0, cliente.getQuantidadeNotificacoes());
        assertEquals(3500.00, cliente.getUltimoPrecoVisto());
        assertEquals("Notebook: R$ 3500,00", painel.getTextoExibido());
    }

    @Test
    public void observadorRemovidoNaoDeveMaisSerNotificado(){
        produto.adicionarObservador(cliente);
        produto.setPreco(3000.00);
        produto.removerObservador(cliente);
        produto.setPreco(2500.00);
        assertEquals(1, cliente.getQuantidadeNotificacoes());
        assertEquals(3000.00, cliente.getUltimoPrecoVisto());
    }

    @Test
    public void removerUmObservadorNaoDeveAfetarOsOutros(){
        produto.adicionarObservador(cliente);
        produto.adicionarObservador(painel);
        produto.removerObservador(cliente);
        produto.setPreco(2500.00);
        assertEquals(0, cliente.getQuantidadeNotificacoes());
        assertEquals("Notebook: R$ 2500,00", painel.getTextoExibido());
    }


    // Painel

    @Test
    public void painelDeveIniciarComOPrecoAtual(){
        assertEquals("Notebook: R$ 3500,00", painel.getTextoExibido());
    }
}

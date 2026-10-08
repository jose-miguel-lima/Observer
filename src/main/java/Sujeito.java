import java.util.ArrayList;
import java.util.List;

public abstract class Sujeito {

    private final List<Observador> observadores = new ArrayList<>();

    public void adicionarObservador(Observador observador){
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void removerObservador(Observador observador){
        observadores.remove(observador);
    }

    public void notificarObservadores(){
        // Itera sobre uma cópia: um observador pode se remover durante a notificação
        for (Observador observador : new ArrayList<>(observadores)) {
            observador.atualizar();
        }
    }

    public int getQuantidadeObservadores(){
        return observadores.size();
    }

}

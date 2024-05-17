package nascimento.garcia.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    // guarda a lista de itens cadastrados
    List<MyItem> itens = new ArrayList<>();
    //obtem a lista de itens
    public List<MyItem> getItens() {
        return itens;
    }
}

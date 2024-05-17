package nascimento.garcia.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    //guarda  o endereço URI da foto que foi escolhida pelo usuario
    Uri selectPhotoLocation = null;
    //obtem a lista de itens
    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }
    //seta o endereço URI dentro do ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}

package nascimento.garcia.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nascimento.garcia.lista.R;
import nascimento.garcia.lista.activity.MainActivity;
import nascimento.garcia.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;
    public MyAdapter(MainActivity mainActivity, List<MyItem>
            itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //obtem um inflador de layouts que será usado para ler o arquivo xml de layout do item
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        // cria os elementos de interface referentes a um item e os guarda dentro de um objeto do tipo View
        View v = inflater.inflate(R.layout.item_list, parent, false);
        //guarda o objeto view dentro de um objeto do tipo MyViewHolder, que é retornado pela função
        return new MyViewHolder(v);
    }
    @Override

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // obtem o item que sera usado para preencher a UI
        MyItem myItem = itens.get(position);
        //obtem o objeto do tipo View que esta guardado dentro do ViewHolder
        View v = holder.itemView;
        //preenche a UI com os dados do item
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.photo);
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);
        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);


    }
    @Override
    public int getItemCount() {
        return itens.size();
    }
}
package nascimento.garcia.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import nascimento.garcia.lista.R;
import nascimento.garcia.lista.adapter.MyAdapter;
import nascimento.garcia.lista.adapter.MyViewHolder;
import nascimento.garcia.lista.model.MainActivityViewModel;
import nascimento.garcia.lista.model.MyItem;
import nascimento.garcia.lista.util.Util;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1;


    MyAdapter myAdapter;
    ArrayList itens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Obtem o fabbutton atraves do id
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //Registra o ouvidor de click
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Realiza a navegacao de uma tela para outra atraves do uso de um Intent explicito
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                //Executa o intent
                // Assume que a activity destino ira retornar com dados em algum momento para
                // a activity que iniciou a navegação
                startActivityForResult(i, NEW_ITEM_REQUEST);

            }


        });
        //Obtem o RecycleView
        RecyclerView rvItens = findViewById(R.id.rvItens);
        //obtem o ViewModel referente a MainActivity
        MainActivityViewModel vm = new ViewModelProvider( this ).get(MainActivityViewModel.class );
        //obtem a lista a partir do ViewModel e repassada para o Adapter
        List<MyItem> itens = vm.getItens();
        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);
        //o método  indica ao RecycleView que nao ha variacao de tamanho entre os itens da lista
        rvItens.setHasFixedSize(true);
        //cria um gerenciador de layout do tipo linear e o seta no RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        //cria um decorador para a lista e o seta no RecycleView
        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(rvItens.getContext(),
                DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se as condicoes de retorno foram cumpridas
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                //guarda os dados do item
                MyItem myItem = new MyItem();
                //obtem os dados retornados por NewItemActivity e os guarda dentro de myItem
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();
                try {
            //carrega a imagem e a guarda dentro de um Bitmap
            Bitmap photo = Util.getBitmap( MainActivity.this, selectedPhotoURI, 100, 100 );
            //guarda o Bitmap da imagem dentro de um objeto do tipo MyItem
                     myItem.photo = photo;
            //a exceção é disparada caso o arquivo de imagem não seja encontrado
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //obtem o ViewMode
                MainActivityViewModel vm = new ViewModelProvider( this ).get(MainActivityViewModel.class );
                //obtem em seguida a lista de itens guardada pelo  ViewModel
                List<MyItem> itens = vm.getItens();
                //guarda o novo item dentro dessa lista
                itens.add(myItem);
                //notifica o Adapter
                myAdapter.notifyItemInserted(itens.size() - 1);
            }


        }
    }
}
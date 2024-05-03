package nascimento.garcia.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import nascimento.garcia.lista.R;
import nascimento.garcia.lista.adapter.MyAdapter;
import nascimento.garcia.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();
    MyAdapter myAdapter;

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
                //Realiza a navegação de uma tela para outra através do uso de um Intent explícito
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                //Executa o intent
                startActivityForResult(i, NEW_ITEM_REQUEST);

            }


        });
        RecyclerView rvItens = findViewById(R.id.rvItens);
        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);
        rvItens.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(rvItens.getContext(),
                DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description =
                        data.getStringExtra("description");
                myItem.photo = data.getData();
                itens.add(myItem);
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size() - 1);
            }


        }
    }
}
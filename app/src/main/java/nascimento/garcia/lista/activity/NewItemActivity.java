package nascimento.garcia.lista.activity;

import static nascimento.garcia.lista.activity.MainActivity.NEW_ITEM_REQUEST;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nascimento.garcia.lista.R;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;
    //guarda o endereco da foto selecionada pelo usuario
    Uri photoSelected= null;

    @Override
    //Cria a interface
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        ImageButton imbCl = findViewById(R.id.imbCl);
        //define o ouvidor de cliques
        imbCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executa a abertura da galeria para escolher a foto
                //cria um Intent implícito
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                // configura no intent criado para selecionar documentos com mimetype
                photoPickerIntent.setType("image/*");
                //Executa o intent
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);
            }
        });

//Obtem o botao atraves do id
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //Seta o ouvidor de cliques
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            //verifica se os campos foram preenchidos pelo usuario
            public void onClick(View v) {
                // Se o campo estiver vazio...
                if(photoSelected== null) {
                    //...exibe uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                 // Se o campo estiver vazio...
                if(title.isEmpty()) {
                    //...exibe uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;

                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                // Se o campo estiver vazio...
                if (description.isEmpty()) {
                    //...exibe uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }
                //Cria um intent (intencao)
                Intent i = new Intent();
                //seta o Uri da imagem escolhida dentro do intent
                i.setData(photoSelected);
                //seta o titulo
                i.putExtra("title", title);
                //seta a descricao
                i.putExtra("description", description);
                //indica o resultado da Activity
                setResult(Activity.RESULT_OK, i);
                //finaliza a Activity
                finish();


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se requestCode eh referente ao fornecido na chamada de startActiviyForResult atraves do id
        if(requestCode== PHOTO_PICKER_REQUEST) {
            //verifica se resultCode eh um codigo de sucesso
            if(resultCode== Activity.RESULT_OK) {
                //obtem o Uri da imagem escolhida e guarda dentro do atributo de classe photoSelected
                photoSelected = data.getData();
                //obtem o ImageView atraves do id
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                //seta o Uri para que a foto seja exibida na app
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }
}

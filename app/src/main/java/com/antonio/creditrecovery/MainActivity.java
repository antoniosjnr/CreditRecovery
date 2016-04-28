package com.antonio.creditrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText cmpLogin = (EditText) findViewById(R.id.cmpLogin);
        final EditText cmpSenha = (EditText) findViewById(R.id.cmpSenha);
        final TextView valida = (TextView) findViewById(R.id.textMensagem);
        final Button btEntrar = (Button) findViewById(R.id.btEntrar);

        valida.setVisibility(View.INVISIBLE);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cmpLogin.getText().toString().equals("admin") && cmpSenha.getText().toString().equals("admin")){

                    Intent menu = new Intent(MainActivity.this,Menu.class);
                    Intent intent = new Intent(MainActivity.this,Notificacao.class);
                    intent.putExtra("tipo","cadastro");
                    intent.putExtra("titulo","Credit Recovery");
                    intent.putExtra("mensagem","Clique aqui para realizar um financiamento");
                    startService(intent);
                    startActivity(menu);
                }else{
                    valida.setVisibility(View.VISIBLE);

                }
            }
        });

    }
}

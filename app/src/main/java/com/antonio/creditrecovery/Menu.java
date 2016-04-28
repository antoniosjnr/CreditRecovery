package com.antonio.creditrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Antonio on 06/04/2016.
 */
public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v){
        if(v.getTag().toString().equals("log_out")){
            Intent tela_inicial = new Intent(this,MainActivity.class);
            startActivity(tela_inicial);

        }else if(v.getTag().toString().equals("add")){
            Intent tela_cadastro = new Intent(this,Cadastro.class);
            startActivity(tela_cadastro);
        }



    }



}

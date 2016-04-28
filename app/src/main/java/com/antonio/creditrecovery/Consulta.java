package com.antonio.creditrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Antonio on 07/04/2016.
 */
public class Consulta extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        final TextView cmp_identificacao = (TextView) findViewById(R.id.cmp_identificacao);
        final TextView cmp_valorTotal = (TextView) findViewById(R.id.cmp_valorTotal);
        final TextView cmp_numeroParcelas = (TextView) findViewById(R.id.cmp_numeroParcelas);
        final TextView cmp_percJuros = (TextView) findViewById(R.id.cmp_percJuros);
        final TextView cmp_tipoPessoa = (TextView) findViewById(R.id.cmp_tipoPessoa);
        final TextView cmp_valorParcela = (TextView) findViewById(R.id.cmp_valorParcela);

        Intent consulta = getIntent();

        cmp_identificacao.setText(consulta.getStringExtra("identificacao"));
        cmp_valorTotal.setText(consulta.getStringExtra("valorTotal"));
        cmp_numeroParcelas.setText(consulta.getStringExtra("numeroParcelas"));
        cmp_percJuros.setText(consulta.getStringExtra("percJuros"));
        cmp_tipoPessoa.setText(consulta.getStringExtra("tipoPessoa"));
        cmp_valorParcela.setText(consulta.getStringExtra("valorParcela"));

    }

}

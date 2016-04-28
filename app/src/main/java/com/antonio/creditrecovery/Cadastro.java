package com.antonio.creditrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by Antonio on 06/04/2016.
 */
public class Cadastro extends Activity{

    public static final int NOTIFICATION_ID = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final TextView parcela = (TextView) findViewById(R.id.text_parcelas);

        SeekBar seek=(SeekBar) findViewById(R.id.seekBar_parcelas);
        seek.setProgress(1);
        seek.setMax(36);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                parcela.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_pessoa,android.R.layout.simple_spinner_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_tipo);
        spinner.setAdapter(adapter);

        final CheckBox checkJuros = (CheckBox) findViewById(R.id.isJuros);
        final EditText campoJuros = (EditText) findViewById(R.id.perc_juros);
        final Button btCalcular = (Button) findViewById(R.id.btCalcular);

        checkJuros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    campoJuros.setEnabled(true);
                } else {
                    campoJuros.setText("");
                    campoJuros.setEnabled(false);
                }
            }
        });


        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorParcela = "";

                final EditText campoIdentificacao = (EditText) findViewById(R.id.campo_identificacao);
                final EditText campoValorTotal    = (EditText) findViewById(R.id.campo_valor_total);
                final TextView numeroParcelas     = (TextView) findViewById(R.id.text_parcelas);
                final EditText percJuros          = (EditText) findViewById(R.id.perc_juros);
                final Spinner tipoPessoa          = (Spinner)  findViewById(R.id.spinner_tipo);

                Calculo ca = new Calculo();
                valorParcela = ca.calculaParcela(Double.parseDouble(String.valueOf(campoValorTotal.getText())),Double.parseDouble(String.valueOf(percJuros.getText())),Integer.parseInt(String.valueOf(numeroParcelas.getText())));

                Intent intent = new Intent(Cadastro.this,Notificacao.class);

                intent.putExtra("identificacao",String.valueOf(campoIdentificacao.getText()));
                intent.putExtra("valorTotal",String.valueOf(campoValorTotal.getText()));
                intent.putExtra("numeroParcelas",String.valueOf(numeroParcelas.getText()));
                intent.putExtra("percJuros",String.valueOf(percJuros.getText()));
                intent.putExtra("valorParcela", String.valueOf(valorParcela));

                if(tipoPessoa.getSelectedItemPosition() == 0){
                    intent.putExtra("tipoPessoa",getResources().getText(R.string.pessoa_juridica));
                }else{
                    intent.putExtra("tipoPessoa",getResources().getText(R.string.pessoa_fisica));
                }

                intent.putExtra("tipo","consulta");
                intent.putExtra("titulo","Simulação efetuada com sucesso!");
                intent.putExtra("mensagem","Clique aqui para visualizar simulação" + String.valueOf(campoIdentificacao.getText()));


                Intent menu = new Intent(Cadastro.this,Menu.class);
                startService(intent);
                startActivity(menu);

            }
        });

    }

}

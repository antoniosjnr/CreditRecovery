package com.antonio.creditrecovery;

import java.text.DecimalFormat;

/**
 * Created by Antonio on 07/04/2016.
 */
public class Calculo {

    public String calculaParcela(double valorTotal, double taxaJuros,int meses){
        String parcela = null;

        DecimalFormat df = new DecimalFormat("#.##");

        parcela = String.valueOf(df.format(valorTotal*((taxaJuros/100)/(1-(Math.pow(1/(1+(taxaJuros/100)),meses))))));

        return parcela;

    }
}

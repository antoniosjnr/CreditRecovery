package com.antonio.creditrecovery;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Antônio on 27/04/2016.
 */
public class Notificacao extends IntentService {

    public static final int NOTIFICATION_ID = 10;
    private NotificationManager notificationManager;


    public Notificacao() {
        super("Notificacao");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String tipo   = intent.getStringExtra("tipo");
        String titulo = intent.getStringExtra("titulo");
        String mensagem = intent.getStringExtra("mensagem");

        if(tipo.equals("cadastro")){
            criarNotificacaoCadastro(titulo,mensagem);
        }else{
            criarNotificacaoConsulta(intent,titulo,mensagem);
        }

    }

    private void criarNotificacaoCadastro(String titulo, String mensagem) {
        Notification notification = null;

        notification = new Notification(R.mipmap.ic_launcher,
                "Novo evento importante!",
                System.currentTimeMillis());

        notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);


        notification.flags = Notification.FLAG_ONGOING_EVENT;

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this,Cadastro.class), 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(titulo);
            builder.setContentText(mensagem);
            builder.setContentIntent(notificationPendingIntent);
            notificationManager.notify(NOTIFICATION_ID + 1, builder.build());
    }

    private void criarNotificacaoConsulta(Intent intent,String titulo, String mensagem) {
        Notification notification = null;

        notification = new Notification(R.mipmap.ic_launcher,
                "Novo evento importante!",
                System.currentTimeMillis());

        notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);


        notification.flags = Notification.FLAG_AUTO_CANCEL;

        Intent consulta  = new Intent(this,Consulta.class);

        consulta.putExtra("identificacao",intent.getStringExtra("identificacao"));
        consulta.putExtra("valorTotal",intent.getStringExtra("valorTotal"));
        consulta.putExtra("numeroParcelas",intent.getStringExtra("numeroParcelas"));
        consulta.putExtra("percJuros",intent.getStringExtra("percJuros"));
        consulta.putExtra("valorParcela",intent.getStringExtra("valorParcela"));
        consulta.putExtra("tipoPessoa",intent.getStringExtra("tipoPessoa"));

        Intent notificationIntent = consulta;

        PendingIntent notificationPendingIntent =
                PendingIntent.getActivity(Notificacao.this,
                        0, notificationIntent,Intent.FLAG_ACTIVITY_NEW_TASK);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(titulo);
        builder.setContentText(mensagem);
        builder.setContentIntent(notificationPendingIntent);
        notificationManager.notify(NOTIFICATION_ID + 2, builder.build());
    }

}

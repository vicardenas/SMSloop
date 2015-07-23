package com.activadev.demo.smsloop;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SMSloop extends Activity  {
    //String txtNumero = "84442809";    //Cristian
    //String txtNumero = "57902175";    //Angelica
    //String txtNumero = "97838571";      //Johana
    String txtNumero = "";
    //String[] arrMensajes = {"Buena cotito", "Que cuentas?", "Lo que hace el ocio! xD", "Buta estoy aburrido", "Esto se llama 'malgastando los mensajes'", "aun me quedan como 1994 mensajes", "Puedo seguir...", "A que hora te vas?"};
    //String[] arrMensajes = {"Hola mermeladita, como estas", "Buxa, estoy aburrido!", "Adivina...", "Hice una aplicacion para el celu que envia y envia mensajes", "xD", "Que loco no?", "Lo que hace el ocio jajaja", "Lo programe, y te enviara una serie de mensajes automaticamente", "Y te seguiran llegando mensajes", "uno tras otro", "y asi y asi...", "por el siglo de los siglos", "Amen! xD", "Ya oh! muchos mensajes por hoy", "Adios", "ese fue mi aporte xD", "Cuidate, un beso", "Despues hablamos"};
    //String[] arrMensajes = {"H", "O", "O", "O", "O", "L", "A", "A", "C", "O", "M", "P", "A", "Ñ", "E", "R", "I", "T", "A", "A", "!", "!", "Y", "O", "P", "U", "E", "D", "O", "S", "E", "R", "M", "U", "C", "H", "O", "M", "A", "S", "M", "O", "L", "E", "S", "T", "O", "S", "O", "!", "xD"};
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsloop);

        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                PendingIntent sentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("SMS_SENT"), 0);

                registerReceiver(new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context context, Intent intent) {
                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                Toast.makeText(getApplicationContext(), "SMS enviado", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                Toast.makeText(getApplicationContext(), "No se pudo enviar SMS", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NO_SERVICE:
                                Toast.makeText(getApplicationContext(), "Servicio no diponible", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(getApplicationContext(), "PDU (Protocol Data Unit) es NULL", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                Toast.makeText(getApplicationContext(), "Failed because radio was explicitly turned off", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }, new IntentFilter("SMS_SENT"));

                for (String i : arrMensajes) {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(txtNumero, null, i, sentIntent, null);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smsloop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

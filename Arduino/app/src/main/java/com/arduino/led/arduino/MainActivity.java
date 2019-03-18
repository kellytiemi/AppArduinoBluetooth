package com.arduino.led.arduino;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    int aux;

    Button btnConectar, btnLed1, btnLed2, btnLed3, btnLed4, btnLed5, btnLed6, btnLed7, btnLed8, btnApagar, btnAlarme, btnSequencial1, btnSequencial2;
    EditText txtSaida;

    private static final int SOLICITA_ATIVACAO = 1;
    private static final int SOLICITA_CONEXAO = 2;
    private static final int MESSAGE_READ = 3;

    ConnectedThread connectedThread;

    Handler mHandler;

    StringBuilder dadosBluetooth = new StringBuilder();

    BluetoothAdapter meuBluetoothAdapter = null;
    BluetoothDevice meuDevice = null;
    BluetoothSocket meuSocket = null;

    boolean conexao = false;

    private static String MAC = null;

    UUID MEU_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConectar = (Button)findViewById(R.id.btnConectar);
        btnLed1 = (Button)findViewById(R.id.btnLed1);
        btnLed2 = (Button)findViewById(R.id.btnLed2);
        btnLed3 = (Button)findViewById(R.id.btnLed3);
        btnLed4 = (Button)findViewById(R.id.btnLed4);
        btnLed5 = (Button)findViewById(R.id.btnLed5);
        btnLed6 = (Button)findViewById(R.id.btnLed6);
        btnLed7 = (Button)findViewById(R.id.btnLed7);
        btnLed8 = (Button)findViewById(R.id.btnLed8);
        btnApagar = (Button)findViewById(R.id.btnApagar);
        btnAlarme = (Button)findViewById(R.id.btnAlarme);
        btnSequencial1 = (Button)findViewById(R.id.btnSequencial1);
        btnSequencial2 = (Button)findViewById(R.id.btnSequencial2);
        txtSaida = (EditText) findViewById(R.id.txtSaida);

        ArduinoBLL.setDisplay(0);
        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));

        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(meuBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Seu dispositivo não possui Bluetooth",Toast.LENGTH_LONG).show();

        }else if(!meuBluetoothAdapter.isEnabled()){
            Intent ativaBluetooth = new Intent(meuBluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ativaBluetooth, SOLICITA_ATIVACAO);
        }
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    //desconectar
                    try{
                        meuSocket.close();
                        conexao = false;
                        btnConectar.setText("Conectar");
                        Toast.makeText(getApplicationContext(), "Bluetooth foi desconectado",Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro: " + erro,Toast.LENGTH_LONG).show();
                    }
                }else{
                    //conectar

                    Intent abreLista = new Intent(MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista,SOLICITA_CONEXAO);
                }
            }
        });
        btnLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led1");


                    if (btnLed1.getText().equals("Ligar")) {
                        aux += 1;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed1.setText("Desligar");

                    } else {
                        aux -= 1;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed1.setText("Ligar");
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
              }
            }
        });

        btnLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led2");
                    if (btnLed2.getText().equals("Ligar")) {
                        aux += 2;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed2.setText("Desligar");

                    } else {
                        aux -= 2;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed2.setText("Ligar");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led3");

                    if (btnLed3.getText().equals("Ligar")) {
                        aux += 4;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed3.setText("Desligar");

                    } else {
                        aux -= 4;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed3.setText("Ligar");
                    }
                }
               else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLed4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led4");

                    if (btnLed4.getText().equals("Ligar")) {
                        aux += 8;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed4.setText("Desligar");

                    } else {
                        aux -= 8;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed4.setText("Ligar");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led5");
                    if (btnLed5.getText().equals("Ligar")) {
                        aux += 16;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed5.setText("Desligar");

                    } else {
                        aux -= 16;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed5.setText("Ligar");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLed6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led6");
                    if (btnLed6.getText().equals("Ligar")) {
                        aux += 32;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed6.setText("Desligar");

                    } else {
                        aux -= 32;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed6.setText("Ligar");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLed7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led7");
                    if (btnLed7.getText().equals("Ligar")) {
                        aux += 64;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed7.setText("Desligar");

                    } else {
                        aux -= 64;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed7.setText("Ligar");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLed8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao) {
                    connectedThread.enviar("led8");
                    if (btnLed8.getText().equals("Ligar")) {
                        aux += 128;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed8.setText("Desligar");

                    } else {
                        aux -= 128;
                        ArduinoBLL.setDisplay(aux);
                        txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                        btnLed8.setText("Ligar");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

               if(conexao){
                    ArduinoBLL.setDisplay(0);
                    txtSaida.setText(ArduinoBLL.mostraBits(ArduinoBLL.getDisplay()));
                    btnLed1.setText("Ligar");
                    btnLed2.setText("Ligar");
                    btnLed3.setText("Ligar");
                    btnLed4.setText("Ligar");
                    btnLed5.setText("Ligar");
                    btnLed6.setText("Ligar");
                    btnLed7.setText("Ligar");
                    btnLed8.setText("Ligar");
                    connectedThread.enviar("apagar");
               }else{
                 Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
               }
            }
        });

        btnAlarme.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(conexao){
                if (btnAlarme.getText().equals("Função Alarme - Ativar"))
                {
                    btnAlarme.setText("Função Alarme - Desativar");
                    connectedThread.enviar("alarme");
                }
                else {
                    connectedThread.enviar("stop");
                    btnAlarme.setText("Função Alarme - Ativar");
                }
                }else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }

        });

        btnSequencial1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                if(conexao){
                if (btnSequencial1.getText().equals("Sequencial 01"))
                {
                    btnSequencial1.setText("Sequencial 01 - Desativar");
                    connectedThread.enviar("sequencial1");
                }
                else
                {
                    connectedThread.enviar("stop");
                    btnSequencial1.setText("Sequencial 01");
                }
                }else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado ",Toast.LENGTH_LONG).show();
                }
            }
        });
   btnSequencial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnSequencial2.getText().equals("Sequencial 02")) {
                    btnSequencial2.setText("Sequencial 02 - Desativar");
                    connectedThread.enviar("sequencial2");
                }
                else
                {
                    connectedThread.enviar("stop");
                    btnSequencial2.setText("Sequencial 02");
                }
            }
        });

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_READ){
                    String recebidos = (String) msg.obj; //recebo

                    dadosBluetooth.append(recebidos); //junto

                    int fimInformacao = dadosBluetooth.indexOf("}");

                    if(fimInformacao > 0)
                    {
                        String dadosCompletos = dadosBluetooth.substring(0,fimInformacao);

                        int tamInformacao = dadosCompletos.length();
                        if(dadosBluetooth.charAt(0) == '{'){

                            String dadosFinais = dadosBluetooth.substring(1,tamInformacao);

                            Log.d("Recebidos", dadosFinais);

                            if(dadosFinais.contains("stop")){
                                btnLed1.setText("PAROU");
                                //Log.d("Led 1", "Ligado");

                            } else if(dadosFinais.contains("l1of")){
                                btnLed1.setText("Ligar");
                               // Log.d("Led 1", "Desligado");
                            }

                            if(dadosFinais.contains("l2on")){
                                btnLed2.setText("Desligar");
                            } else if(dadosFinais.contains("l2of")){
                                btnLed2.setText("Ligar");
                            }

                            if(dadosFinais.contains("l3on")){
                                btnLed3.setText("Desligar");
                            } else if(dadosFinais.contains("l3of")){
                                btnLed3.setText("Ligar");
                            }

                            if(dadosFinais.contains("l4on")){
                                btnLed4.setText("Desligar");
                                Log.d("Led 4", "Ligado");

                            } else if(dadosFinais.contains("l4of")){
                                btnLed4.setText("Ligar");
                                Log.d("Led 4", "Desligado");
                            }
                            if(dadosFinais.contains("l5on")){
                                btnLed5.setText("Desligar");
                                Log.d("Led 1", "Ligado");

                            } else if(dadosFinais.contains("l5of")){
                                btnLed5.setText("Ligar");
                                Log.d("Led 1", "Desligado");
                            }

                            if(dadosFinais.contains("l6on")){
                                btnLed6.setText("Desligar");
                                Log.d("Led 1", "Ligado");

                            } else if(dadosFinais.contains("l6of")){
                                btnLed6.setText("Ligar");
                                Log.d("Led 1", "Desligado");
                            }

                            if(dadosFinais.contains("l7on")){
                                btnLed7.setText("Desligar");
                                Log.d("Led 1", "Ligado");

                            } else if(dadosFinais.contains("l7of")){
                                btnLed7.setText("Ligar");
                                Log.d("Led 1", "Desligado");
                            }

                            if(dadosFinais.contains("l8on")){
                                btnLed8.setText("Desligar");
                                Log.d("Led 1", "Ligado");

                            } else if(dadosFinais.contains("l8of")){
                                btnLed8.setText("Ligar");
                                Log.d("Led 1", "Desligado");
                            }
                        }
                        dadosBluetooth.delete(0, dadosBluetooth.length());
                    }
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SOLICITA_ATIVACAO:
                if (resultCode == Activity.RESULT_OK){
                    Toast.makeText(getApplicationContext(), "O Bluetooth foi ativado",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "O Bluetooth não foi ativado, o aplicativo será encerrado",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case SOLICITA_CONEXAO:
                if(resultCode == Activity.RESULT_OK){
                    MAC = data.getExtras().getString(ListaDispositivos.ENDERECO_MAC);
                    //Toast.makeText(getApplicationContext(), "MAC FINAL: " + MAC,Toast.LENGTH_LONG).show();
                    meuDevice = meuBluetoothAdapter.getRemoteDevice(MAC);

                    try{
                        meuSocket = meuDevice.createRfcommSocketToServiceRecord(MEU_UUID);
                        meuSocket.connect();

                        conexao = true;
                        connectedThread = new ConnectedThread(meuSocket);
                        connectedThread.start();

                        btnConectar.setText("Desconectar");
                        Toast.makeText(getApplicationContext(), "Você foi conectado com: " + MAC,Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        conexao = false;
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro: " + erro,Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Falha ao obter o MAC",Toast.LENGTH_LONG).show();
                }
        }
    }
    private class ConnectedThread extends Thread {

        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() { //Responsável por receber os dados que vem do arduino

            byte[] buffer = new byte[1024];
            int bytes;

              while (true) {
                try {
                    bytes = mmInStream.read(buffer);

                    String dadosBt = new String (buffer, 0, bytes);

                   mHandler.obtainMessage(MESSAGE_READ, bytes, -1, dadosBt).sendToTarget();

                } catch (IOException e) {
                    break;
                }
            }
        }

        public void enviar(String dadosEnviar) {
            byte [] msgBuffer = dadosEnviar.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {}
        }
    }
}

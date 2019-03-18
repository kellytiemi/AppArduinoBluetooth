package com.arduino.led.arduino;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.Set;


public class ListaDispositivos extends ListActivity {
    private BluetoothAdapter meuBluetoothAdapter2 = null;

    static String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        meuBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdapter2.getBondedDevices();

        if (dispositivosPareados.size() > 0){
            for(BluetoothDevice dispositivo : dispositivosPareados){
                String nomeBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluetooth.add(nomeBt + " \n" + macBt);

            }
        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String informacaoGeral = ((TextView) v).getText().toString();

        //Toast.makeText(getApplicationContext(),"Info: " + informacaoGeral, Toast.LENGTH_LONG).show();

        String enderecoMAC = informacaoGeral.substring(informacaoGeral.length()-17);
        //Toast.makeText(getApplicationContext(),"MAC: " + enderecoMAC, Toast.LENGTH_LONG).show();

        Intent retornMac = new Intent();
        retornMac.putExtra(ENDERECO_MAC, enderecoMAC);
        setResult(RESULT_OK, retornMac);
        finish();
    }
}

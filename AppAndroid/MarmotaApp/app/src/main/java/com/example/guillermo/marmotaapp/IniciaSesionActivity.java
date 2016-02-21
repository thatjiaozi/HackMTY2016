package com.example.guillermo.marmotaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class IniciaSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicia_sesion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicia_sesion, menu);
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

    public void sendMessage(View view) {
        SocketLogin socConexion = new SocketLogin();
        EditText editText1 = (EditText) findViewById(R.id.email);
        EditText editText2 = (EditText) findViewById(R.id.password);
        String message = editText1.getText().toString() + "#" + editText2.getText().toString();
        socConexion.bLogin(message);
        if(socConexion.bLogin(message)) {
            Intent i= new Intent(this, MapsActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("Socket", socConexion);
            i.putExtras(b);
            System.out.println("sdfghjk");
            startActivity(i);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Usuario o contraseña incorrectos")
                    .setMessage("Intenta de nuevo")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public void Registrarse(View view) {
        Intent intent = new Intent(this,RegistroActivity.class);
        startActivity(intent);
    }
}

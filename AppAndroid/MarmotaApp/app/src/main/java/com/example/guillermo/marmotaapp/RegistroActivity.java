package com.example.guillermo.marmotaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {
    private SocketRegister socRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        socRegistrar = new SocketRegister();
    }
    public void EnviarDatos(View view){
        EditText editText1 = (EditText) findViewById(R.id.email_signup);
        EditText editText2 = (EditText) findViewById(R.id.password_signup);
        String message = editText1.getText().toString() + "#" + editText2.getText().toString();
        if(socRegistrar.bRegister(message)) {
            Intent i= new Intent(this, IniciaSesionActivity.class);
            Bundle b = new Bundle();
            startActivity(i);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Usuario o contraseña no disponibles")
                    .setMessage("Intenta de nuevo")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}

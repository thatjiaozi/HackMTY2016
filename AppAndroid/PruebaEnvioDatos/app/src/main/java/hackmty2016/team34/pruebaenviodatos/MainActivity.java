package hackmty2016.team34.pruebaenviodatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private int iMinuto;
    private int iHora;
    private int iDia;
    private int iMes;
    private int iAnio;
    Calendar datFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // se captura la fecha del sistema android
        datFecha = Calendar.getInstance();
        // se obtienen los enteros  que corresponden a la hora actual
        iMinuto = datFecha.get(Calendar.MINUTE);
        iHora = datFecha.get(Calendar.HOUR_OF_DAY);
        // se obtienen los enteros que correspen a la fecha
        iDia = datFecha.get(Calendar.DAY_OF_MONTH);
        iMes = datFecha.get(Calendar.MONTH) + 1;
        iAnio = datFecha.get(Calendar.YEAR);
    }
}

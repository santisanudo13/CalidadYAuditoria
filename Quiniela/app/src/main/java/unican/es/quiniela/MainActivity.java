package unican.es.quiniela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    static Quiniela quiniela;
    Button button_Generar, button_Limpiar, button_Ajustes;
    TextView textView_InfoMain;

    static final int AJUSTES_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos objeto quiniela
        quiniela = new Quiniela(0.6, 0.2, 0.1, 0.25, 0.25);
        textView_InfoMain = (TextView) findViewById(R.id.textView_InfoMain);
        button_Generar = (Button) findViewById(R.id.button_Generar);
        button_Limpiar = (Button) findViewById(R.id.button_Limpiar);
        button_Ajustes = (Button) findViewById(R.id.button_Ajustes);
    }

    public void generaQuiniela(View view)
    {
        textView_InfoMain.setText(quiniela.dameQuiniela());
    }
    public void limpiarQuiniela(View view)
    {
        textView_InfoMain.setText(R.string.InfoMain);
    }

    public void ajustes(View view)
    {
        Intent intent = new Intent (this, AjustesActivity.class);
        startActivityForResult(intent, AJUSTES_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == AJUSTES_REQUEST)
        {
            // Make sure the request was successful
            if (resultCode == RESULT_OK)
            {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
            if(resultCode == RESULT_CANCELED)
            {

            }
        }
    }
}

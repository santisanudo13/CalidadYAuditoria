package unican.es.quiniela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Quiniela quiniela;
    Button button_Generar, button_Limpiar, button_Ajustes;
    TextView textView_InfoMain;

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
}

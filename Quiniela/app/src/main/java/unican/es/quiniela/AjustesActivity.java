package unican.es.quiniela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class AjustesActivity extends AppCompatActivity {
    Quiniela quiniela;
    EditText probabildiad_EquipoLocal, probabilidad_Empate;
    Button  button_Aceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        probabildiad_EquipoLocal = (EditText) findViewById(R.id.editText_Probabildiad_EquipoLocal);
        probabilidad_Empate = (EditText) findViewById(R.id.editText_Probabildiad_Empate);
        button_Aceptar = (Button) findViewById(R.id.button_Aceptar);
    }

    public void setQuiniela(Quiniela quiniela)
    {
        this.quiniela = quiniela;
    }

    public void aceptarAjustes(View view)
    {
        Double p1, px;

        p1 = Double.parseDouble(probabildiad_EquipoLocal.getText()+"");
        px = Double.parseDouble(probabilidad_Empate.getText()+"");
        if(p1+px > 1)
        {
            setResult(RESULT_CANCELED, getIntent());
        }

        //CASO EN EL QUE LOS PARAMETROS SON LOS CORRECTOS
        if(p1+px <= 1)
        {
            this.quiniela = MainActivity.quiniela;
            quiniela.setProbabilidades(p1, px);

            setResult(RESULT_OK, getIntent());
        }

        finish();
    }
}

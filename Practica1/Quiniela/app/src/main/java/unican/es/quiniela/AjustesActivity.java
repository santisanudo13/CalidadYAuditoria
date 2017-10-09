package unican.es.quiniela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;




public class AjustesActivity extends AppCompatActivity {
    EditText probabildiadEquipoLocal;
    EditText probabilidadEmpate;
    Button buttonAceptar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        probabildiadEquipoLocal = (EditText) findViewById(R.id.editText_Probabildiad_EquipoLocal);
        probabilidadEmpate = (EditText) findViewById(R.id.editText_Probabildiad_Empate);
        buttonAceptar = (Button) findViewById(R.id.button_Aceptar);
    }


    /**
     * Este metodo se ejecuta al pulsar el boton Aceptar. Cuando uno de los campos esta vacio salta un mensaje de error.
     * Si la suma de ambos es mayor a 1 muestra un mensaje de error.
     * En caso de cumplir ambos casos, cambia el valor de la clase quiniela asociada a la MainActivity y termina la actividad con un resultado correcto
     * @param view
     */
    public void aceptarAjustes(View view)
    {
        String strP1 = probabildiadEquipoLocal.getText().toString();
        String strPx = probabilidadEmpate.getText().toString();

        //Caso campos vacios
        if(strP1.isEmpty() || strPx.isEmpty())
        {
            Toast.makeText(this, (R.string.errorCamposVacios), Toast.LENGTH_LONG).show();
        }else{
            Double p1;
            Double px;
            p1 = Double.parseDouble(strP1);
            px = Double.parseDouble(strPx);

            //Caso suma de ambos campos mayor que 1
            if(p1+px > 1 || p1+px < 0)
            {
                Toast.makeText(this, (R.string.errorP1PxMayor1), Toast.LENGTH_LONG).show();
             //CASO EN EL QUE LOS PARAMETROS SON LOS CORRECTOS
            }else{
                getIntent().putExtra("p1", p1);
                getIntent().putExtra("px", px);

                setResult(RESULT_OK, getIntent());
                finish();
            }
        }
    }
}

package unican.es.quiniela;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Quiniela quiniela;
    Button buttonGenerar;
    Button buttonLimpiar;
    Button buttonAjustes;
    TextView textViewInfoMain;
    static final int AJUSTES_REQUEST = 1;

    /**
     * Constructor de la vista al generar actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos objeto quiniela
        quiniela = new Quiniela(0.3, 0.3, 0.3, 0.3, 0.4);
        textViewInfoMain = (TextView) findViewById(R.id.textView_InfoMain);
        buttonGenerar = (Button) findViewById(R.id.button_Generar);
        buttonLimpiar = (Button) findViewById(R.id.button_Limpiar);
        buttonAjustes = (Button) findViewById(R.id.button_Ajustes);
    }

    /**
     * Metodo encargado de generar el toolbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Metodo encargado de determinar las acciones a realizar por los botones del toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.toolbar_ajustes)
        {
                Intent intent = new Intent (this, AjustesActivity.class);
                startActivityForResult(intent, AJUSTES_REQUEST);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }


    /**
     * Metodo que se ejecuta al pulsar el boton Generar, el cual calcula una quiniela aleatorio basandose
     * en la probabilidad establecida en la clase quiniela
     * @param view
     */
    public void generaQuiniela(View view)
    {
        textViewInfoMain.setText("");
        List<String> list = quiniela.dameQuiniela();

        for(int i = 0; i<list.size(); i++)
        {
            String line = list.get(i);
            if(i == 13 )
            {
                appendColoredText(textViewInfoMain, line, Color.BLACK);
            }else{
                if(i == 14 )
                {
                    appendColoredText(textViewInfoMain, line, Color.BLACK);
                }else{
                    switch (line){
                        case "1":
                            appendColoredText(textViewInfoMain, line, Color.GREEN);
                            break;
                        case "2":
                            appendColoredText(textViewInfoMain, line, Color.RED);
                            break;
                        case "X":
                            appendColoredText(textViewInfoMain, line, Color.BLACK);
                            break;
                        default:
                            appendColoredText(textViewInfoMain, line, Color.BLACK);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Metodo encargado de añadir al final del textView un String con el Color indicado
     * @param tv
     * @param text
     * @param color
     */
    public void appendColoredText(TextView tv, String text, int color) {
        int start = tv.getText().length();
        tv.append(text);
        int end = tv.getText().length();
        Spannable spannableText = (Spannable) tv.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
        tv.append("\n");
    }

    /**
     * Metodo que se ejecuta al pulsar el boton Limpiar y que se encarga de mostrar en pantalla el mensaje
     * inicial igual que al inciar la App
     * @param view
     */
    public void limpiarQuiniela(View view)
    {
        textViewInfoMain.setText(R.string.InfoMain);
    }

    /**
     * Metodo que se encarga de ejecutar al pulsar el boton Ajustes, este genera una nueva activity y esta a la espera del resultado de esta
     * @param view
     */
    public void ajustes(View view)
    {
        Intent intent = new Intent (this, AjustesActivity.class);
        startActivityForResult(intent, AJUSTES_REQUEST);
    }

    /**
     * Este metodo se ejecuta cada vez que termina una actividad ejecutada a partir del metodo startActivityForResult()
     * En este caso solo se ejecuta 1 vez con la activity de ajustes y comprobamos que los resultados son los correctos
     * en base al mensaje de respuesta que dan al cerrarse dicha actividad, en nuestro casi la actividad solo puede cerrarse cuando la actividad
     * ha finalizado con los valores correctos, por tanto en caso de que resultCode sea RESULT_OK variamos las probabilidades de la quiniela a los valores
     * obtenidos de la actividad Ajustes
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Caso Actividad AJUSTES
        if (requestCode == AJUSTES_REQUEST)
        {
            //CASO CORRECTO
            if (resultCode == RESULT_OK)
            {
                quiniela.setProbabilidades(data.getDoubleExtra("p1",0.0),data.getDoubleExtra("px",0.0));
            }
            //CASO INCORRECTO, este caso nunca llega a darse ya que no esta implementado
            if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, (R.string.errorCasoIncorrecto), Toast.LENGTH_LONG).show();
            }
        }
    }
}

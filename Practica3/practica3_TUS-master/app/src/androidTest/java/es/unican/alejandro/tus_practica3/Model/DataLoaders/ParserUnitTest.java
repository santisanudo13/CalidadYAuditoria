package es.unican.alejandro.tus_practica3.Model.DataLoaders;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import es.unican.alejandro.tus_practica3.Model.Linea;
import es.unican.alejandro.tus_practica3.R;

import static junit.framework.Assert.assertTrue;

/**
 * Created by alejandro on 12/09/17.
 */

public class ParserUnitTest {
    /**
     * Test para comprobar el parseo del JSON correspondiente a las lineas de TUS Santander
     * @throws Exception
     */
    @Test
    public void testParserLineas() throws Exception {
        //Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_test);

        //Cargamos el parser y una lista donde almacenar los resultados
        ParserJSON parser = new ParserJSON();
        List<Linea> result = new ArrayList<Linea>();

        //Realizamos la llamada a la lectura del archivo json almacenado en res/raw
        result = parser.readArrayLineasBus(is);

        //Comprobamos que los resultados de la lista son los deseados
        assertTrue(checkResult(result));
    }// testParserLineas

    private boolean checkResult(List<Linea> result) {
        for (int i=0; i<result.size();i++)
        {
            if(i == 0) {
                if (result.get(i).getIdentifier() != 20)
                    return false;
                if (!result.get(i).getName().equals("ESTACIONES-BARRIO LA TORRE"))
                    return false;
                if (!result.get(i).getNumero().equals("20"))
                    return false;
            }
            if(i == 1) {
                if (result.get(i).getIdentifier() != 19)
                    return false;
                if (!result.get(i).getName().equals("ESTACIONES-RICARDO L. ARANDA"))
                    return false;
                if (!result.get(i).getNumero().equals("19"))
                    return false;
            }
        }
        return true;
    }
}// ParserUnitTest

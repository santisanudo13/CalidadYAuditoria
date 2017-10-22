package es.unican.alejandro.tus_practica3.Views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.unican.alejandro.tus_practica3.Model.Linea;
import es.unican.alejandro.tus_practica3.R;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListLineasAdapter extends ArrayAdapter {
    List<Linea> lineasBus;
    Context context;

    public ListLineasAdapter (Context context, List<Linea> lineasBus){
        super(context, R.layout.custom_list_lineas_layout,lineasBus);
        this.context = context;
        this.lineasBus = lineasBus;
    }// ListLineasAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);

        //HACEMOS QUE Se muestre cada numero y cada nombre con el color apropiado de cada linea
        String numero = lineasBus.get(position).getNumero().trim();
        String nombre = lineasBus.get(position).getName().trim();

        int color = 0;

        switch (numero){
            case "1":
                color = Color.RED;
                break;
            case "2":
                color = Color.MAGENTA;
                break;
            case "3":
                color = Color.YELLOW;
                break;
            case "4":
                color = Color.BLUE;
                break;
            case "5":
                color = Color.GRAY;
                break;
            case "6":
                color = Color.GREEN;
                break;
            case "7":
                color = Color.argb(100, 255, 165, 0);
                break;
            case "11":
                color = Color.argb(100, 0, 0, 255);
                break;
            case "12":
                color = Color.argb(100, 127, 255, 0);
                break;
            case "13":
                color = Color.argb(100, 147,112,219);
                break;
            case "14":
                color = Color.argb(100, 30,144,255);
                break;
            case "16":
                color = Color.argb(100, 138,43,226);
                break;
            case "17":
                color = Color.argb(100, 255,182,193);
                break;
            case "18":
                color = Color.argb(100, 176,224,230);
                break;
            case "19":
                color = Color.argb(100, 0,191,255);
                break;
            case "20":
                color = Color.argb(100, 0,255,127);
                break;
            case "21":
                color = Color.argb(100, 144,238,144);
                break;
            case "23":
                color = Color.GRAY;
                break;
            case "N1":
                color = Color.BLACK;
                break;
            case "N2":
                color = Color.BLACK;
                break;
            case "N3":
                color = Color.BLACK;
                break;
            default:
                color = Color.BLACK;
                break;
        }

        appendColoredText(textViewName, nombre, Color.BLACK);
        appendColoredText(textViewNumero, numero, color);



        return viewRow;
    }


    public void appendColoredText(TextView tv, String text, int color) {
        int start = tv.getText().length();
        tv.append(text);
        int end = tv.getText().length();
        Spannable spannableText = (Spannable) tv.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
        tv.append("");
    }
}// ListLineasAdapter

package unican.es.quiniela;


import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class QuinielaTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest1() {

    //Clickamos Generar y comprabmos que se generan 15 lineas
        onView(withId(R.id.button_Generar)).perform(click());
        onView(withId(R.id.textView_InfoMain)).check(ViewAssertions.matches(checkLineas(15)));
        onView(withId(R.id.textView_InfoMain)).check(ViewAssertions.matches(checkerExtra()));

    }

    public static Matcher<View> checkLineas (final int lineas){
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                // Comprueba que en el textView hay 15 lineas basandonos en que hay 15 "\n"
                TextView txt = (TextView) item;

                String str = txt.getText().toString();
                String[] lines = str.split("\n");

               if(lines.length == lineas)
               {
                   return true;
               }else{
                   return false;
               }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El numero de lineas no coincide");
            }
        };
    }


    public static Matcher<View> checkerExtra (){
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                // Comprueba que en el textView hay 15 lineas basandonos en que hay 15 "\n"
                TextView txt = (TextView) item;

                String str = txt.getText().toString();
                String[] lines = str.split("\n");

                boolean correcto = true;

                for(int i=0; i< lines.length; i++)
                {
                    if(i == 13 || i == 14){
                        if(!(lines[i].contains("0")  ||lines[i].contains("1")  || lines[i].contains("2")  || lines[i].contains("M"))){
                            correcto = false;
                            break;
                        }
                    }else{
                      if(!(lines[i].contains("1")  || lines[i].contains("2")  || lines[i].contains("X"))){
                          correcto = false;
                          break;
                      }
                    }

                }

                return correcto;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Los campos de las lineas no son los esperados");
            }
        };
    }
}

package com.mapplinks.ohmslawcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class MainActivity extends AppCompatActivity {

    TextView  message;
    EditText voltage, current, resistance;
    Button calc, reset;

    String projectToken = "e3861f14fc3766a4a858c38abde22023"; // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"


    String law = "Ohm's Law states that V=I/R\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, projectToken);

        voltage = (EditText) findViewById(R.id.voltage);
        current = (EditText) findViewById(R.id.current);
        resistance = (EditText) findViewById(R.id.resistance);

        message=(TextView)findViewById(R.id.message);

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltage.setText("");
                current.setText("");
                resistance.setText("");
                message.setText("");
                keyboardHide();

            }
        });


        calc = (Button) findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String V = voltage.getText().toString();
                String A = current.getText().toString();
                String R=resistance.getText().toString();
                calculate(V, A ,R);
                mixpanel.track("Calculate");
                keyboardHide();
            }
        });
    }

void calculate(String V, String A, String R){
    float v, i , r;

    if(!V.isEmpty()&&!A.isEmpty()&&!R.isEmpty()){
        Toast.makeText(MainActivity.this, "Woah! Enter just two values!", Toast.LENGTH_SHORT).show();
    } else if(!V.isEmpty() && !A.isEmpty()){
        v=Float.parseFloat(V);
        i= Float.parseFloat(A);
        r=v/i;
        voltage.setText(V);
        current.setText(A);
        resistance.setText(Float.toString(r));
        message.setText(law +"Voltage: "+V + " V\n"+"Current: "+A + " A\n"+"Resistance: "+Float.toString(r)+ " Ω");
    }else if(!V.isEmpty() && !R.isEmpty()){
        v=Float.parseFloat(V);
        r= Float.parseFloat(R);
        i=v/r;
        voltage.setText(V);
        resistance.setText(R);
        current.setText(Float.toString(i));
        message.setText(law+"Voltage: "+V + " V\n"+"Current: "+Float.toString(i) + " A\n"+"Resistance: "+R+ " Ω");
    }else if(!A.isEmpty() && !R.isEmpty()){
        i=Float.parseFloat(A);
        r= Float.parseFloat(R);
        v=i*r;
        voltage.setText(Float.toString(v));
        resistance.setText(R);
        current.setText(A);
        message.setText(law+"Voltage: "+Float.toString(v) + " V\n"+"Current: "+A + " A\n"+"Resistance: "+R+ " Ω");
    }else{
        Toast.makeText(MainActivity.this, "Enter the Appropriate Values!", Toast.LENGTH_SHORT).show();
    }

}



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    void keyboardHide(){
        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}

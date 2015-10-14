package com.mapplinks.ohmslawcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView  message;
    EditText voltage, current, resistance;
    Button calc, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        voltage = (EditText) findViewById(R.id.voltage);
        current = (EditText) findViewById(R.id.current);
        resistance = (EditText) findViewById(R.id.resistance);

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltage.setText("");
                current.setText("");
                resistance.setText("");
            }
        });

        message=(TextView)findViewById(R.id.message);

        calc = (Button) findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String V = voltage.getText().toString();
                String A = current.getText().toString();
                String R=resistance.getText().toString();
                calculate(V, A ,R);
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
        voltage.setText(V + " V");
        current.setText(A + " A");
        resistance.setText(Float.toString(r)+ " Ω");
        message.setText("Voltage: "+V + " V\n"+"Current: "+A + " A\n"+"Resistance: "+Float.toString(r)+ " Ω");
    }else if(!V.isEmpty() && !R.isEmpty()){
        v=Float.parseFloat(V);
        r= Float.parseFloat(R);
        i=v/r;
        voltage.setText(V + " V");
        resistance.setText(R+ " Ω");
        current.setText(Float.toString(i)+ " A");
        message.setText("Voltage: "+V + " V\n"+"Current: "+Float.toString(i) + " A\n"+"Resistance: "+R+ " Ω");
    }else if(!A.isEmpty() && !R.isEmpty()){
        i=Float.parseFloat(A);
        r= Float.parseFloat(R);
        v=i*r;
        voltage.setText(Float.toString(v)+ " V");
        resistance.setText(R+ " Ω");
        current.setText(A+ " A");
        message.setText("Voltage: "+Float.toString(v) + " V\n"+"Current: "+A + " A\n"+"Resistance: "+R+ " Ω");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

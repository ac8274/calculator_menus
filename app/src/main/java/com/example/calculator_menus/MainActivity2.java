package com.example.calculator_menus;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnCreateContextMenuListener {
    Intent gi;
    Button returner;
    TextView AN;
    ListView lv;
    Double[] B = new Double[20];
    double a1;
    double qd12;
    boolean ans;
    int positioner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        returner = findViewById(R.id.button2);
        lv = findViewById(R.id.An);
        AN = findViewById(R.id.txt);
        gi = getIntent();
        B = list_veiw_filler();
        ArrayAdapter<Double> adp = new ArrayAdapter<Double>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, B);
        lv.setAdapter(adp);
        registerForContextMenu(lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        menu.add("n");
        menu.add("S");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo(); // get extra info from adapter

        int position = menuinfo.position; // get the position of the item clicked in the adapter of the list view.


        String str = item.getTitle().toString(); // get option that the user choose.

        if(str == "n"){
            String str2 = "n = "+ Integer.toString(position+2);
            AN.setText(str2);
        }
        else {
            Sn_writer(position);
        }
        
        return super.onContextItemSelected(item);
    }
    public Double[] list_veiw_filler()
    /*
    This function fills a list of 20 places with the first 20 items of the sequence.
     */
    {
        Double[] B = new Double[20];
        a1 = 0;
        qd12 = 0;
        ans = false;
        //int i = 2;
        ans = gi.getBooleanExtra("Q or D", ans);
        a1 = gi.getDoubleExtra("A1", a1);
        qd12 = gi.getDoubleExtra("QD", qd12);
        for (int i = 2; i < 22; i++) {
            B[i - 2] = ANQN(i, a1, qd12, ans);
        }
        return B;
    }




    public double ANQN(int position, double a1, double qd, boolean answear)
    // Function returns the number in the sequence in the position that was given to it.
    {
        if (answear) {
            return Qn_calculator(position, a1, qd);
        } else {
            return An_calculator(position, a1, qd);
        }
    }


    public double Qn_calculator(int position, double a1, double q)
    // calculates the number in the engeniering sequence.
    {
        return (a1 * (Math.pow(q, ((double) position) - 1.0)));
    }

    public double An_calculator(int position, double a1, double d)
    // calculates the number in the normal sequence.
    {
        return (a1 + ((((double) position) - 1.0) * d));
    }


    public void return_main(View view)
    // finishes activity.
    {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String st2 = " A(n) = " + Double.toString(B[position]);
        AN.setText(st2);
    }

    public void Sn_writer(int position)
    // writes the sum of the numbers from start till the place given.
    {
        String st = " S(n) = " + Double.toString(Sn_calculator_NORMAL(position+2, B[position]));
        String st2 = " S(n) = " + Double.toString(Sn_calculator_Engineer(position+2));
        if (ans && qd12 == 1) {
            AN.setText(st);
        } else if (ans) {
            AN.setText(st2);
        } else {
            AN.setText(st);
        }
    }


    public Double Sn_calculator_Engineer(int position)
    // calculates the sum of all the numbers between the first number given and the number in the position "position" in an enginering sequence.
    {
        return ((a1 * (Math.pow(qd12, position) - 1.0)) / (qd12 - 1));
    }

    public Double Sn_calculator_NORMAL(int position, Double an)
    // calculates the sum of all the numbers between the first number given and the number in the position "position" in a normal sequence.
    {
        return ((position * (an + a1))/2);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
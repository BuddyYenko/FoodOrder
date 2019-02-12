package com.androiddeft.loginandregistration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Jollof extends AppCompatActivity {

    static int plate_of_jollof;
    static int jollof_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jollof);

        total_cal();
        orders_list_init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void jollof_method(View view) {
        plate_of_jollof = inc(plate_of_jollof);
        TextView tv = (TextView) findViewById(R.id.joff_number);
        tv.setText("" + plate_of_jollof);
        total_cal();
    }

    public void jollof_method1(View view) {
        if (plate_of_jollof >= 0) {
            plate_of_jollof = dec(plate_of_jollof);
            TextView tv = (TextView) findViewById(R.id.joff_number);
            if (plate_of_jollof > 0) tv.setText("" + plate_of_jollof);
            else tv.setText("__");
            total_cal();
        }

    }

    public void total_cal() {
        jollof_total = plate_of_jollof * (40);
        FinalizeOrder.all_total = Jollof.jollof_total + Samp.samp_total;
        if (FinalizeOrder.all_total > 0) {
            //TextView tv = (TextView) findViewById(R.id.dessert_tot_id);
            //tv.setText("" + "R" + (FinalizeOrder.all_total));

        }
        else{
            //TextView tv = (TextView) findViewById(R.id.dessert_tot_id);
            //tv.setText("");
        }
        orders_list_init();
    }


    public void main_menu(View view) {
        Intent goto_main_menu = new Intent(this, DashboardActivity.class);
        startActivity(goto_main_menu);
        //overridePendingTransition(R.anim.fadin, R.anim.fadout);
    }


    public int dec(int x) {
        if (x > 0) {
            x--;
            return x;
        } else return 0;
    }


    public int inc(int x) {
        x++;
        return (x);
    }

    public void orders_list_init() {



        if (plate_of_jollof > 0) {
            //TextView tv = (TextView) findViewById(R.id.choco_ice_cream_order);
            //tv.setText("" + chocolate_ice_cream);
        }

    }

    public void fin_ord(View view)
    {
        final Object o = this;
        if(FinalizeOrder.all_total>0) {
            Intent fin = new Intent(this, FinalizeOrder.class);
            startActivity(fin);
            //overridePendingTransition(R.anim.fadin, R.anim.fadout);
        }
        else{

            if(FinalizeOrder.next_ord_flag==1)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you don't want to place another order?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent nextact = new Intent((Jollof) o, Thank_You.class);
                                startActivity(nextact);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }

            else{
                Toast.makeText(getApplicationContext(),
                        "Please select your order", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

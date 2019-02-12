package com.androiddeft.loginandregistration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Samp extends AppCompatActivity {

    static int bowl_of_samp;
    static int samp_total;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samp);

        total_cal();
        orders_list_init();
    }


    public void samp_method(View view) {
        bowl_of_samp = inc(bowl_of_samp);
        TextView tv = (TextView) findViewById(R.id.samp_number);
        tv.setText("" + bowl_of_samp);
        total_cal();
    }

    public void samp_method1(View view) {
        if (bowl_of_samp >= 0) {
            bowl_of_samp = dec(bowl_of_samp);
            TextView tv = (TextView) findViewById(R.id.samp_number);
            if (bowl_of_samp > 0) tv.setText("" + bowl_of_samp);
            else tv.setText("__");
            total_cal();
        }
    }


    public void total_cal() {
        samp_total = bowl_of_samp * (40);
        FinalizeOrder.all_total = Samp.samp_total + Jollof.jollof_total;
        if (FinalizeOrder.all_total > 0) {
            //TextView tv = (TextView) findViewById(R.id.dessert_tot_id);
            //tv.setText("" + "R" + (finalize_order.all_total));

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



        if (bowl_of_samp > 0) {
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

                                Intent nextact = new Intent((Samp) o, Thank_You.class);
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

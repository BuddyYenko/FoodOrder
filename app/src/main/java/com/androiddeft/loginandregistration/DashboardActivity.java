package com.androiddeft.loginandregistration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;
    static int new_order_flag = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        //TextView welcomeText = findViewById(R.id.welcomeText);

        //welcomeText.setText("Welcome "+user.getFirstName()+", your session will expire on "+user.getSessionExpiryDate());

        FinalizeOrder.all_total=Jollof.jollof_total+Samp.samp_total;
        TextView tv = (TextView) findViewById(R.id.total_on_mm);
        if(FinalizeOrder.all_total >0){
            tv.setText(""+"R" + FinalizeOrder.all_total);}
    }
    public void Jollof(View view)
    {
        Intent nextact=new Intent(this,Jollof.class);
        startActivity(nextact);
        //overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }
    public void Samp(View view)
    {
        Intent nextact=new Intent(this,Samp.class);
        startActivity(nextact);
        //overridePendingTransition(R.anim.right_left, R.anim.left_right);
    }

    public void fin_ord(View view)
    {
        final Object o= this;
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

                                Intent nextact = new Intent((DashboardActivity) o, Thank_You.class);
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

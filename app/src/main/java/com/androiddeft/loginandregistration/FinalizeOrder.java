package com.androiddeft.loginandregistration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class FinalizeOrder extends AppCompatActivity {

    FinalizeOrder a=this;
    public static Socket client;
    public static PrintWriter printwriter;
    public static BufferedReader bb;
    public static String messsage;
    public static String m1;
    static int old_all_total;
    static int all_total;

    String personal_preferances;
    Object oo;
    String fin_order_string="";
    public String S;
    static int next_ord_flag;
    static String order_string;
    static String old_ord_string="";

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_order);
        show_Order();
        tot_v();
        oo=this;
    }

    public void show_Order() {
        if (Jollof.plate_of_jollof > 0) {
            fin_order_string = fin_order_string + "Plate of Jollof Rice-" + String.valueOf(Jollof.plate_of_jollof) + ",";
        }
        if (Samp.bowl_of_samp > 0) {
            fin_order_string = fin_order_string + "Plate of Umngqusho-" + String.valueOf(Samp.bowl_of_samp) + ",";
        }

        String temp_order_view=fin_order_string+old_ord_string;
        //TextView tv = (TextView) findViewById(R.id.order_final);
        //tv.setText("" + temp_order_view);
    }

    public void main_menu(View view) {
        Intent goto_main_menu = new Intent(this, DashboardActivity.class);
        startActivity(goto_main_menu);
        //overridePendingTransition(R.anim.fadin, R.anim.fadout);
    }

    public void tot_v(){
        all_total=all_total+old_all_total;
        //TextView tv = (TextView) findViewById(R.id.tot_p);
        //tv.setText("total price:" + "R" +(FinalizeOrder.all_total));
    }



    public class SendMessage extends AsyncTask<Void, Void, Void> {
        BufferedReader bb;
        Socket client;
        PrintWriter printwriter;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                client = new Socket("192.168.1.105", 4444); // connect to the server
                printwriter = new PrintWriter(client.getOutputStream(), true);
                bb=new BufferedReader(new InputStreamReader(client.getInputStream()));
                printwriter.println(FinalizeOrder.messsage); // write the message to output stream
                printwriter.flush();
                m1=bb.readLine();
                //System.out.println("dd:"+m1);
                //m1="hello";
                bb.close();
                client.close(); // closing the connection
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void send_ord(View v){
        order_string=fin_order_string;

        //EditText Ed= (EditText) findViewById(R.id.personalp);

        //personal_preferances=Ed.getText().toString();

        final Object o = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to confirm this order?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //messsage = "Order:" + SlimpleTextClientActivity.tablex + "|" + fin_order_string + "|" + Integer.toString(all_total)+"|"+personal_preferances;
                        ; // get the text message on the text field
                        // messsage = "Order:" + SlimpleTextClientActivity.tablex + "|" + fin_order_string + "|" + Integer.toString(all_total);
                        SendMessage sendMessageTask = new SendMessage();
                        sendMessageTask.execute();
                        Intent nextact = new Intent((FinalizeOrder) o, Thank_You.class);
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
}

package com.androiddeft.loginandregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Thank_You extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank__you);
    }

    public void main_menu(View view)
    {
        FinalizeOrder.next_ord_flag=1;
        FinalizeOrder.old_ord_string=FinalizeOrder.order_string+FinalizeOrder.old_ord_string;
        FinalizeOrder.old_all_total=FinalizeOrder.all_total;

        Jollof.plate_of_jollof=0;
        Jollof.jollof_total=0;

        Samp.bowl_of_samp=0;
        Samp.samp_total=0;




        Intent menu= new Intent(this,DashboardActivity.class);
        startActivity(menu);

    }
}

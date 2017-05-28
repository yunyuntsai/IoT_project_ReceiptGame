package i.iot_project_receiptapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import i.iot_project_receiptapp.Data.ReceiptList;
import i.iot_project_receiptapp.Data.ShareActionHelper;
import i.iot_project_receiptapp.ReceiptDataBase.RaffleReceipt;
import i.iot_project_receiptapp.ReceiptMng.MonthPeriod;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class ResultActivity extends AppCompatActivity{
    TextView tv_super,tv_special,tv_one,tv_two,tv_three,tv_four,tv_five,tv_six,tv_tal,
            tv_period,tv_buns;
    ReceiptList mReceiptData;
    Toolbar mToolbar;
    String year;
    int period;
    RaffleReceipt report;
    ShareActionHelper mShareActionHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Bundle bd =getIntent().getExtras();
        if(bd!=null){
            year = bd.getString("year");
            period = bd.getInt("peroid");
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mShareActionHelper = new ShareActionHelper(this);
       /* mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem arg0) {
                // TODO Auto-generated method stub
                if(arg0.getItemId()== R.id.share){
                    mShareActionHelper.setShareIntent();
                }
                return true;
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        mReceiptData = new ReceiptList(this);
        tv_period =  (TextView) findViewById(R.id.period);
        tv_super = (TextView) findViewById(R.id.textView1);
        tv_special = (TextView) findViewById(R.id.textView2);
        tv_one = (TextView) findViewById(R.id.textView3);
        tv_two = (TextView) findViewById(R.id.textView4);
        tv_three = (TextView) findViewById(R.id.textView5);
        tv_four = (TextView) findViewById(R.id.textView6);
        tv_five = (TextView) findViewById(R.id.textView7);
        tv_six = (TextView) findViewById(R.id.textView8);
        tv_tal = (TextView) findViewById(R.id.textView9);
        tv_buns = (TextView) findViewById(R.id.textView10);
        report  = mReceiptData.getReport(year, period);
        updateUI(report);
    }


    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.report, menu);
        return true;
    }*/

    public void updateUI(RaffleReceipt report){
        if(report==null)
            return;
        tv_period.setText(report.getYear()+"年 "+MonthPeriod.values()[report.getPeriod()].getString(this));
        tv_super.setText(String.valueOf(report.getSuperAwardsCount()));
        tv_special.setText(String.valueOf(report.getSpecialAwardsCount()));
        tv_one.setText(String.valueOf(report.getOneAwardsCount()));
        tv_two.setText(String.valueOf(report.getTwoAwardsCount()));
        tv_three.setText(String.valueOf(report.getThreeAwardsCount()));
        tv_four.setText(String.valueOf(report.getFourAwardsCount()));
        tv_five.setText(String.valueOf(report.getFiveAwardsCount()));
        tv_six.setText(String.valueOf(report.getSixAwardsCount()));
        int bouns = report.getSuperAwardsCount()*10000000+
                report.getSpecialAwardsCount()*2000000+
                report.getOneAwardsCount()*200000+
                report.getTwoAwardsCount()*40000+
                report.getThreeAwardsCount()*10000+
                report.getFourAwardsCount()*4000+
                report.getFiveAwardsCount()*1000+
                report.getSixAwardsCount()*200;
        tv_buns.setText(String.valueOf(bouns));
        tv_tal.setText(String.valueOf(report.getTalcount()));

    }

}

package i.iot_project_receiptapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import i.iot_project_receiptapp.Data.ReceiptList;
import i.iot_project_receiptapp.ReceiptDataBase.Receipt;
import i.iot_project_receiptapp.ReceiptMng.receiptMng;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = "MONEY: ";
    public final static int BASE_YEAR = 106;
    private TextView moneytxt;
    public int Totalmoney;
    ReceiptList mReceiptData;
    public List<Integer> moneyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mReceiptData = new ReceiptList(this);
        initViewPager();

        initMoneyCounter();
        //myStorage = new MyStorage(mReceiptData);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        com.github.clans.fab.FloatingActionButton fabSearch = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item1);
        assert fabSearch != null;
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent();
                searchIntent.setClass(MainActivity.this, MyStorage.class);
                startActivity(searchIntent);
            }
        });
        com.github.clans.fab.FloatingActionButton fabMessenger = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item2);
        assert fabMessenger != null;
        fabMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Uri uri = Uri.parse("fb-messenger://user/");
                String peopleId = "1054916604587192";
                //Log.d("id", ""+ LoginActivity.id.toString());
                uri = ContentUris.withAppendedId(uri, Long.parseLong(peopleId));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                */
              /*  Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent
                        .putExtra(Intent.EXTRA_TEXT,
                                "<---YOUR TEXT HERE--->.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.facebook.orca");
                try
                {
                    startActivity(sendIntent);
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    //ToastHelper.MakeShortText("Please Install Facebook Messenger");
                }*/
                Intent searchIntent = new Intent();
                searchIntent.setClass(MainActivity.this,MyStorage.class);
                startActivity(searchIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViewPager(){
        List<Fragment> fragments = getFragments();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(adapter);
    }
    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PageFragment.newInstance("a", R.drawable.background640480));
        fragments.add(PageFragment.newInstance("b", R.drawable.blue640480));
        fragments.add(PageFragment.newInstance("c", R.drawable.green640480));

        return fragments;
    }


    private void initMoneyCounter(){

        if (moneyList.size() == 0) {
            moneyList.add(0, 0);
            moneytxt = (TextView) findViewById(R.id.TextView10);
            moneytxt.setText(String.valueOf(moneyList.get(0)));
        }

    }

    private void updateMoneyCounter(int money){
        Totalmoney = Totalmoney + money;
        moneyList.add(0,Totalmoney);
        displayMoney(moneyList.get(0));

    }

    private void displayMoney(int money) {
        moneytxt.setText(String.valueOf(money));
        Log.d(TAG,String.valueOf(money));

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_QRcode) {
            toScan();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(MainActivity.this, MyStorage.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(MainActivity.this, TesseractActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent i = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("SCAN_RESULT");
                if(receiptMng.parserQRcode(mReceiptData,result)==1){
                    showNextDialog(R.string.add_success);//新增成功
                    updateMoneyCounter(10);

                }else{
                    showNextDialog(R.string.add_isExitTip);//統一發票已存在
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }
    private void showNextDialog(int id){
        showNextDialog(getString(id));
    }
    private void showNextDialog(String msg){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle(msg);
        ab.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                arg0.cancel();
            }
        });
        ab.setPositiveButton(R.string.continue_scan, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                toScan();
                arg0.cancel();
            }
        });
        ab.create().show();
    }

    /**儲存統一發票*/
    private boolean  saveReceipt(String code,String number,String date){
        if(!code.matches("[A-Z][A-Z]")||number.length()!=8){
            Toast.makeText(this, getString(R.string.input_tip_current_number), Toast.LENGTH_SHORT).show();
            return false;
        }

        Receipt receipt = new Receipt();
        receipt.setCreateDate(date);
        receipt.setPeriod(receiptMng.getPeriod(Integer.valueOf(date.substring(3, 5))).ordinal());
        receipt.setReceiptID(code+number);
        receipt.setStatus(false);
        receipt.setAwards(0);
        if(mReceiptData.addReceipt(receipt)==ReceiptList.RECEIPT_IS_EXIST){
            Toast.makeText(this, getString(R.string.add_isExitTip), Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(this, getString(R.string.add_success), Toast.LENGTH_SHORT).show();
        return true;
    }

    public void toScan() {
        try {

            Intent intent = new Intent(
                    "com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, "ERROR:" + e, Toast.LENGTH_SHORT).show();

        }
    }



}
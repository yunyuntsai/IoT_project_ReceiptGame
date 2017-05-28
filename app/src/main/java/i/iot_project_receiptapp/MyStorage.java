package i.iot_project_receiptapp;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import i.iot_project_receiptapp.Data.InvoiceWeb;
import i.iot_project_receiptapp.Data.ReceiptList;
import i.iot_project_receiptapp.ReceiptDataBase.RaffleReceipt;
import i.iot_project_receiptapp.ReceiptDataBase.Receipt;
import i.iot_project_receiptapp.ReceiptMng.MonthPeriod;
import i.iot_project_receiptapp.ReceiptMng.WinningNumber;
import i.iot_project_receiptapp.ReceiptMng.ReceiptAwards;
import i.iot_project_receiptapp.ReceiptMng.receiptMng;

/**
 * Created by tsaiyunyun on 2017/5/17.
 */
public class MyStorage extends AppCompatActivity {

    String TAG = "MYSTORAGE";
    ReceiptList mReceiptData;
    private ListView mListView;
    private ListView nListView;
    Context context;
    //  DatabaseHelper dbHelper;
    //private static final String TAG = "Garage";
    //ReceiptManager RM = ReceiptManager.getRM(this);
    //MonthList ML = new MonthList() ;
    //ArrayList<Receipt> RList = RM.getScoreList();
    //ArrayAdapter<Receipt> MyArrayAdapter;
    public String MONTH;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        //nListView = (ListView)findViewById(R.id.listView1);
        mListView = (ListView) findViewById(R.id.listView);
        mReceiptData = new ReceiptList(this);
        context = this;

        String[] values = new String[]{
                "Jan - February",
                "March - April",
                "May - June",
                "July - August",
                "September - October",
                "November - December",
        };
        //丟入你要顯示的文字

        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 ,values);
        //使用ListAdapter來顯示你輸入的文字

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                ListView listView = (ListView) arg0;
                Toast.makeText(
                        MyStorage.this,
                        "ID：" + arg3 +
                                "   選單文字：" + listView.getItemAtPosition(arg2).toString(),
                        Toast.LENGTH_LONG).show();
                String month = listView.getItemAtPosition(arg2).toString();
                Log.v(TAG, month);

                MonthList.setMonth(arg3);
                Intent i = new Intent(MyStorage.this, MonthList.class);
                startActivity(i);

            }

        });

        com.github.clans.fab.FloatingActionButton fabSearch = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item1);
        assert fabSearch != null;
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCheckReceiptDialog();
                //Intent searchIntent = new Intent();
                //searchIntent.setClass(MyStorage.this, MyStorage.class);
                //startActivity(searchIntent);



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
                searchIntent.setClass(MyStorage.this,MyStorage.class);
                startActivity(searchIntent);
            }
        });



        }
        /*ArrayList<String> thelist = new ArrayList<>();
        Cursor Data = dbHelper.getListContent();
        if (Data.getCount()==0){
            Toast.makeText(MyStorage.this,"database is empty" , Toast.LENGTH_SHORT).show();
        }else{
            while(Data.moveToNext()){
                thelist.add(Data.getString(1));
                Log.i("OUTPUT ",Data.getString(1));
                ListAdapter MyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);
                mList.setAdapter(MyAdapter);
            }
        }*/
        // dbHelper.close();

       /* MyArrayAdapter = new ArrayAdapter<Receipt>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                RList) {
            @Override
            public View getView(int pos, View convert, ViewGroup group) {
                View v = super.getView(pos, convert, group);
                TextView t1 = (TextView) v.findViewById(android.R.id.text1);
                t1.setText(getItem(pos).getNumber());
                return v;
            }
        } ;
        mList.setAdapter(MyArrayAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Overridei/iot_project_receiptapp/MyStorage.java:17
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long l) {
                //Adapter adapter = adapterView.getAdapter();
                Receipt clicked = (Receipt) MyArrayAdapter.getItem(position);
                String T = clicked.getNumber();
            }
        }
            );*/




    ProgressDialog progressDialog;
    private void showCheckReceiptDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.search_numbers_wait));//查詢對獎號碼
        progressDialog.show();
        InvoiceWeb.query(new InvoiceWeb.CallBack() {

            @Override
            public void onLoadNumbers(final String year, final MonthPeriod period, final WinningNumber numbers) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();
                        progressDialog.setMessage(getString(R.string.check_Receipt_wait));//對獎中
                        AlertDialog.Builder ab = new AlertDialog.Builder(context);
                        ab.setTitle(getString(R.string.select_award_date));//v選擇對講日期
                        List<String> data = new ArrayList<String>();
                        data.add(year + " " + getString(R.string.year_unit) + " " + period.getString(context));
                        ab.setAdapter(new ArrayAdapter<String>(context, R.layout.datelistitem, data), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                progressDialog.show();
                                List<Receipt> datas = mReceiptData.getNotCheckReceiptList(year, period.ordinal());
                                for (int i = 0; i < datas.size(); i++) {
                                    Receipt receipt = datas.get(i);
                                    ReceiptAwards awards = receiptMng.CheckReceiptAwards(numbers, receipt.getReceiptID());
                                    receipt.setAwards(awards.ordinal());
                                    receipt.setStatus(true);
                                    mReceiptData.updateReceipt(receipt);
                                }
                                update(year, period.ordinal());
                                toReport(year, period);
                                dialog.cancel();
                                progressDialog.cancel();
                            }
                        });
                        ab.setPositiveButton(R.string.alert_cancel, null);
                        ab.create().show();
                    }
                });

            }
        });

    }

    public void toReport(String year,MonthPeriod period){
        Intent it = new Intent(MyStorage.this, ResultActivity.class);
        Bundle bd = new Bundle();
        bd.putString("year", year);
        bd.putInt("peroid", period.ordinal());
        it.putExtras(bd);
        startActivity(it);
    }

    private void update(String year,int period){
        List<Receipt> list = mReceiptData.getReceiptList(year, period);
        int superTal = 0;
        int specialTal = 0;
        int oneTal = 0;
        int twoTal = 0;
        int threeTal = 0;
        int fourTal = 0;
        int fiveTal = 0;
        int sixTal = 0;
        int talcount = 0;
        for(Receipt item:list ){
            if(item.getStatus()){
                talcount++;
                switch (item.getAwards()) {
                    case 1:
                        sixTal++;
                        break;
                    case 2:
                        fiveTal++;
                        break;
                    case 3:
                        fourTal++;
                        break;
                    case 4:
                        threeTal++;
                        break;
                    case 5:
                        twoTal++;
                        break;
                    case 6:
                        oneTal++;
                        break;
                    case 7:
                        specialTal++;
                        break;
                    case 8:
                        superTal++;
                        break;

                    default:
                        break;
                }
            }
        }
        RaffleReceipt report = new RaffleReceipt();
        report.setYear(year);
        report.setPeriod(period);
        report.setSixAwardsCount(sixTal);
        report.setFiveAwardsCount(fiveTal);
        report.setFourAwardsCount(fourTal);
        report.setThreeAwardsCount(threeTal);
        report.setTwoAwardsCount(twoTal);
        report.setOneAwardsCount(oneTal);
        report.setSpecialAwardsCount(specialTal);
        report.setSuperAwardsCount(superTal);
        report.setTalcount(talcount);
        mReceiptData.updateReport(report);
    }
    ListPopupWindow popupWindow;

    public ListPopupWindow getListPopupWindow(View  view , final Receipt receipt){

        popupWindow  = new ListPopupWindow(context);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        popupWindow.setContentWidth(dm.widthPixels/4);
        popupWindow.setAnchorView(view);
        popupWindow.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,
                context.getResources().getStringArray(R.array.edit)));
        popupWindow.setModal(true);

        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                switch (position){
                    case 0:
                        showEditeReceipt(receipt);
                        break;
                    case 1:
                        showDeleteTip(receipt);
                        break;
                }
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }
    private void showDeleteTip(final Receipt receipt){
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle(R.string.delete_tip);
        String msg = getString(R.string.delete_tip_receipt);
        msg = msg.replace("&id",receipt.getReceiptID());
        msg = msg.replace("&date",receipt.getCreateDate());
        ab.setMessage(msg);
        ab.setNegativeButton(R.string.alert_cancel,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ab.setPositiveButton(R.string.alert_ok,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mReceiptData.deleteReceipt(receipt);
               // updateCurrntValue();
                Toast.makeText(context,getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        ab.create().show();
    }
    private void showEditeReceipt(final Receipt receipt){
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle(R.string.repair_receipt);
        View convertView = LayoutInflater.from(context).inflate(R.layout.inputdialog, null);
        final EditText et_code = (EditText) convertView.findViewById(R.id.et_code);
        final EditText et_number = (EditText) convertView.findViewById(R.id.et_number);
        final DatePicker datePicker = (DatePicker) convertView.findViewById(R.id.datePicker1);
        et_code.setText(receipt.getReceiptID().substring(0,2));
        et_number.setText(receipt.getReceiptID().substring(2));
        datePicker.setCalendarViewShown(false);
        int year =Integer.valueOf(receipt.getCreateDate().substring(0,3))+1911;
        int month =Integer.valueOf(receipt.getCreateDate().substring(3, 5));
        int day =Integer.valueOf(receipt.getCreateDate().substring(5));
        datePicker.init(
                year,
                month-1,
                day,null);
        ab.setView(convertView);
        ab.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                arg0.cancel();
            }
        });
        ab.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                String date = YearFix(datePicker.getYear());
                date += DateFix(datePicker.getMonth()+1);
                date += DateFix(datePicker.getDayOfMonth());
                String receiptId = et_code.getText().toString()+et_number.getText().toString();
                if(!date.equals(receipt.getCreateDate())
                        ||!(receiptId).equals(receipt.getReceiptID())) {
                    receipt.setCreateDate(date);
                    receipt.setReceiptID(receiptId);
                    mReceiptData.updateReceipt(receipt);
                    receipt.setStatus(false);
                    //updateCurrntValue();
                    Toast.makeText(context,getString(R.string.repair_success),Toast.LENGTH_SHORT).show();
                }
                arg0.cancel();

            }
        });
        ab.create().show();
    }
    //西元轉民國
    private  String YearFix(int year){
        return String.valueOf(year-1911);
    }
    //修正日期
    private  String DateFix(int c){
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


}
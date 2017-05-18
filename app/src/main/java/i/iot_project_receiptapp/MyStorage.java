package i.iot_project_receiptapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
/**
 * Created by tsaiyunyun on 2017/5/17.
 */
public class MyStorage extends AppCompatActivity {

  //  DatabaseHelper dbHelper;
    //private static final String TAG = "Garage";
    ReceiptManager RM = ReceiptManager.getRM(this);
    MonthList ML = new MonthList() ;
    //ArrayList<Receipt> RList = RM.getScoreList();
    //ArrayAdapter<Receipt> MyArrayAdapter;
    public String MONTH;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ListView mList = (ListView) findViewById(R.id.listView);
       // dbHelper = new DatabaseHelper(this);



        String[] values = new String[]{
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",
        };
        //丟入你要顯示的文字

        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 ,values);
        //使用ListAdapter來顯示你輸入的文字

        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
           public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                ListView listView = (ListView) arg0;
                Toast.makeText(
                        MyStorage.this,
                        "ID：" + arg3 +
                                "   選單文字："+ listView.getItemAtPosition(arg2).toString(),
                        Toast.LENGTH_LONG).show();
                String month = listView.getItemAtPosition(arg2).toString();
                MonthList.setMonth(month);
                Intent i = new Intent(MyStorage.this, MonthList.class);
                startActivity(i);


            }
        });


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

    }









}

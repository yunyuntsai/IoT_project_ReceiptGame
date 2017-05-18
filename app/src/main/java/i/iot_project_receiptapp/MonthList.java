package i.iot_project_receiptapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by tsaiyunyun on 2017/5/18.
 */
public class MonthList extends AppCompatActivity {
    ListView List;
    ListAdapter adapter;
    public static final int CHOICE_MODE_MULTIPLE = 2;
    private static String MONTH;
    ReceiptManager RM = ReceiptManager.getRM(this);
    //MyStorage ST = new MyStorage();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_single);
        List = (ListView) findViewById(R.id.listView1);
        //Log.i("OUTPUT ", MONTH);
        Toast.makeText(MonthList.this, getMonth(), Toast.LENGTH_LONG).show();
        adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_multiple_choice , RM.getList(getMonth()));
        List.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        List.setItemChecked(2, true);
        List.setAdapter(adapter);

        // Capture ListView item click
        /*int count = List.getCount();
        SparseBooleanArray checkedItemPositions = List.getCheckedItemPositions();
        for (int i=0;i < count;i++) {
            if(!checkedItemPositions.get(i)) UPdatelist(i);
            else {

            }
        }*/

    }

   public void UPdatelist(int i){
        ArrayList<String> updatedList = new ArrayList<String>(); //initialize the second ArrayList
        ListView List = (ListView) findViewById(R.id.listView1);

       updatedList.add( RM.getList(getMonth()).get(i));
       ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, updatedList);

        List.setAdapter(adapter);
    }






    public static void setMonth(String id){
        //i/iot_project_receiptapp/MyStorage.java:17
        MONTH = id;
    }

    public String getMonth(){
        return MONTH;
    }
}

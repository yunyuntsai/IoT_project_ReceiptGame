package i.iot_project_receiptapp;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
/**
 *
 * Created by tsaiyunyun on 2017/5/11.
 */
public class Receipt extends Activity{

    ListView list;
    String tag = "receipt";
    public  int ID;
    public int MONTH;
    private String NUMBER;

    public Receipt(){
        this.ID = -1;
        this.MONTH = -1;
        this.NUMBER = "new";
    }

    public void setId(int id){
       // Log.v("save fail. the score has an id");
        ID = id;
        return;
    }

    public int getId(){
        return this.ID;
    }

    public void setMonth(int id){
        Log.e(tag,"i am here");
        MONTH = id;
    }

    public int getMonth(){
        return this.MONTH;
    }

    public boolean setNumber(String name){
        NUMBER = name;
        return true;
    }

    public String getNumber(){
        return this.NUMBER;
    }




    /*String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.drawable.side_nav_bar,
            R.drawable.side_nav_bar,
            R.drawable.side_nav_bar,
            R.drawable.side_nav_bar,
            R.drawable.side_nav_bar,
            R.drawable.side_nav_bar,
            R.drawable.side_nav_bar

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ReceiptList adapter = new ReceiptList(Storage.this, web, imageId);
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Storage.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }*/


}

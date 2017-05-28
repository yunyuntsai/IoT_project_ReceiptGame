package i.iot_project_receiptapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import i.iot_project_receiptapp.Data.ReceiptList;
import i.iot_project_receiptapp.ReceiptDataBase.Receipt;
import i.iot_project_receiptapp.view.SwipeListLayout;

/**
 * Created by tsaiyunyun on 2017/5/18.
 */
public class MonthList extends AppCompatActivity {
    String TAG = "Monthlist";
    ListView mListView;
    ArrayAdapter<String> adapter;
    public static final int CHOICE_MODE_MULTIPLE = 2;
    private static long MONTH;
    ReceiptList mReceiptData;
    //MyStorage ST = new MyStorage();
    private Set<SwipeListLayout> sets = new HashSet();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_single);
        //nListView = (ListView)findViewById(R.id.listView1);
        mListView = (ListView) findViewById(R.id.listView1);
        mReceiptData = new ReceiptList(this);
        //Log.i("OUTPUT ", MONTH);


        if (getMonth() == 0) populateListView1();
        else if (getMonth() == 1) populateListView2();
        else if (getMonth() == 2) populateListView3();
        else if (getMonth() == 3) populateListView4();
        else if (getMonth() == 4) populateListView5();
        /////////////////////delete////////////
        mListView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i("ww","ww");
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        if (sets.size() > 0) {
                            for (SwipeListLayout s : sets) {
                                s.setStatus(SwipeListLayout.Status.Close, true);
                                sets.remove(s);
                            }
                        }
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
        ///////////////////////////////////
        // Capture ListView item click
        /*int count = List.getCount();
        SparseBooleanArray checkedItemPositions = List.getCheckedItemPositions();
        for (int i=0;i < count;i++) {
            if(!checkedItemPositions.get(i)) UPdatelist(i);
            else {
            }
        }*/

    }

    private void populateListView1(){
        Log.d(TAG,"populateListView : displaying data in the listview");

        List<Receipt> data = mReceiptData.getReceiptList("106",0);
        ArrayList<String> ListData = new ArrayList<>();
        if(data.size()>0) {
            for (Receipt item:data){
                ListData.add(item.getReceiptID());
            }
        }
        android.widget.ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListData);
        mListView.setAdapter(adapter);
        mListView.setAdapter(new ListAdapter());

    }
    private void populateListView2(){
        Log.d(TAG,"populateListView : displaying data in the listview");

        List<Receipt> data = mReceiptData.getReceiptList("106",1);
        ArrayList<String> ListData = new ArrayList<>();
        if(data.size()>0) {
            for (Receipt item:data){
                ListData.add(item.getReceiptID());
            }
        }
        android.widget.ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListData);
        mListView.setAdapter(adapter);
        mListView.setAdapter(new ListAdapter());

    }
    private void populateListView3(){
        Log.d(TAG,"populateListView : displaying data in the listview");

        List<Receipt> data = mReceiptData.getReceiptList("106",2);
        ArrayList<String> ListData = new ArrayList<>();
        if(data.size()>0) {
            for (Receipt item:data){
                ListData.add(item.getReceiptID());
            }
        }
        android.widget.ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListData);
        mListView.setAdapter(adapter);
        mListView.setAdapter(new ListAdapter());

    }
    private void populateListView4(){
        Log.d(TAG,"populateListView : displaying data in the listview");

        List<Receipt> data = mReceiptData.getReceiptList("106",3);
        ArrayList<String> ListData = new ArrayList<>();
        if(data.size()>0) {
            for (Receipt item:data){
                ListData.add(item.getReceiptID());
            }
        }
        android.widget.ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListData);
        mListView.setAdapter(adapter);
        mListView.setAdapter(new ListAdapter());

    }
    private void populateListView5(){
        Log.d(TAG,"populateListView : displaying data in the listview");

        List<Receipt> data = mReceiptData.getReceiptList("106",4);
        ArrayList<String> ListData = new ArrayList<>();
        if(data.size()>0) {
            for (Receipt item:data){
                ListData.add(item.getReceiptID());
            }
        }
        android.widget.ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListData);
        mListView.setAdapter(adapter);
        mListView.setAdapter(new ListAdapter());

    }

    private  void ToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    public static void setMonth(long id){
        //i/iot_project_receiptapp/MyStorage.java:17
        MONTH = id;
    }

    public int getMonth(){
        return (int)MONTH;
    }

    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

        private SwipeListLayout slipListLayout;

        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
            } else {
                if (sets.contains(slipListLayout))
                    sets.remove(slipListLayout);
            }
        }

        @Override
        public void onStartCloseAnimation() {

        }

        @Override
        public void onStartOpenAnimation() {

        }

    }

    class ListAdapter extends BaseAdapter {
        List<Receipt> data = mReceiptData.getReceiptList("106",getMonth());
        @Override
        public int getCount() {
            return mReceiptData.getReceiptList("106",getMonth()).size();
        }

        @Override
        public Object getItem(int arg0) {
            return data.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int arg0, View view, ViewGroup arg2) {
            if (view == null) {
                view = LayoutInflater.from(MonthList.this).inflate(
                        R.layout.slip_list_item, null);
            }
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(data.get(arg0).getReceiptID());
            final SwipeListLayout sll_main = (SwipeListLayout) view
                    .findViewById(R.id.sll_main);
            TextView tv_top = (TextView) view.findViewById(R.id.tv_top);
            TextView tv_delete = (TextView) view.findViewById(R.id.tv_delete);
            sll_main.setOnSwipeStatusListener(new MyOnSlipStatusListener(
                    sll_main));
            tv_top.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    sll_main.setStatus(SwipeListLayout.Status.Close, true);
                    Receipt receipt = data.get(arg0);
                    mReceiptData.deleteReceipt(data.get(arg0));
                    mReceiptData.getReceiptList("106",getMonth()).add(0,receipt);
                    notifyDataSetChanged();
                }
            });
            tv_delete.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    sll_main.setStatus(SwipeListLayout.Status.Close, true);
                    mReceiptData.deleteReceipt(data.get(arg0));
                    notifyDataSetChanged();
                }
            });
            return view;
        }


    }
    }
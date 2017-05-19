package i.iot_project_receiptapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import i.iot_project_receiptapp.view.SwipeListLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by tsaiyunyun on 2017/5/18.
 */
public class MonthList extends AppCompatActivity {
    ListView List;
    ArrayAdapter<String> adapter;
    public static final int CHOICE_MODE_MULTIPLE = 2;
    private static String MONTH;
    ReceiptManager RM = ReceiptManager.getRM(this);
    //MyStorage ST = new MyStorage();
    private Set<SwipeListLayout> sets = new HashSet();
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
        List.setAdapter(new ListAdapter());
        /////////////////////delete////////////
        List.setOnScrollListener(new OnScrollListener() {

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

    public void UPdatelist(int i){
        ArrayList<String> updatedList = new ArrayList<String>(); //initialize the second ArrayList
        ListView List = (ListView) findViewById(R.id.listView1);

        updatedList.add( RM.getList(getMonth()).get(i));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
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

        @Override
        public int getCount() {
            return RM.getList(getMonth()).size();
        }

        @Override
        public Object getItem(int arg0) {
            return RM.getList(getMonth()).get(arg0);
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
            tv_name.setText(RM.getList(getMonth()).get(arg0));
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
                    String str = RM.getList(getMonth()).get(arg0);
                    RM.getList(getMonth()).remove(arg0);
                    RM.getList(getMonth()).add(0, str);
                    notifyDataSetChanged();
                }
            });
            tv_delete.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    sll_main.setStatus(SwipeListLayout.Status.Close, true);
                    RM.getList(getMonth()).remove(arg0);
                    notifyDataSetChanged();
                }
            });
            return view;
        }

    }
}
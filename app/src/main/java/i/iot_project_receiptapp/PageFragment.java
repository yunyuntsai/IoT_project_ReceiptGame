package i.iot_project_receiptapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PageFragment extends Fragment {

    private final String TAG = "PageFragment";
    public static final String TITLE = "TITLE";
    public static final String IMAGE = "IMAGE";

    private String title = "";
    private int resImageId = 0;

    public static final PageFragment newInstance(String title, int resImageId){
        PageFragment f = new PageFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(TITLE, title);
        bdl.putInt(IMAGE, resImageId);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getString(TITLE);
        resImageId = getArguments().getInt(IMAGE);

        Log.d(TAG, title + " - onCreate");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, title + " - onCreateView");
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, title + " - onViewCreated");

        TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        ImageView img = (ImageView) view.findViewById(R.id.imageView1);
        txtTitle.setText(title);
        img.setImageResource(resImageId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, title + " - onActivityCreated");

        Toast.makeText(getActivity(), "Create View " + title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, title + " - onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, title + " - onDestroy");
        super.onDestroy();
    }
}
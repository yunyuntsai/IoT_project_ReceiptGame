package i.iot_project_receiptapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.google.zxing.integration.android.IntentIntegrator.QR_CODE_TYPES;

/*
 * Created by tsaiyunyun on 2017/5/11.
 */
public class QRreader extends Activity {

    private Button scan_btn;
    DatabaseHelper dB;
    ReceiptManager RM = ReceiptManager.getRM(this);
    Receipt receipt = RM.create();
    //String receipt;
    public String month;
    public String number;

    /*public ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
    public ArrayList<String> January = new ArrayList<String>();
    public ArrayList<String> February = new ArrayList<String>();
    public ArrayList<String> March = new ArrayList<String>();
    public ArrayList<String> April = new ArrayList<String>();
    public ArrayList<String> May = new ArrayList<String>();
    public ArrayList<String> June = new ArrayList<String>();
    public ArrayList<String> July = new ArrayList<String>();
    public ArrayList<String> August = new ArrayList<String>();
    public ArrayList<String> September = new ArrayList<String>();
    public ArrayList<String> October = new ArrayList<String>();
    public ArrayList<String> November = new ArrayList<String>();
    public ArrayList<String> December = new ArrayList<String>();
    public void getMonth()
    {
        list1.add(January);
        list1.add(February);
        list1.add(March);
        list1.add(April);
        list1.add(May);
        list1.add(June);
        list1.add(July);
        list1.add(August);
        list1.add(September);
        list1.add(October);
        list1.add(November);
        list1.add(December);
    }   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qreader);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;

        scan_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //dB.del();
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancel the scanning", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                //Intent intent = getIntent();
                //number = intent.getStringExtra("num");
                //month = intent.getStringExtra("month");
                //list.setAdapter(adapter);
                String month = result.getContents().substring(13, 15);
                int m = Integer.parseInt(month);
                receipt.setMonth(m);

                String number = result.getContents().substring(2, 10);
                //Log.i("OUTPUT ",result.getContents().substring(2,10));
                receipt.setNumber(number);
                Log.i("OUTPUT ", receipt.getNumber());
                RM.save(receipt);
                //dB.addReceipt(receipt);
                Toast.makeText(QRreader.this, result.getContents(), Toast.LENGTH_SHORT).show();
               /* list1.add(January);
                list1.add(February);
                list1.add(March);
                list1.add(April);
                list1.add(May);
                list1.add(June);
                list1.add(July);
                list1.add(August);
                list1.add(September);
                list1.add(October);
                list1.add(November);
                list1.add(December);

                switch(m) {
                    case 1:
                        January.add(number);
                        break;
                    case 2:
                        February.add(number);
                        break;
                    case 3:
                        March.add(number);
                        break;
                    case 4:
                        April.add(number);
                        break;
                    case 5:
                        //System.out.println(number);
                        May.add(number);
                        // System.out.println(May.get(0));
                        break;
                    case 6:
                        June.add(number);
                        break;
                    case 7:
                        July.add(number);
                        break;
                    case 8:
                        August.add(number);
                        break;
                    case 9:
                        September.add(number);
                        break;
                    case 10:
                        August.add(number);
                        break;
                    case 11:
                        November.add(number);
                        break;
                    case 12:
                        December.add(number);
                        break;
                    default:
                        break;
                }  */


                //AddData(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
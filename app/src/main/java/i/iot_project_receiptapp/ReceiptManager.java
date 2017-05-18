package i.iot_project_receiptapp;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
public class ReceiptManager {

    private static final String TAG = "ReceiptManager";
    private static ReceiptManager RM;
    private Context context;
    private DatabaseHelper dbHelper;
    public ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
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
    }

    private ReceiptManager(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    public static ReceiptManager getRM(Context context){
        if(RM == null) RM = new ReceiptManager(context);
        return RM;
    }


    public boolean save(Receipt receipt) {

        /*if (storage.getNumber() != -1) {
            Log.d(TAG, "save fail. the score has an id");
            return false;
        }*/
        //int id = dbHelper.addReceipt(receipt);
        //receipt.setId(id);
        writeToInternalStorage(receipt);
        return true;
    }

    public Receipt create(){
        Receipt s = new Receipt();
        return s;
    }

    public ArrayList<String> getList(String month) {
        Log.e("TAG","HIIII~");
        ArrayList<String> receiptList = new ArrayList<String>();
         if (month=="January") receiptList = January;
        else if(month=="February") receiptList = February;
        else if(month=="February") receiptList = February;
        else if(month=="March") receiptList = March;
        else if(month=="April") receiptList = April;
        else if(month=="May") receiptList = May;
        else if(month=="June") receiptList = June;
        else if(month=="July") receiptList = July;
        else if(month=="August")receiptList = August;
        else if(month=="September")receiptList = September;
        else if(month=="October")receiptList =October;
        else if(month=="November")receiptList = November;
         else if (month=="December")receiptList = December;

        return  receiptList;
    }

    private void writeToInternalStorage(Receipt receipt) {
        String fileName = String.valueOf(receipt.getId());

        int m= receipt.getMonth();
        String number = receipt.getNumber();
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
        }

        FileOutputStream fos;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            //fos.write(fileContent.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readScoreFromInternalSotrage(Receipt r) {

        String fileName = String.valueOf(r.getId());

        FileInputStream fis;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            fis = context.openFileInput(fileName);
            while (fis.available() > 0) {
                stringBuilder.append((char)fis.read());
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //convertStringToScore(score, stringBuilder.toString());
    }


}

package i.iot_project_receiptapp.Data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class SharePerferenceHelper {
    static SharedPreferences mSharedPreferences;
    static SharePerferenceHelper msharePerferenceHelper;

    SharePerferenceHelper(Context mContext){
        if(mSharedPreferences==null)
            mSharedPreferences=mContext.getSharedPreferences("ebook", Context.MODE_PRIVATE);
    }
    public static SharePerferenceHelper getIntent(Context mContext){
        if(mSharedPreferences==null)
            msharePerferenceHelper=new SharePerferenceHelper(mContext);
        return msharePerferenceHelper;
    }
    @SuppressLint("CommitPrefEdits")
    public void clear(){
        SharedPreferences.Editor mEditor=getEditor();
        if(mEditor!=null){
            mEditor.clear();
            mEditor.commit();
        }
    }
    public SharedPreferences.Editor getEditor(){
        if(mSharedPreferences!=null)
            return mSharedPreferences.edit();
        return null;
    }
    public  String getString(String key,String def){
        if(mSharedPreferences!=null)
            return mSharedPreferences.getString(key, def);
        return def;
    }
    public  boolean setString(String key,String value){
        SharedPreferences.Editor mEditor=getEditor();
        if(mEditor!=null){
            mEditor.putString(key, value);
            mEditor.commit();
            return true;
        }
        return false;
    }
    public  int getInt(String key,int def){
        if(mSharedPreferences!=null)
            return mSharedPreferences.getInt(key, def);
        return def;
    }
    public  boolean setInt(String key,int value){
        SharedPreferences.Editor mEditor=getEditor();
        if(mEditor!=null){
            mEditor.putInt(key, value);
            mEditor.commit();
            return true;
        }
        return false;
    }
    public  boolean getboolean(String key,boolean def){
        if(mSharedPreferences!=null)
            return mSharedPreferences.getBoolean(key, def);
        return def;
    }
    public  boolean setboolean(String key,boolean value){
        SharedPreferences.Editor mEditor=getEditor();
        if(mEditor!=null){
            mEditor.putBoolean(key, value);
            mEditor.commit();
            return true;
        }
        return false;
    }
}

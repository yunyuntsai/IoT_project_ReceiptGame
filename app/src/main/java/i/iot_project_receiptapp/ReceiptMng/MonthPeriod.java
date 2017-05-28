package i.iot_project_receiptapp.ReceiptMng;

import android.content.Context;
import i.iot_project_receiptapp.R;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public  enum MonthPeriod {
    one_TwoMonth,
    three_FourMonth,
    five_SixMonth,
    seven_EightMonth,
    nine_TenMonth,
    eleven_TwelveMonth;

    public String getString(Context context){
        return context.getResources().getStringArray(R.array.period)[this.ordinal()];
    }
}

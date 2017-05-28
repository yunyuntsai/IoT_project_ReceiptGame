package i.iot_project_receiptapp.ReceiptMng;
import i.iot_project_receiptapp.R;
/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public enum ReceiptAwards {
    /**沒中獎*/
    NONE,
    /**六獎:同期統一發票收執聯末3 位數號碼與頭獎中獎號碼末3 位相同者各得獎金2百元*/
    SixAwards,
    /**五獎:同期統一發票收執聯末4 位數號碼與頭獎中獎號碼末4 位相同者各得獎金1 千元*/
    FiveAwards,
    /**四獎	同期統一發票收執聯末5 位數號碼與頭獎中獎號碼末5 位相同者各得獎金4 千元*/
    FourAwards,
    /**三獎	同期統一發票收執聯末6 位數號碼與頭獎中獎號碼末6 位相同者各得獎金1 萬元*/
    ThreeAwards,
    /**二獎	同期統一發票收執聯末7 位數號碼與頭獎中獎號碼末7 位相同者各得獎金4 萬元*/
    TwoAwards,
    /**頭獎：同期統一發票收執聯8位數號碼與上列號碼相同者獎金20 萬元*/
    OneAwards,
    /**特獎:同期統一發票收執聯8位數號碼與上列號碼相同者獎金200 萬元*/
    SpecialAwards,
    /**特別獎:同期統一發票收執聯8位數號碼與上列號碼相同者獎金1,000 萬元*/
    SuperSpecialAwards
    ;
    public static int getImage(int index){

        switch (index) {
            case 0:
                return R.drawable.ic_launcher;
            case 1:
                return R.drawable.ic_launcher;
            case 2:
                return R.drawable.ic_launcher;
            case 3:
                return R.drawable.ic_launcher;
            case 4:
                return R.drawable.ic_launcher;
            case 5:
                return R.drawable.ic_launcher;
            case 6:
                return R.drawable.ic_launcher;
            case 7:
                return R.drawable.ic_launcher;
            case 8:
                return R.drawable.ic_launcher;
            default:
                break;
        }
        return R.drawable.ic_launcher;
    }

}

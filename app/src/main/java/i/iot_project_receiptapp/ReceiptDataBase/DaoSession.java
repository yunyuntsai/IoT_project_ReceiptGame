package i.iot_project_receiptapp.ReceiptDataBase;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig receiptDaoConfig;
    private final DaoConfig reportDaoConfig;

    private final ReceiptDB receiptDao;
    private final  RaffleReceiptDB reportDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        receiptDaoConfig = daoConfigMap.get(ReceiptDB.class).clone();
        receiptDaoConfig.initIdentityScope(type);

        reportDaoConfig = daoConfigMap.get( RaffleReceiptDB.class).clone();
        reportDaoConfig.initIdentityScope(type);

        receiptDao = new ReceiptDB(receiptDaoConfig, this);
        reportDao = new  RaffleReceiptDB(reportDaoConfig, this);

        registerDao(Receipt.class, receiptDao);
        registerDao( RaffleReceipt.class,  reportDao);
    }

    public void clear() {
        receiptDaoConfig.getIdentityScope().clear();
       reportDaoConfig.getIdentityScope().clear();
    }

    public ReceiptDB getReceiptDao() {
        return receiptDao;
    }

    public  RaffleReceiptDB getReportDao() {
        return  reportDao;
    }

}

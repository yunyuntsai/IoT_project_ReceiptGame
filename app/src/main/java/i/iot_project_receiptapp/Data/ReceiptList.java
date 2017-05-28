package i.iot_project_receiptapp.Data;

import android.content.Context;
import java.util.List;

import i.iot_project_receiptapp.ReceiptDataBase.RaffleReceipt;
import i.iot_project_receiptapp.ReceiptDataBase.Receipt;
import i.iot_project_receiptapp.ReceiptDataBase.DaoMaster;
import i.iot_project_receiptapp.ReceiptDataBase.DaoMaster.DevOpenHelper;


import de.greenrobot.dao.query.QueryBuilder;
import i.iot_project_receiptapp.ReceiptDataBase.ReceiptDB;
import i.iot_project_receiptapp.ReceiptDataBase.RaffleReceiptDB;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class ReceiptList {
    DevOpenHelper receiptDB;
    DevOpenHelper reportDB;
    DaoMaster daoMaster_read;
    DaoMaster daoMaster_write;
    public final static int RECEIPT_IS_EXIST = 2;
    public final static int RECEIPT_ADD_SUCCESS = 1;

    public ReceiptList(Context context){
        receiptDB = new DaoMaster.DevOpenHelper(context, "Receipt", null);
        reportDB = new DaoMaster.DevOpenHelper(context, "Report", null);
    }
    public int addReceipt(Receipt receipt){
        if(checkExist(receipt))
            return RECEIPT_IS_EXIST;
        daoMaster_write = new DaoMaster(receiptDB.getWritableDatabase());
        daoMaster_write.newSession().getReceiptDao().insertOrReplace(receipt);
        return RECEIPT_ADD_SUCCESS;
    }
    public void updateReceipt(Receipt receipt){
        daoMaster_write = new DaoMaster(receiptDB.getWritableDatabase());
        daoMaster_write.newSession().getReceiptDao().update(receipt);
    }

    public List<Receipt> getReceiptList(String year, int period){
        daoMaster_read = new DaoMaster(receiptDB.getReadableDatabase());
        QueryBuilder<Receipt> qb =daoMaster_read.newSession().getReceiptDao().queryBuilder();
        qb.orderDesc(ReceiptDB.Properties.Id);
        qb.where(
                ReceiptDB.Properties.CreateDate.like(year+"%"),
                ReceiptDB.Properties.Period.eq(period));

        return qb.list();
    }
    public void deleteReceipt(Receipt item){
        daoMaster_write = new DaoMaster(receiptDB.getWritableDatabase());
        daoMaster_write.newSession().getReceiptDao().delete(item);
    }
    public void deleteReceipt(String year,int period){
        daoMaster_write = new DaoMaster(receiptDB.getWritableDatabase());
        List<Receipt> data = getReceiptList(year,period);
        if(data.size()>0) {
            for (Receipt item:data)
                daoMaster_write.newSession().getReceiptDao().delete(item);
        }

    }
    public  List<Receipt> getReceiptList(){
        daoMaster_read = new DaoMaster(receiptDB.getReadableDatabase());
        QueryBuilder<Receipt> qb =daoMaster_read.newSession().getReceiptDao().queryBuilder();
        qb.orderDesc(ReceiptDB.Properties.Id);
        return qb.list();
    }
    public  List<Receipt> getNotCheckReceiptList(String year,int period){
        daoMaster_read = new DaoMaster(receiptDB.getReadableDatabase());
        QueryBuilder<Receipt> qb =daoMaster_read.newSession().getReceiptDao().queryBuilder();
        qb.where(ReceiptDB.Properties.Status.eq(false),
                ReceiptDB.Properties.CreateDate.like(year+"%"),
                ReceiptDB.Properties.Period.eq(period));
        return qb.list();
    }
    public boolean checkExist(Receipt receipt){
        daoMaster_read = new DaoMaster(receiptDB.getReadableDatabase());
        QueryBuilder<Receipt> qb =daoMaster_read.newSession().getReceiptDao().queryBuilder();
        qb.where(
                ReceiptDB.Properties.CreateDate.eq(receipt.getCreateDate()),
                ReceiptDB.Properties.ReceiptID.eq(receipt.getReceiptID()));
        return qb.list().size()!=0;
    }
    public boolean checkExist(RaffleReceipt report){
        daoMaster_read = new DaoMaster(reportDB.getReadableDatabase());
        QueryBuilder<RaffleReceipt> qb =daoMaster_read.newSession().getReportDao().queryBuilder();
        qb.where(
                RaffleReceiptDB.Properties.Year.eq(report.getYear()),
                RaffleReceiptDB.Properties.Period.eq(report.getPeriod()));
        return qb.list().size()!=0;
    }
    public void updateReport(RaffleReceipt report){
        List<RaffleReceipt> items = getReports(report.getYear(),report.getPeriod());
        daoMaster_write = new DaoMaster(reportDB.getWritableDatabase());
        if(items!=null){
            for(RaffleReceipt item:items)
                daoMaster_write.newSession().getReportDao().delete(item);
        }
        daoMaster_write.newSession().getReportDao().insertOrReplace(report);
    }
    public List<RaffleReceipt> getReports(String year,int period){
        daoMaster_read = new DaoMaster(reportDB.getReadableDatabase());
        QueryBuilder<RaffleReceipt> qb =daoMaster_read.newSession().getReportDao().queryBuilder();
        qb.where(
                RaffleReceiptDB.Properties.Year.eq(year),
                RaffleReceiptDB.Properties.Period.eq(period));
        if(qb.list().size()>0)
            return qb.list();
        return null;
    }
    public List<RaffleReceipt> getAllReports(){
        daoMaster_read = new DaoMaster(reportDB.getReadableDatabase());
        QueryBuilder<RaffleReceipt> qb =daoMaster_read.newSession().getReportDao().queryBuilder();
        if(qb.list().size()>0)
            return qb.list();
        return null;
    }
    public RaffleReceipt getReport(String year,int period){
        daoMaster_read = new DaoMaster(reportDB.getReadableDatabase());
        QueryBuilder<RaffleReceipt> qb =daoMaster_read.newSession().getReportDao().queryBuilder();
        qb.where(
                RaffleReceiptDB.Properties.Year.eq(year),
                RaffleReceiptDB.Properties.Period.eq(period));
        if(qb.list().size()>0)
            return qb.list().get(0);
        return null;
    }
}

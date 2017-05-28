package i.iot_project_receiptapp.ReceiptDataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class ReceiptDB extends AbstractDao<Receipt, Long> {

    public static final String TABLENAME = "RECEIPT";

    /**
     * Properties of entity Receipt.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ReceiptID = new Property(1, String.class, "receiptID", false, "RECEIPT_ID");
        public final static Property CreateDate = new Property(2, String.class, "createDate", false, "CREATE_DATE");
        public final static Property Period = new Property(3, Integer.class, "period", false, "PERIOD");
        public final static Property Status = new Property(4, Boolean.class, "status", false, "STATUS");
        public final static Property Awards = new Property(5, Integer.class, "awards", false, "AWARDS");
    };


    public ReceiptDB(DaoConfig config) {
        super(config);
    }

    public ReceiptDB(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'RECEIPT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'RECEIPT_ID' TEXT," + // 1: receiptID
                "'CREATE_DATE' TEXT," + // 2: createDate
                "'PERIOD' INTEGER," + // 3: period
                "'STATUS' INTEGER," + // 4: status
                "'AWARDS' INTEGER);"); // 5: awards
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'RECEIPT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Receipt entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String receiptID = entity.getReceiptID();
        if (receiptID != null) {
            stmt.bindString(2, receiptID);
        }

        String createDate = entity.getCreateDate();
        if (createDate != null) {
            stmt.bindString(3, createDate);
        }

        Integer period = entity.getPeriod();
        if (period != null) {
            stmt.bindLong(4, period);
        }

        Boolean status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(5, status ? 1l: 0l);
        }

        Integer awards = entity.getAwards();
        if (awards != null) {
            stmt.bindLong(6, awards);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public Receipt readEntity(Cursor cursor, int offset) {
        Receipt entity = new Receipt( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // receiptID
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // createDate
                cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // period
                cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // status
                cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // awards
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Receipt entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setReceiptID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPeriod(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setStatus(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setAwards(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Receipt entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(Receipt entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

}

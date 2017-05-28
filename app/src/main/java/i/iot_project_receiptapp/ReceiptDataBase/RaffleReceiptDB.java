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
public class RaffleReceiptDB extends AbstractDao<RaffleReceipt, Object> {

    public static final String TABLENAME = "REPORT";

    /**
     * Properties of entity Report.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Year = new Property(1, String.class, "year", false, "YEAR");
        public final static Property Period = new Property(2, Integer.class, "period", false, "PERIOD");
        public final static Property Talcount = new Property(3, Integer.class, "talcount", false, "TALCOUNT");
        public final static Property SuperAwardsCount = new Property(4, Integer.class, "superAwardsCount", false, "SUPER_AWARDS_COUNT");
        public final static Property SpecialAwardsCount = new Property(5, Integer.class, "specialAwardsCount", false, "SPECIAL_AWARDS_COUNT");
        public final static Property OneAwardsCount = new Property(6, Integer.class, "oneAwardsCount", false, "ONE_AWARDS_COUNT");
        public final static Property TwoAwardsCount = new Property(7, Integer.class, "twoAwardsCount", false, "TWO_AWARDS_COUNT");
        public final static Property ThreeAwardsCount = new Property(8, Integer.class, "threeAwardsCount", false, "THREE_AWARDS_COUNT");
        public final static Property FourAwardsCount = new Property(9, Integer.class, "fourAwardsCount", false, "FOUR_AWARDS_COUNT");
        public final static Property FiveAwardsCount = new Property(10, Integer.class, "fiveAwardsCount", false, "FIVE_AWARDS_COUNT");
        public final static Property SixAwardsCount = new Property(11, Integer.class, "sixAwardsCount", false, "SIX_AWARDS_COUNT");
    };


    public RaffleReceiptDB(DaoConfig config) {
        super(config);
    }

    public RaffleReceiptDB(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'REPORT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'YEAR' TEXT," + // 1: year
                "'PERIOD' INTEGER," + // 2: period
                "'TALCOUNT' INTEGER," + // 3: talcount
                "'SUPER_AWARDS_COUNT' INTEGER," + // 4: superAwardsCount
                "'SPECIAL_AWARDS_COUNT' INTEGER," + // 5: specialAwardsCount
                "'ONE_AWARDS_COUNT' INTEGER," + // 6: oneAwardsCount
                "'TWO_AWARDS_COUNT' INTEGER," + // 7: twoAwardsCount
                "'THREE_AWARDS_COUNT' INTEGER," + // 8: threeAwardsCount
                "'FOUR_AWARDS_COUNT' INTEGER," + // 9: fourAwardsCount
                "'FIVE_AWARDS_COUNT' INTEGER," + // 10: fiveAwardsCount
                "'SIX_AWARDS_COUNT' INTEGER);"); // 11: sixAwardsCount
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'REPORT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RaffleReceipt entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String year = entity.getYear();
        if (year != null) {
            stmt.bindString(2, year);
        }

        Integer period = entity.getPeriod();
        if (period != null) {
            stmt.bindLong(3, period);
        }

        Integer talcount = entity.getTalcount();
        if (talcount != null) {
            stmt.bindLong(4, talcount);
        }

        Integer superAwardsCount = entity.getSuperAwardsCount();
        if (superAwardsCount != null) {
            stmt.bindLong(5, superAwardsCount);
        }

        Integer specialAwardsCount = entity.getSpecialAwardsCount();
        if (specialAwardsCount != null) {
            stmt.bindLong(6, specialAwardsCount);
        }

        Integer oneAwardsCount = entity.getOneAwardsCount();
        if (oneAwardsCount != null) {
            stmt.bindLong(7, oneAwardsCount);
        }

        Integer twoAwardsCount = entity.getTwoAwardsCount();
        if (twoAwardsCount != null) {
            stmt.bindLong(8, twoAwardsCount);
        }

        Integer threeAwardsCount = entity.getThreeAwardsCount();
        if (threeAwardsCount != null) {
            stmt.bindLong(9, threeAwardsCount);
        }

        Integer fourAwardsCount = entity.getFourAwardsCount();
        if (fourAwardsCount != null) {
            stmt.bindLong(10, fourAwardsCount);
        }

        Integer fiveAwardsCount = entity.getFiveAwardsCount();
        if (fiveAwardsCount != null) {
            stmt.bindLong(11, fiveAwardsCount);
        }

        Integer sixAwardsCount = entity.getSixAwardsCount();
        if (sixAwardsCount != null) {
            stmt.bindLong(12, sixAwardsCount);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public  RaffleReceipt readEntity(Cursor cursor, int offset) {
        RaffleReceipt entity = new  RaffleReceipt( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // year
                cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // period
                cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // talcount
                cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // superAwardsCount
                cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // specialAwardsCount
                cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // oneAwardsCount
                cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // twoAwardsCount
                cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // threeAwardsCount
                cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // fourAwardsCount
                cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // fiveAwardsCount
                cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11) // sixAwardsCount
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor,  RaffleReceipt entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setYear(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPeriod(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setTalcount(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setSuperAwardsCount(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setSpecialAwardsCount(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setOneAwardsCount(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setTwoAwardsCount(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setThreeAwardsCount(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setFourAwardsCount(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setFiveAwardsCount(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setSixAwardsCount(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert( RaffleReceipt entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey( RaffleReceipt entity) {
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

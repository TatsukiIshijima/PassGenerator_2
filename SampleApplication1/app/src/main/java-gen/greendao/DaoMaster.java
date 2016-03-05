package greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import greendao.MailDao;
import greendao.SNSDao;
import greendao.WebDao;
import greendao.ComputerDao;
import greendao.ShoppingDao;
import greendao.CreditDao;
import greendao.BankDao;
import greendao.OtherDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        MailDao.createTable(db, ifNotExists);
        SNSDao.createTable(db, ifNotExists);
        WebDao.createTable(db, ifNotExists);
        ComputerDao.createTable(db, ifNotExists);
        ShoppingDao.createTable(db, ifNotExists);
        CreditDao.createTable(db, ifNotExists);
        BankDao.createTable(db, ifNotExists);
        OtherDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        MailDao.dropTable(db, ifExists);
        SNSDao.dropTable(db, ifExists);
        WebDao.dropTable(db, ifExists);
        ComputerDao.dropTable(db, ifExists);
        ShoppingDao.dropTable(db, ifExists);
        CreditDao.dropTable(db, ifExists);
        BankDao.dropTable(db, ifExists);
        OtherDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(MailDao.class);
        registerDaoClass(SNSDao.class);
        registerDaoClass(WebDao.class);
        registerDaoClass(ComputerDao.class);
        registerDaoClass(ShoppingDao.class);
        registerDaoClass(CreditDao.class);
        registerDaoClass(BankDao.class);
        registerDaoClass(OtherDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
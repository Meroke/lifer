package com.example.lifer.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";

    // 创建联系人表的 SQL 语句
    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PHONE + " TEXT,"
            + COLUMN_ADDRESS + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建联系人表
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库升级时的操作，如表结构变更等
        // 这里简单示例，直接删除旧表并创建新表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // 插入联系人数据
    public void insertContact(SQLiteDatabase db, ContentValues values) {
        db.insert(TABLE_CONTACTS, null, values);
    }

    // 更新联系人数据
    public void updateContact(SQLiteDatabase db, int id, ContentValues values) {
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        db.update(TABLE_CONTACTS, values, whereClause, whereArgs);
    }


    // 删除联系人数据
    public void deleteContact(SQLiteDatabase db, int id) {
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
    }


    public  String getDatabaseName() {
        return DATABASE_NAME;
    }

    public  int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public  String getTableContacts() {
        return TABLE_CONTACTS;
    }

    public  String getColumnId() {
        return COLUMN_ID;
    }

    public  String getColumnName() {
        return COLUMN_NAME;
    }

    public  String getColumnPhone() {
        return COLUMN_PHONE;
    }

    public  String getColumnAddress() {
        return COLUMN_ADDRESS;
    }

    public  String getCreateContactsTable() {
        return CREATE_CONTACTS_TABLE;
    }

}

package com.example.lifer.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifer.Data.Contact;
import com.example.lifer.Data.DatabaseHelper;
import com.example.lifer.R;
import com.example.lifer.adapters.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private ListView contactsListView;
    private ContactsAdapter adapter;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsListView = findViewById(R.id.contactsListView);
        dbInit();
        List<Contact> contacts = createContactsList();
        adapter = new ContactsAdapter(this, contacts);
        contactsListView.setAdapter(adapter);

    }

    public void dbInit(){
        // 实例化 DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        // 插入联系人数据
        insertContact("John Doe", "13812345678", "123 Main St");
        insertContact("Jane Smith", "13987654321", "456 Main St");
        insertContact("Michael Johnson", "13567891234", "789 Main St");
        insertContact("Emily Davis", "13798765432", "1011 Main St");
        insertContact("David Wilson", "1112223333", "1213 Main St");
        insertContact("Sarah Thompson", "15876543210", "1415 Main St");
        insertContact("James Brown", "15901234567", "1617 Main St");
        insertContact("Linda Martinez", "18654321098", "1819 Main St");
        insertContact("Robert Garcia", "18789012345", "2021 Main St");
        insertContact("Elizabeth Taylor", "13678901234", "2223 Main St");
    }

    public void insertContact(String name, String phone, String address) {
        // 获取可写的数据库


        // 创建要插入的数据
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);
        values.put(DatabaseHelper.COLUMN_ADDRESS, address);

        // 插入数据
        dbHelper.insertContact(db, values);

    }

    private List<Contact> createContactsList() {
        List<Contact> contacts = new ArrayList<>();

        Cursor cursor = db.query("contacts", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE));
                String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS));

                // 处理联系人数据，例如添加到列表中
                contacts.add(new Contact(name, phone, address));
            } while (cursor.moveToNext());
        }
        return contacts;
    }

    public void onBackButtonClick(View view) {
        finish(); // 结束当前Activity，返回到上一个Activity（MainActivity）
    }

    public void onSendSmsButtonClick(View view) {
        int position = contactsListView.getPositionForView(view);
        Contact contact = adapter.getItem(position);

        String phoneNumber = contact.getPhoneNumber();

        // TODO: 实现发送短信的逻辑，可以使用Intent.ACTION_SENDTO或其他方式发送短信
        // 可以参考Android官方文档：https://developer.android.com/reference/android/content/Intent#ACTION_SENDTO
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        startActivity(intent);
    }
    public void onDialButtonClick(View view) {
        int position = contactsListView.getPositionForView(view);
        Contact contact = adapter.getItem(position);

        String phoneNumber = contact.getPhoneNumber();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}

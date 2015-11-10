package com.android.mycontact;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * 读取通讯录中的所有联系人
 * 1.copy ButterKnife.jar to libs,Sync Project(同步)
 * 2.授予 读取联系人 权限
 * 3.查询通讯录中的所有联系人
 * 4.activity_main.xml / contact_item.xml / adapter
 * 5.data - adapter - ListView
 * 6.call phone number
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<Contact> contacts;
    private ContactAdapter adapter;


    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.tvSearch)
    EditText tvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        contacts = new ArrayList<>();
        // 查询通讯录中的所有联系人
        getContacts("");
        for (Contact contact : contacts) {
            Log.v(TAG, contact.toString());
        }
        adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(int position){
        Contact contact = contacts.get(position);
        // 拨号 一个号码
        String phoneNumber = contact.getMobile();
        if (phoneNumber.indexOf(",") != -1) {
            String[] pn = phoneNumber.split(",");
            phoneNumber = pn[0];
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        startActivity(intent);

        Toast.makeText(this,phoneNumber,Toast.LENGTH_SHORT).show();
    }

    private void getContacts(String inputName) {
        contacts.clear();
        // 获取通讯录的联系人的路径(网址)
        // content://com.android.contacts/contacts
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        // 实例化 内容访问者 对象
        ContentResolver resolver = this.getContentResolver();
        // 内容访问者 通过 指定路径 访问 内容提供者,返回查询结果集
        // query(路径,返回字段,条件字段,条件字段值,排序)
        // select 返回字段 from 路径 where 条件字段=条件字段值 order by ..
        //Cursor cursor = resolver.query(uri, null, null, null, null);

        // 模糊查询 姓名
        //String inputName =tvSearch.getText().toString(); // 来自文本框的值
       //String inputName ="黄";
       // Intent intent = this.getIntent();

       // String inputName = intent.getStringExtra("tvSearch");
        Cursor cursor = resolver.query(uri, null, ContactsContract.Contacts.DISPLAY_NAME+" like ? ",new String[]{"%"+inputName+"%"}, null);

        while (cursor.moveToNext()) {
            // 从结果集中获得联系人的 id 和 姓名
            String id = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.
                    getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            String mobile = "";
            // 获得联系人的所有手机号码
            // content://com.android.contacts/data/phones
            Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            // select 返回字段 from phoneUri where contact_id = id
            // Cursor phoneCursor = resolver.query(phoneUri, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);

            Cursor phoneCursor = resolver.query(
                    phoneUri, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=? ", new String[]{id}, null);

            while (phoneCursor.moveToNext()) {
                String phone = phoneCursor.getString(
                        phoneCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                mobile += phone + ","; // 130,155,138,
            }
            if (mobile.length() > 0) {
                mobile = mobile.substring(0, mobile.length() - 1);
            }

            Contact contact = new Contact();
            contact.setId(id);
            contact.setName(name);
            contact.setMobile(mobile);
            contacts.add(contact);
        }

        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvSearchOnClick();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void tvSearchOnClick() {
        String inputName =tvSearch.getText().toString();
        getContacts(inputName);



    }


}

package com.example.administrator.findcontact;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.findcontact.adapter.ContactAdapter;
import com.example.administrator.findcontact.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    private List<Contact> contacts;
    private ContactAdapter adapter;

    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.tvSearch)
    EditText tvSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        contacts = new ArrayList<>();
        getContacts("");
        adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }

    private void getContacts(String inputName) {
        contacts.clear();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(uri, null, ContactsContract.Contacts.DISPLAY_NAME + " like ? ", new String[]{"%" + inputName + "%"}, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.
                    getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String mobile = "";
            Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor phoneCursor = resolver.query(
                    phoneUri, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=? ", new String[]{id}, null);

            while (phoneCursor.moveToNext()) {
                String phone = phoneCursor.getString(
                        phoneCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                mobile += phone + ",";
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
        if(contacts.isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.notfind,Toast.LENGTH_SHORT).show();
        }else {
            listView.setVisibility(View.VISIBLE);
        }

    }

}

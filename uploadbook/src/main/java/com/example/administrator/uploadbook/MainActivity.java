package com.example.administrator.uploadbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;

public class MainActivity extends AppCompatActivity {
    private EditText isbn;
    private EditText title;
    private EditText author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isbn = (EditText) findViewById(R.id.isbn);
        title = (EditText) findViewById(R.id.title);
        author = (EditText) findViewById(R.id.author);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.OK:
                do1();
                break;
            case R.id.choose:
                //do2();
                break;
        }
    }

    private void do1() {
        RequestParams params=new RequestParams();
        params.add("isbn",isbn.getText().toString());
        params.add("title",title.getText().toString());
        params.add("author",author.getText().toString());
        params.add("image","image/qq.jpg");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

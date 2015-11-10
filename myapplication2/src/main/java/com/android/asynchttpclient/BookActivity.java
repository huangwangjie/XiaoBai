package com.android.asynchttpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    private List<Book> books;
    private BookFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        if (savedInstanceState == null) {
            books = new ArrayList<>();
            fragment = new BookFragment();
            // 在 Activity 中加载 Fragment
            getFragmentManager().beginTransaction().
                    add(R.id.container, fragment).commit();
        }
    }

    private void getDataFromServer() {
        AndroidClient.get("BookJsonServlet", null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray ja) {
                // 处理服务器返回的结果是 JSON 数组
                try {
                    // 清空已有数据
                    books.clear();
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        String isbn = jo.getString("isbn");
                        String title = jo.getString("title");
                        String author = jo.getString("author");
                        String image = AndroidClient.BASE_URL + jo.getString("image");
                        Book book = new Book(isbn, title, author, image);
                        books.add(book);
                    }
                    fragment.loadData(books); // 更新界面
                    Log.v("MainActivity", books.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onSuccess(int statusCode, Header[] headers, JSONObject jo) {
                // 处理服务器返回的结果是 JSON 对象
                // {"isbn":"9787506287081","title":"认识电影","author":"路易斯·贾内梯","image":"images/1.jpg"}
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                getDataFromServer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

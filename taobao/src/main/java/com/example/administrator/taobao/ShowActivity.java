package com.example.administrator.taobao;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.taobao.pojo.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private static final String VOLLEY_TAG = "VOLLEY_TAG";
    private ArrayList<Product> list;
    private ProductFragment productFragment;
    private ConnectivityManager cm;
    private static final String BASE_URL = "http://192.168.43.188:8087/taobao/";

    private RequestQueue mQueue;
    private JsonArrayRequest jsonArrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        if (savedInstanceState == null) {
            list = new ArrayList<Product>();
            productFragment = new ProductFragment();
            getFragmentManager().beginTransaction().add(R.id.container, productFragment)
                    .commit();
        }
        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    private void getDataFromServer() {
        mQueue = Volley.newRequestQueue(this);
        jsonArrayRequest = new JsonArrayRequest(
                "http://192.168.43.188:8087/taobao/ProductJsonServlet",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray ja) {
                        try {
                            list.clear();
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jo = ja.getJSONObject(i);
                                Product product = new Product();
                                product.setName(jo.getString("name"));
                                product.setCreateDate(jo.getString("createDate"));
                                product.setPrice(Double.valueOf(jo.getString("price")));
                                product.setImage(BASE_URL + jo.getString("image"));
                                list.add(product);
                            }
                            productFragment.loadData(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),"出错了",Toast.LENGTH_LONG).show();
            }
        });

        jsonArrayRequest.setTag(VOLLEY_TAG);
        mQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(VOLLEY_TAG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                getDataFromServer();
                break;
            case R.id.action_settings:
                break;
        }
        return true;
    }
}

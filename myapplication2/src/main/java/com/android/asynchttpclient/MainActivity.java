package com.android.asynchttpclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1.copy
 * butterknife.jar
 * picasso.jar
 * android-async-http-1.4.8.jar
 * to libs
 * 2.strings.xml
 * 3.assets/index.html
 * 4.在 activity_main.xml 定义两个按钮分别跳转至WebViewActivity / BookActivity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btnWebView)
    public void webViewClick() {
        startActivity(new Intent(this, WebViewActivity.class));
    }

    @OnClick(R.id.btnBookActivity)
    public void bookActivityClick() {
        startActivity(new Intent(this, BookActivity.class));
    }

    @OnClick(R.id.uploadBookActivity)
    public void uploadBookActivityClick() {
        startActivity(new Intent(this, UploadBookActivity.class));
    }
}

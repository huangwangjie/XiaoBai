package com.android.asynchttpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 第一种方式(不使用布局文件):
        // 实例化WebView对象 (51 行:setContentView(webView);)
        webView = new WebView(this);

        // 另一种方式(使用 布局文件 - 注释第 51 行):
        // setContentView(R.layout.activity_web_view);
        // webView = (WebView) findViewById(R.id.webView);

        // 设置使用够执行JS脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置使支持缩放
        webView.getSettings().setBuiltInZoomControls(true);

        // 加载本地网页
        // 右击 Module 名,New -> Folder -> Assets Folder
        // webView.loadUrl("file:///android_asset/index.html");

        // 加载云端的网页
        webView.loadUrl("http://10.0.3.2:8087/androidcloud/index.jsp");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // 转向错误时的处理
                Toast.makeText(getApplicationContext(), "Oh no.." + description,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 使用当前WebView处理跳转
                return true; // true表示此事件在此处被处理，不需要再广播
            }
        });
        setContentView(webView);
    }

    /**
     * 默认点回退键，会退出Activity，需监听按键操作，使回退在WebView内发生
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

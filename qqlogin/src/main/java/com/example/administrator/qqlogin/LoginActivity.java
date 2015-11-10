package com.example.administrator.qqlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.administrator.qqlogin.Util.QQUtil;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class LoginActivity extends Activity {

    public static String mAppid = "222222";
    // 腾讯对象
    public static Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (mTencent == null) {
            // 根据 APP_ID和上下文 创建腾讯对象实例
            mTencent = Tencent.createInstance(mAppid, this);
        }
    }

    public void qqLogin(View view) {
        // QQ登录按钮动画
        Animation shake = AnimationUtils.loadAnimation(this,
                R.anim.shake);
        onClickLogin();
        view.startAnimation(shake);
    }

    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
        }
    }

    // 登录监听事件
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                QQUtil.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                QQUtil.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
            // QQUtil.showResultDialog(LoginActivity.this, response.toString(), "登录成功");
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            QQUtil.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
            QQUtil.dismissDialog();
        }

        @Override
        public void onCancel() {
            QQUtil.toastMessage(LoginActivity.this, "onCancel: ");
            QQUtil.dismissDialog();
        }
    }

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }
}

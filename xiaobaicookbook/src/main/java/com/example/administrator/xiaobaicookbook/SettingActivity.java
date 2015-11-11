package com.example.administrator.xiaobaicookbook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.administrator.xiaobaicookbook.util.CustomMultipleChoiceView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class SettingActivity extends Activity {
    private PopupWindow stationSelectDialog;
    private EditText diet;
    //    private Spinner s_peopleCount;
//    private Spinner s_favorite;
//    private Spinner s_richOrPoor;
//    @InjectView(R.id.diet)
//    Button diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        diet = (EditText) findViewById(R.id.diet);
    }

    public void onClick(View v) {
        showMutiChoiceDialog(new String[]{"没有忌口", "不吃牛肉", "不吃羊肉", "不吃狗肉",
                "不吃鸭肉", "不吃鸡肉", "不吃海鲜", "不吃辣椒",
                "不喜荤腥", "不吃鱼肉", "有孕妇", "有“三高”",
                "不吃内脏", "不吃发物"}, diet);
    }

    private void showMutiChoiceDialog(final String[] stationsMean, final TextView textView) {
        if(stationSelectDialog == null){
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.dialog_multiplechoice, null);
            CustomMultipleChoiceView mutipleChoiceView = (CustomMultipleChoiceView) view.findViewById(R.id.CustomMultipleChoiceView);
            mutipleChoiceView.setData(stationsMean, null);
            mutipleChoiceView.selectAll();
            mutipleChoiceView.setTitle("多选");
            stationSelectDialog = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            mutipleChoiceView.setOnSelectedListener(new CustomMultipleChoiceView.onSelectedListener() {
                @Override
                public void onSelected(SparseBooleanArray sparseBooleanArray) {
                    stationSelectDialog.dismiss();
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<sparseBooleanArray.size(); i++){
                        if(sparseBooleanArray.get(i)){
                            sb.append(stationsMean[i] + ",");
                        }
                    }
                    if(sb.length() > 0)
                        sb.deleteCharAt(sb.length()-1);
                    textView.setText(sb.toString());
                }
            });
        }
        stationSelectDialog.showAtLocation(getCurrentFocus(), Gravity.CENTER, 0, 0);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

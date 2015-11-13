package com.example.administrator.xiaobaicookbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;





public class SettingActivity extends Activity {
    private ImageView filte;
    private EditText diet;
    //private List<Icon> appList;
    private String[] items;
    private Switch swich1;
    private Button OK;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        items=getResources().getStringArray(R.array.dietList);
        swich1= (Switch) findViewById(R.id.switch1);
        OK= (Button) findViewById(R.id.OK);
        //init();
        filte = (ImageView) findViewById(R.id.filte);
        diet = (EditText) findViewById(R.id.diet);
        filte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swich1.isChecked()==false){
                    showPrepareDialog();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            dialog.cancel();
                            //来个if判断，如果只有一个人，跳转到今日推荐1
                            //如果多于1人，跳转到今日推荐2
                            startActivity(new Intent(SettingActivity.this, TodayMenuActivity.class));
                            finish();
                        }
                    }).start();


                }else {
                    startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                }
            }
        });
    }





    private void showDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle(R.string.diet1);
        final boolean[] checkedItems = new boolean[items.length];

        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
            }
        });
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = "";
                for (int i = 0; i < items.length; i++) {
                    if (checkedItems[i]) {
                        text += items[i] + ",";
                    }
                }
                if (text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                }else {
                    text=getResources().getString(R.string.everyThingIsOk);
                }
                diet.setText(text);
            }
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showPrepareDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(R.string.preparing);
        View view = getLayoutInflater().inflate(R.layout.preparing, null);
        builder.setView(view);
        builder.setPositiveButton(null, null);
        builder.setNegativeButton(null, null);
        dialog = builder.create();
        dialog.show();
    }
//    private void init() {
//        appList = new ArrayList<>();
//        int label[] = new int[]{R.string.everyThingIsOk, R.string.noBeef,
//                R.string.noPork, R.string.noSheep, R.string.noDog,
//                R.string.noDuck, R.string.noChicken,
//                R.string.noFish, R.string.noSeaFish,
//                R.string.noMeat, R.string.noNeiZang,
//                R.string.noHot, R.string.noFa,
//                R.string.pregnant, R.string.threeHigh};
//        List<Drawable> list = new ArrayList<>();
//        list.add(getResources().getDrawable(R.drawable.bigmouth));
//        list.add(getResources().getDrawable(R.drawable.cow));
//        list.add(getResources().getDrawable(R.drawable.pork));
//        list.add(getResources().getDrawable(R.drawable.sheep));
//        list.add(getResources().getDrawable(R.drawable.dog));
//        list.add(getResources().getDrawable(R.drawable.duck));
//        list.add(getResources().getDrawable(R.drawable.chicken));
//        list.add(getResources().getDrawable(R.drawable.fish));
//        list.add(getResources().getDrawable(R.drawable.seafish));
//        list.add(getResources().getDrawable(R.drawable.meat));
//        list.add(getResources().getDrawable(R.drawable.neizang));
//        list.add(getResources().getDrawable(R.drawable.lesshot));
//        list.add(getResources().getDrawable(R.drawable.cock));
//        list.add(getResources().getDrawable(R.drawable.pregnant));
//        list.add(getResources().getDrawable(R.drawable.fat));
//        Icon icon = null;
//        for (int i = 0; i < label.length; i++) {
//            icon = new Icon();
//            icon.setLabel(getResources().getString(label[i]));
//            icon.setIcon(list.get(i));
//            icon.setCategory(i);
//            appList.add(icon);
//        }
//    }


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

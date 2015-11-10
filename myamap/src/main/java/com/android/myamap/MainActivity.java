package com.android.myamap;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.android.myamap.util.Constants;
import com.android.myamap.util.ToastUtil;

public class MainActivity extends Activity {
    private static final int REQ_MAP =17;
    private  TextView tvYourAddress;
    private MarkerOptions markerOption;
    private TextView markerText;
    private RadioGroup radioOption;
    private AMap aMap;
    private MapView mapView;
    private Marker marker2;// 有跳动效果的marker对象
    private LatLng latlng = new LatLng(36.061, 103.834);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvYourAddress= (TextView) findViewById(R.id.tvYourAddress);

    }

    public void onClickLocation(View view){
        Intent intent=new Intent(this,MultyLocationActivity.class);
        startActivityForResult(intent, REQ_MAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_MAP&&resultCode==RESULT_OK){
            String address=data.getStringExtra("address");
            tvYourAddress.setText(address);
        }
    }


}

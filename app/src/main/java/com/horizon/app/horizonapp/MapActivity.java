package com.horizon.app.horizonapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.horizon.app.core.activities.StoreActivity;
import com.horizon.app.core.ui.loader.HorizonLoader;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    //坐标位置
    private LocationClient mLocationClient;
    //显示地图的视图（View）
    private MapView mapView;
    //地图对象的总控制器
    private BaiduMap baiduMap;
    //定位到当前位置一次就够了，防止多次定位
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        initMap();
        checkPermission();

    }


    //判断需要的权限是否全部征得用户同意，若不同意则退出程序
    @Override
    public void onRequestPermissionsResult(int requsetCode ,String[] permissions,int[] grantResults){
        switch (requsetCode){
            case 1:
                if (grantResults.length>0){
                    for (int result : grantResults){
                        if (result!= PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    //若权限全部满足，则开始定位
                    requsetLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    //下列代码用于判断运行时的权限是否申请到，没申请到的放入集合内，后续一起申请
    private void checkPermission(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            //一次性申请集合内的权限
            ActivityCompat.requestPermissions(MapActivity.this,permissions,1);
        }else {
            //运行时权限全部满足，开始定位
            requsetLocation();
        }
    }

    //地图初始化
    private void initMap(){
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        mapView = findViewById(R.id.bmapView);
        //得到地图实例
        baiduMap = mapView.getMap();
        //开启在地图上显示我的位置功能
        baiduMap.setMyLocationEnabled(true);
        //对兴趣点点击做出响应
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            //对地图空白处点击事件
            @Override
            public void onMapClick(LatLng latLng) {

                Toast.makeText(getApplicationContext(),"On Map Click"
                        ,Toast.LENGTH_LONG).show();

            }

            //对地图兴趣点点击事件
            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {

                startActivity(new Intent(MapActivity.this, StoreActivity.class));
                overridePendingTransition(R.layout.bottom_in,R.layout.bottom_silent);

                return false;
            }
        });
    }

    //开始定位
    private void requsetLocation() {
        //定位初始化
        //定位信息设置
        LocationClientOption option = new LocationClientOption();
        //定位刷新时间
        option.setScanSpan(5000);
        //获取当前位置详细地址信息
        option.setIsNeedAddress(true);
        //对设置进行应用
        mLocationClient.setLocOption(option);
        //定位完成后，回调MyLocationListener
        mLocationClient.start();
    }

    //定位监视器，当定位成功时，由requsetLocation中的mLocationClient.start()回调
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getLocType()==BDLocation.TypeGpsLocation
                    ||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }
    }

    //使地图移动到获取到的位置信息处
    private void navigateTo(BDLocation location){
        if (isFirstLocate){
            //Latlng 主要用于存放经纬度
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            //让地图显示latLng中存放的经纬度位置
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            //地图缩放级别，3~19，这里设置为16
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }

        //让我的位置（设定位置）显示在地图上，开启此功能前必须开启setMyLocationEnabled
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    //从onPause调回到Active状态，重新开始，复活-.-
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    //被另一个活动半覆盖，仍显示在窗口时，系统维持其内部状态
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    //活动销毁后的处理
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //活动销毁后停止定位
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
}

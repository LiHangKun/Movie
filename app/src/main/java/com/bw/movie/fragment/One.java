package com.bw.movie.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.GengDuoActivity;
import com.bw.movie.activity.SearchActivtiy;
import com.bw.movie.activity.XiangQingActivity;
import com.bw.movie.adapter.ComingSoonMovieAdapter;
import com.bw.movie.adapter.ReMenAdapter;
import com.bw.movie.adapter.ZhengReYingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.BannerListBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.presenter.HomePageFrgOnePresenter;
import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;

public class One extends BaseFragment implements HomePageFrgOneContral.getView, LocationSource, AMapLocationListener, View.OnClickListener , PermissionCallbacks {

    @BindView(R.id.xb)
    XBanner xb;
    @BindView(R.id.aaaa)
    TextView aaaa;
    @BindView(R.id.reying_re)
    RecyclerView reyingRe;
    @BindView(R.id.jijiang_re)
    RecyclerView jijiangRe;
    @BindView(R.id.remen_re)
    RecyclerView remenRe;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.sou_dianying)
    ImageView sou_dianying;
    @BindView(R.id.gengone)
    TextView gengone;
    @BindView(R.id.gengtwo)
    TextView gengtwo;
    @BindView(R.id.gengthree)
    TextView gengthree;
    @BindView(R.id.map)
    MapView mapView;
    //AMap是地图对象
    private AMap aMap;

    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private String city1;
    private List<JiangShangYingBean.ResultBean> result;
    private static BasePresenter basePresenter;
    private ZhengReYingAdapter zhengReYingAdapter;
    private ComingSoonMovieAdapter comingSoonMovieAdapter;


    public static void setClick(Context context, int i) {
        if (basePresenter instanceof HomePageFrgOnePresenter) {
            ((HomePageFrgOnePresenter) basePresenter).getYuYue(i);
        }
    }

    public static void setXiangQingId(Context context, int movieId) {
        Intent intent = new Intent(context, XiangQingActivity.class);
        intent.putExtra("movieId",movieId);
        context.startActivity(intent);
    }


    @Override
    protected void initData() {

        basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageFrgOnePresenter) {
            showDialog();
            ((HomePageFrgOnePresenter) basePresenter).getBanner();
            ((HomePageFrgOnePresenter) basePresenter).getReMen(1, 5);
            ((HomePageFrgOnePresenter) basePresenter).getZhengShangYing(1, 5);
            ((HomePageFrgOnePresenter) basePresenter).JiangShang(1, 16);
        }
        sou_dianying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivtiy.class);
                startActivity(intent);
            }
        });
        gengone.setOnClickListener(this);
        gengtwo.setOnClickListener(this);
        gengthree.setOnClickListener(this);
    }


    @Override
    protected BasePresenter initPresenter() {
        return new HomePageFrgOnePresenter(this);
    }
    boolean isYou=false;
    @Override
    protected void initView(View inflate) {
        String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(getContext(), mPermissionList)) {
            isYou=true;
            Toast.makeText(getContext(), "已有权限", Toast.LENGTH_SHORT).show();
            if (aMap == null) {
                aMap = mapView.getMap();
                //设置显示定位按钮 并且可以点击
                UiSettings settings = aMap.getUiSettings();
                aMap.setLocationSource(this);//设置了定位的监听
                // 是否显示定位按钮
                settings.setMyLocationButtonEnabled(true);
                aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            }
            location();
        } else {
            //未同意过,或者说是拒绝了，再次申请权限
            EasyPermissions.requestPermissions(getActivity(), "需要定位的权限", 1, mPermissionList);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_one;
    }


    @Override
    public void getBannerSucc(BannerBean bannerBean) {
        hindDialog();
        final ArrayList<BannerListBean> arrayList = new ArrayList<>();
        for (int i = 0; i < bannerBean.getResult().size(); i++) {
            arrayList.add(new BannerListBean(bannerBean.getResult().get(i).getImageUrl()));
        }
        xb.setBannerData(arrayList);
        xb.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getContext()).load(arrayList.get(position).getA()).into((ImageView) view);
            }
        });
        xb.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                aaaa.setText("" + (i + 1) + "/" + arrayList.size() + "");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void getBannerFiuld(String str) {

    }


    @Override
    public void getZhengShangYingSucc(ZhengShangYingBean zhengShangYingBean) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        reyingRe.setLayoutManager(linearLayoutManager);
        zhengReYingAdapter = new ZhengReYingAdapter(getContext(), zhengShangYingBean.getResult());
        reyingRe.setAdapter(zhengReYingAdapter);
    }

    @Override
    public void getZhengShangYingFiuld(String str) {

    }

    @Override
    public void getJiangShangSucc(JiangShangYingBean jiangShangYingBean) {
        result = jiangShangYingBean.getResult();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        jijiangRe.setLayoutManager(linearLayoutManager);
        comingSoonMovieAdapter = new ComingSoonMovieAdapter(getContext(), jiangShangYingBean.getResult());
        jijiangRe.setAdapter(comingSoonMovieAdapter);
    }

    @Override
    public void getJiangShangFiuld(String str) {

    }


    @Override
    public void getReMenSucc(ReMenMovieBean reMenMovieBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        remenRe.setLayoutManager(linearLayoutManager);
        ReMenAdapter reMenAdapter = new ReMenAdapter(getContext(), reMenMovieBean.getResult());
        remenRe.setAdapter(reMenAdapter);
    }

    @Override
    public void getReMenFiuld(String str) {

    }

    @Override
    public void getYuYueSucc(YuYueBean yuYueBean) {
        Toast.makeText(getContext(), "" + yuYueBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (yuYueBean.getMessage().equals("预约成功")) {
            Toast.makeText(getContext(), "判断正确", Toast.LENGTH_SHORT).show();
            ((HomePageFrgOnePresenter) basePresenter).JiangShang(1, 16);
        }
    }

    @Override
    public void getYuYueFiuld(String str) {

    }

    //地图
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    //Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    city1 = aMapLocation.getCity();
                    Log.i("chengshi", "" + city1 + aMapLocation.getStreet());
                    //Toast.makeText(getContext(), ""+city1+"11", Toast.LENGTH_SHORT).show();
                    city.setText(city1 + "");
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.gengone:
                Intent intent = new Intent(getContext(), GengDuoActivity.class);
                startActivity(intent);
                break;
            case R.id.gengtwo:
                Intent intenttwo = new Intent(getContext(), GengDuoActivity.class);
                startActivity(intenttwo);
                break;
            case R.id.gengthree:
                Intent intentthree = new Intent(getContext(), GengDuoActivity.class);
                startActivity(intentthree);
                break;

        }

    }



    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //权限
/*
    private static final int RC_CAMERA_AND_RECORD_AUDIO = 10000;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    *//**
     * 去申请权限
     *//*
    @AfterPermissionGranted(RC_CAMERA_AND_RECORD_AUDIO)
    private void requestPermissions() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE};

        //判断有没有权限
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            // 如果有权限了,
            // doing something就做你该做的事情
            openCamera();
        } else {
            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是你要申请的权限
            EasyPermissions.requestPermissions(getActivity(), "该页面需要您开启定位,否则可能使用不了", RC_CAMERA_AND_RECORD_AUDIO, perms);
        }
    }
    *//**
     * 权限申请成功的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms       申请成功的权限集合
     *//*
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i("aaaa", "onPermissionsGranted: ");
        if (requestCode != RC_CAMERA_AND_RECORD_AUDIO) {
            return;
        }
        for (int i = 0; i < perms.size(); i++) {
            if (perms.get(i).equals(perms.get(i))) {
                Log.i("aaaa", "onPermissionsGranted: " + "相机权限成功");
                openCamera();

            }*//* else if (perms.get(i).equals(Manifest.permission.RECORD_AUDIO)) {
                Log.i("aaaa", "onPermissionsGranted: " + "录制音频权限成功");
            }*//*
        }
    }
    *//**
     * 权限申请拒绝的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms       申请拒绝的权限集合
     *//*
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i("aaaa", "onPermissionsDenied: ");

        if (requestCode != RC_CAMERA_AND_RECORD_AUDIO) {
            return;
        }

        for (int i = 0; i < perms.size(); i++) {
            if (perms.get(i).equals(perms.get(i))) {
                Log.i("aaaa", "onPermissionsDenied: " + "地图权限拒绝");
            } *//*else if (perms.get(i).equals(Manifest.permission.RECORD_AUDIO)) {
                Log.i("aaaa", "onPermissionsDenied: " + "地图权限拒绝");
            }*//*
        }

        //如果有一些权限被永久的拒绝, 就需要转跳到 设置-->应用-->对应的App下去开启权限
        if (EasyPermissions.somePermissionPermanentlyDenied(getActivity(), perms)) {
            new AppSettingsDialog.Builder(getActivity())
                    .setTitle("权限已经被您拒绝")
                    .setRationale("如果不打开权限则无法使用该功能,点击确定去打开权限")
                    .setRequestCode(10001)//用于onActivityResult回调做其它对应相关的操作
                    .build()
                    .show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001) {
            Toast.makeText(getContext(), "从开启权限的页面转跳回来", Toast.LENGTH_SHORT).show();
        }
    }
    private void openCamera() {
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();
    }*/
   /* protected String[] needPermissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
            };
    private static final int PERMISSON_REQUESTCODE = 0;
    private boolean isNeedCheck = true;
    *//**
     * 判断是否需要检测，防止不停的弹框
     *//*
    @Override
    public void onResume() {
        super.onResume();
        if(isNeedCheck){
            checkPermissions(needPermissions);
        }
    }
    private void openCamera() {
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();
    }

    *//**
     *
     * @param
     * @since 2.5.0
     *
     *//*
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
         if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(getActivity(),
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }
    *//**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     *//*
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    getActivity(), perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;

    }
    *//**
     * 检测是否说有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     *//*
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

            openCamera();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }
    *//**

     * 显示提示信息

     *

     * @since 2.5.0

     *

     *//*

    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);
        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });



        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }
    *//**

     *  启动应用的设置

     *

     * @since 2.5.0

     *

     *//*

    private void startAppSettings() {

        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));

        startActivity(intent);
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();

    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            getActivity().finish();
            return true;
        }
        return getActivity().onKeyDown(keyCode, event);
    }
*/

    String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(getContext(), "用户授权", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getContext(), "没有定位权限", Toast.LENGTH_SHORT).show();
    }

}

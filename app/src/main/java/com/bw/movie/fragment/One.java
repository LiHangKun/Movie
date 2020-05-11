package com.bw.movie.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.bw.movie.util.SPUtils;
import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;

public class One extends BaseFragment implements HomePageFrgOneContral.getView, LocationSource, AMapLocationListener, View.OnClickListener, PermissionCallbacks {

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
    Unbinder unbinder;
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
        intent.putExtra("movieId", movieId);
        context.startActivity(intent);
    }


    @Override
    protected void initData() {
        basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageFrgOnePresenter) {
            ((HomePageFrgOnePresenter) basePresenter).getBanner();
            ((HomePageFrgOnePresenter) basePresenter).getReMen(1, 100);
            ((HomePageFrgOnePresenter) basePresenter).getZhengShangYing(1, 100);
            ((HomePageFrgOnePresenter) basePresenter).JiangShang(1, 100);
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

    boolean isYou = false;

    @Override
    protected void initView(View inflate) {


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
                    double longitude = aMapLocation.getLongitude();
                    double latitude = aMapLocation.getLatitude();
                    SPUtils.putString(getActivity(), SPUtils.USERINFO_NAME, "jingdu", longitude + "");
                    SPUtils.putString(getActivity(), SPUtils.USERINFO_NAME, "weidu", latitude + "");
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
        switch (id) {
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



    @OnClick(R.id.city)
    public void onViewClicked() {
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


    String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

}

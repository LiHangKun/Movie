package com.bw.movie.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.util.App;
import com.bw.movie.util.MD5;
import com.bw.movie.util.RetrofitManager;
import com.bw.movie.util.SPUtils;
import com.bw.movie.zidingyi.PayBottomDialog;
import com.bw.movie.zidingyi.SeatTable;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class XuanZuoActivity extends BaseActivity {


    @BindView(R.id.mSearchView)
    SeatTable mSearchView;
    @BindView(R.id.zhi)
    LinearLayout zhi;
    IWXAPI api;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_xuan_zuo;
    }

    @Override
    public void initView() {
        api = WXAPIFactory.createWXAPI(XuanZuoActivity.this, null);
    }

    @Override
    public void initData() {
        mSearchView.setScreenName("8号厅荧幕");//设置屏幕名称
        mSearchView.setMaxSelected(3);//设置最多选中
        mSearchView.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }
            @Override
            public boolean isSold(int row, int column) {
                if(row==4&&column==6){
                    return true;
                }else if(row==6&&column==6){
                    return true;
                }
                return false;
            }
            @Override
            public void checked(int row, int column) {
                Toast.makeText(XuanZuoActivity.this, "排"+row+"列"+column, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void unCheck(int row, int column) {

            }
            @Override
            public String[] checkedSeatTxt(int row, int column) {
                    return null;
            }
        });
        mSearchView.setData(10,15);
        zhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();


            }
        });
    }

    private void xiaDan(final int payType) {
        String USERINFO_KEY_USER_ID = SPUtils.getString(App.getAppContext(), SPUtils.USERINFO_NAME, SPUtils.USERINFO_KEY_USER_ID);
        String a=USERINFO_KEY_USER_ID+1+"movie";
        String s = MD5.MD5(a);
        RetrofitManager.getInstance().getApis().getGouPiao(1,"4-8",s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiaDanBean>() {



                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XiaDanBean xiaDanBean) {

                        Toast.makeText(XuanZuoActivity.this, "", Toast.LENGTH_SHORT).show();
                        if(xiaDanBean.getMessage().equals("下单成功")){
                            String  orderId = xiaDanBean.getOrderId();
                            zhifu(orderId,payType);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private ImageView mIvWeichatSelect;
    private ImageView mIvAliSelect;

    private static final int PAY_TYPE_WECHAT = 1;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIBABA = 2;  //支付宝支付
    private int payType = 0;

    private void pay() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_pay_type, null);
        //微信支付的选择
        mIvWeichatSelect = dialogView.findViewById(R.id.iv_buy_weichat_select);
        //支付宝的选择
        mIvAliSelect = dialogView.findViewById(R.id.iv_buy_alipay_select);

        PayBottomDialog dialog = new PayBottomDialog(XuanZuoActivity.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_ali, R.id.tv_confirm, R.id.tv_cancel});
        dialog.bottmShow();
        dialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog dialog, View view) {
                switch (view.getId()) {
                    case R.id.ll_pay_weichat:   //微信支付
                        showToast("微信支付");
                        if (PAY_TYPE_WECHAT != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
                            payType = PAY_TYPE_WECHAT;

                        }

                        break;
                    case R.id.ll_pay_ali:  //支付宝支付
                        showToast("支付宝支付");
                        if (PAY_TYPE_ALIBABA != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong));
                            payType = PAY_TYPE_ALIBABA;
                        }
                        break;
                    case R.id.tv_confirm:  //确认支付
                        //TODO 支付
                        showToast("确认支付");
                        //重置
                        payType = PAY_TYPE_WECHAT;
                        xiaDan(payType);
                        dialog.cancel();
                        break;
                    case R.id.tv_cancel:  //取消支付
                        showToast("取消支付");
                        //重置
                        payType = PAY_TYPE_WECHAT;
                        dialog.cancel();
                        break;
                }
            }
        });
    }

    private void zhifu(String orderId,int payType) {
        Log.i("orderId",orderId+"");
        if(orderId!=null){

            RetrofitManager.getInstance().getApis().getZhiFu(payType,orderId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhiFuBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ZhiFuBean zhiFuBean) {

                            if(zhiFuBean.getMessage().equals("支付成功")){

                                PayReq request = new PayReq();
                                request.appId = zhiFuBean.getAppId();//子商户appid
                                request.partnerId = zhiFuBean.getPartnerId();//子商户号
                                request.prepayId= zhiFuBean.getPrepayId();
                                request.packageValue = zhiFuBean.getPackageValue();
                                request.nonceStr= zhiFuBean.getNonceStr();
                                request.timeStamp= zhiFuBean.getTimeStamp();
                                request.sign= zhiFuBean.getSign();
                                api.registerApp(zhiFuBean.getAppId());
                                api.sendReq(request);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }



    private void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}

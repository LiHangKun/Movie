package com.bw.movie.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.text.SimpleDateFormat;

public class BannerListBean extends SimpleBannerInfo {
    String a;

    public BannerListBean(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}

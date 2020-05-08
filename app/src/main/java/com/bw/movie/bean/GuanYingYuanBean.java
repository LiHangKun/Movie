package com.bw.movie.bean;

import java.util.List;

public class GuanYingYuanBean {

    /**
     * result : [{"address":"黄平路19号院龙旗购物中心3层","cinemaId":17,"logo":"http://mobile.bwstudent.com/images/movie/logo/blgj.jpg","name":"保利国际影城北京龙旗广场店"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 黄平路19号院龙旗购物中心3层
         * cinemaId : 17
         * logo : http://mobile.bwstudent.com/images/movie/logo/blgj.jpg
         * name : 保利国际影城北京龙旗广场店
         */

        private String address;
        private int cinemaId;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

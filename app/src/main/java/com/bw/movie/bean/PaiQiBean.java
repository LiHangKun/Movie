package com.bw.movie.bean;

import java.util.List;

public class PaiQiBean {

    /**
     * result : [{"director":"胡安·安东尼奥·巴亚纳","imageUrl":"http://172.17.8.100/images/movie/stills/zljgy/zljgy1.jpg","movieId":8,"name":"侏罗纪世界2","score":9.3,"starring":"克里斯·帕拉特,布莱丝·达拉斯·霍华德,泰德·拉文"},{"director":"文牧野","imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","movieId":1,"name":"我不是药神","score":8.9,"starring":"徐峥,周一围,王传君,谭卓,章宇,杨新鸣,王砚辉"},{"director":"徐克","imageUrl":"http://172.17.8.100/images/movie/stills/drjzsdtw/drjzsdtw1.jpg","movieId":4,"name":"狄仁杰之四大天王","score":8.7,"starring":"赵又廷,冯绍峰,林更新,刘嘉玲,阮经天,马思纯"}]
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
         * director : 胡安·安东尼奥·巴亚纳
         * imageUrl : http://172.17.8.100/images/movie/stills/zljgy/zljgy1.jpg
         * movieId : 8
         * name : 侏罗纪世界2
         * score : 9.3
         * starring : 克里斯·帕拉特,布莱丝·达拉斯·霍华德,泰德·拉文
         */

        private String director;
        private String imageUrl;
        private int movieId;
        private String name;
        private double score;
        private String starring;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}

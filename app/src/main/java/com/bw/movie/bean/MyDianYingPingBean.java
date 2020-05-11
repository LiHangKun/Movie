package com.bw.movie.bean;

import java.util.List;

public class MyDianYingPingBean {

    /**
     * result : [{"commentTime":1588669312000,"director":"\r\n刘伟强","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"movieName":"中国机长","movieScore":0,"myCommentContent":"我是dj喜羊羊青青草原我最狂","myCommentScore":10,"starring":"张涵予,欧豪,袁泉,张天爱,李沁"}]
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
         * commentTime : 1588669312000
         * director :
         刘伟强
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/zgjz/zgjz1.jpg
         * movieId : 24
         * movieName : 中国机长
         * movieScore : 0
         * myCommentContent : 我是dj喜羊羊青青草原我最狂
         * myCommentScore : 10
         * starring : 张涵予,欧豪,袁泉,张天爱,李沁
         */

        private long commentTime;
        private String director;
        private String imageUrl;
        private int movieId;
        private String movieName;
        private int movieScore;
        private String myCommentContent;
        private int myCommentScore;
        private String starring;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

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

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public int getMovieScore() {
            return movieScore;
        }

        public void setMovieScore(int movieScore) {
            this.movieScore = movieScore;
        }

        public String getMyCommentContent() {
            return myCommentContent;
        }

        public void setMyCommentContent(String myCommentContent) {
            this.myCommentContent = myCommentContent;
        }

        public int getMyCommentScore() {
            return myCommentScore;
        }

        public void setMyCommentScore(int myCommentScore) {
            this.myCommentScore = myCommentScore;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}

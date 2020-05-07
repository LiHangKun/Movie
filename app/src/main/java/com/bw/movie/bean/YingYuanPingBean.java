package com.bw.movie.bean;

import java.util.List;

public class YingYuanPingBean {

    /**
     * result : [{"commentContent":"4156415615","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":582,"commentTime":1587949801000,"commentUserId":13966,"commentUserName":"李秉正","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"Android","commentHeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ermNiacFa2CtwmMS7JL0vXNtRGxhEcQmBiaIjEA7aPX8a5jb9y9ZVkJjqQ1iaEq1XTGibI9Iu0LjOcxvw/132","commentId":577,"commentTime":1586574013000,"commentUserId":13867,"commentUserName":"Acolasia_8d7","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"小啊giao在线","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":576,"commentTime":1586567055000,"commentUserId":13912,"commentUserName":"giao","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"skin头目","commentHeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhRK0gFk0jT52Mib8ELDTxBEF4QD3LBjmGCnicE4a8oO4BzDGq0FD5ic4Vt99vcCTSML8blE0CO5EdQ/132","commentId":573,"commentTime":1586531745000,"commentUserId":13653,"commentUserName":"Youth_pH2","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"1","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":567,"commentTime":1586490214000,"commentUserId":13776,"commentUserName":"cxy","greatHeadPic":[],"greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"www","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-04-27/20200427213048.unknown","commentId":558,"commentTime":1586155643000,"commentUserId":13692,"commentUserName":"���С�ɰ�","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"服务不错","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":463,"commentTime":1586144299000,"commentUserId":13887,"commentUserName":"xte","greatHeadPic":[],"greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"dadsd","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-04-11/20200411180754.jpg","commentId":445,"commentTime":1585557742000,"commentUserId":13760,"commentUserName":"王黎杨","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"nice7387","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-03-05/20200305151944.png","commentId":204,"commentTime":1583292900000,"commentUserId":13731,"commentUserName":"林家铭1232456","greatHeadPic":[],"greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"海星","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-03-15/20200315204539.jpg","commentId":166,"commentTime":1583226477000,"commentUserId":13770,"commentUserName":"I啦啦啦I","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"很好","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-03-12/20200312152342.jpg","commentId":73,"commentTime":1583154691000,"commentUserId":13744,"commentUserName":"nisss","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"就很奈斯86","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-03-14/20200314215329.png","commentId":42,"commentTime":1583148137000,"commentUserId":13737,"commentUserName":"挽风的秋天","greatHeadPic":[],"greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"1","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-12-30/20191230114644.png","commentId":23,"commentTime":1577678196000,"commentUserId":13657,"commentUserName":"清醒与谦卑_Tci","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0}]
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
         * commentContent : 4156415615
         * commentHeadPic : http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg
         * commentId : 582
         * commentTime : 1587949801000
         * commentUserId : 13966
         * commentUserName : 李秉正
         * greatHeadPic : []
         * greatNum : 1
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private List<?> greatHeadPic;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public List<?> getGreatHeadPic() {
            return greatHeadPic;
        }

        public void setGreatHeadPic(List<?> greatHeadPic) {
            this.greatHeadPic = greatHeadPic;
        }
    }
}

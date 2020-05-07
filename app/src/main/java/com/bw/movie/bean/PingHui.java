package com.bw.movie.bean;

import java.util.List;

public class PingHui {


    /**
     * result : [{"replyContent":"中龙龙","replyHeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLecXOK4eogDLOqfx2DV6MC9cCTwPkjEnBDkia4ia3E0HY7icvtVYXlpqajft363HuaWKWmOPl8EYwhw/132","replyTime":1588413357000,"replyUserId":13917,"replyUserName":"A_Cww"}]
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
         * replyContent : 中龙龙
         * replyHeadPic : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLecXOK4eogDLOqfx2DV6MC9cCTwPkjEnBDkia4ia3E0HY7icvtVYXlpqajft363HuaWKWmOPl8EYwhw/132
         * replyTime : 1588413357000
         * replyUserId : 13917
         * replyUserName : A_Cww
         */

        private String replyContent;
        private String replyHeadPic;
        private long replyTime;
        private int replyUserId;
        private String replyUserName;

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getReplyHeadPic() {
            return replyHeadPic;
        }

        public void setReplyHeadPic(String replyHeadPic) {
            this.replyHeadPic = replyHeadPic;
        }

        public long getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(long replyTime) {
            this.replyTime = replyTime;
        }

        public int getReplyUserId() {
            return replyUserId;
        }

        public void setReplyUserId(int replyUserId) {
            this.replyUserId = replyUserId;
        }

        public String getReplyUserName() {
            return replyUserName;
        }

        public void setReplyUserName(String replyUserName) {
            this.replyUserName = replyUserName;
        }
    }
}

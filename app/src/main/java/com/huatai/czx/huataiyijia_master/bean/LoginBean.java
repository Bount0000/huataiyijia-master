package com.huatai.czx.huataiyijia_master.bean;

/**
 * Created by lenovo on 2018/3/16.
 */

public class LoginBean {

    /**
     * code : 0
     * msg : success
     * data : {"reTranTime":"16:18:43","ldToken":"04800B111E9B671087FF3AE14778D02029A58026CDA80793C8577D62D302853E5BAECB85EDE439E1475E390789239625722FBFC6F790EFD66337970F97047A1F75703CD195C9AB6313BC9D835D3C53C5683340C57DA34F838CE52715BE940D7B2C1A350550D4612BFB10216936C18C6C596F0F61CD00","deadTime":1521195523508,"transNo":"0000002017021516390556179","reTranDate":"20180316","userCode":"10000004"}
     */

    public String code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * reTranTime : 16:18:43
         * ldToken : 04800B111E9B671087FF3AE14778D02029A58026CDA80793C8577D62D302853E5BAECB85EDE439E1475E390789239625722FBFC6F790EFD66337970F97047A1F75703CD195C9AB6313BC9D835D3C53C5683340C57DA34F838CE52715BE940D7B2C1A350550D4612BFB10216936C18C6C596F0F61CD00
         * deadTime : 1521195523508
         * transNo : 0000002017021516390556179
         * reTranDate : 20180316
         * userCode : 10000004
         */

        public String reTranTime;
        public String ldToken;
        public long deadTime;
        public String transNo;
        public String reTranDate;
        public String userCode;
    }
}

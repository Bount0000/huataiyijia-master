package com.huatai.czx.huataiyijia_master.bean;

/**
 * Created by lenovo on 2018/3/16.
 */

public class UpdatePwBean {
    /**
     * code : 0
     * msg : success
     * data : {"tranDate":"20170215","tranTime":"16:39:05","batchNo":"htwx2017021516390556179","ip":"10.10.255.39","system":"epad","location":"xxxxxxxxxx","equipmentType":"IOS","equipment":"IPhone7","equipmentID":"1234567","data":{"transNo":"0000002017021516390556179","userCode":"123456","passWord":"zhangsan@ehuatai.com","newPassWord":"xxxxxxxxxxx"}}
     */

    public String code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * tranDate : 20170215
         * tranTime : 16:39:05
         * batchNo : htwx2017021516390556179
         * ip : 10.10.255.39
         * system : epad
         * location : xxxxxxxxxx
         * equipmentType : IOS
         * equipment : IPhone7
         * equipmentID : 1234567
         * data : {"transNo":"0000002017021516390556179","userCode":"123456","passWord":"zhangsan@ehuatai.com","newPassWord":"xxxxxxxxxxx"}
         */

        public String tranDate;
        public String tranTime;
        public String batchNo;
        public String ip;
        public String system;
        public String location;
        public String equipmentType;
        public String equipment;
        public String equipmentID;
        public DataBean data;

        public static class DataBean {
            /**
             * transNo : 0000002017021516390556179
             * userCode : 123456
             * passWord : zhangsan@ehuatai.com
             * newPassWord : xxxxxxxxxxx
             */

            public String transNo;
            public String userCode;
            public String passWord;
            public String newPassWord;
        }
    }
}

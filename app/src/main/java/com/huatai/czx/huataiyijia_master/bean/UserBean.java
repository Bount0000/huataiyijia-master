package com.huatai.czx.huataiyijia_master.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lenovo on 2018/3/16.
 */

public class UserBean extends RealmObject {
   @PrimaryKey
    private String  policyId;
    private String  policyCode;
    private String createDate;
    private String  updateDate;
    private String insureDate;
    private String  productName;
    private String applicantName;
    private String  policyStatus;
    private String jsonContent;
    private String  relation;
    private String createUserid;
    private String  applicantId;
    private String  insureId;
    private String  insureName;
    private String amount;
    private String  premium;
    private String updatetime;
    private String  isDelete;
    private String hintPayFees;
    private String  sendPolicy;
    private String adviceNote;
    private String  bankSign;
    private String applicantSign;
    private String insuredSign;
    private String  policySign;
    private String  hasHuomian;
    private String  isAutoPay;
    private String  dividendReceive;
    private String insuredCode;
    private String  problemStatus;
    private String jumpView;

}

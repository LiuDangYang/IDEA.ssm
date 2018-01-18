package com.baidu.model;

import java.util.Date;

/**
 * Created by 67545 on 2017/12/15.
 */
public class Reconciliation {
    private String productName;
    private String sonCode;
    private String parentCode;
    private String id;
    private Date createDate;

    public void setId(String id) {
        this.id = id;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSonCode(String sonCode) {
        this.sonCode = sonCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getSonCode() {
        return sonCode;
    }

    public String getParentCode() {
        return parentCode;
    }
}

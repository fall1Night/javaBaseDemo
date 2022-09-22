package com.example.learningdemo.recordLog;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Test implements Serializable {

    @FieldCompare(chineseName = "线上")
    private String servicePriceOnline;


    @FieldCompare(chineseName = "服务费收取方式", properties = "1:固定金额,2:百分比")
    private Integer serviceFeeCollectWay;


    @FieldCompare(chineseName = "线下")
    private BigDecimal servicePriceOffline;

    private Integer id;

    private Integer serviceId;

    private Integer type;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServiceFeeCollectWay() {
        return serviceFeeCollectWay;
    }

    public void setServiceFeeCollectWay(Integer serviceFeeCollectWay) {
        this.serviceFeeCollectWay = serviceFeeCollectWay;
    }

    public String getServicePriceOnline() {
        return servicePriceOnline;
    }

    public void setServicePriceOnline(String servicePriceOnline) {
        this.servicePriceOnline = servicePriceOnline;
    }

    public BigDecimal getServicePriceOffline() {
        return servicePriceOffline;
    }

    public void setServicePriceOffline(BigDecimal servicePriceOffline) {
        this.servicePriceOffline = servicePriceOffline;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public static void main(String[] args) throws Exception {
        Test newTest = new Test();
        newTest.setServiceFeeCollectWay(1);
        newTest.setServicePriceOnline("222");
        Test oldTest = new Test();
        oldTest.setServiceFeeCollectWay(2);
        oldTest.setServicePriceOnline("111");
        List<Map<String, Object>> compareLog = SerializableFieldCompare.compare(Test.class, newTest, oldTest);
        List<Log> logList = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : compareLog) {
            // Log log=Test.mapToEntity(stringObjectMap,Log.class);
            //map 转化为实体类
            Log log = JSONObject.parseObject(JSONObject.toJSONString(stringObjectMap), Log.class);
            logList.add(log);
        }
        System.out.println("111");
    }


}

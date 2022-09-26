package com.example.learningdemo.recordLog;

public class Log {

    private String id;
    private String ParamName;
    private String ParamChineseName;
    private String oldParam;
    private String newParam;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamName() {
        return ParamName;
    }

    public void setParamName(String paramName) {
        ParamName = paramName;
    }

    public String getParamChineseName() {
        return ParamChineseName;
    }

    public void setParamChineseName(String paramChineseName) {
        ParamChineseName = paramChineseName;
    }

    public String getOldParam() {
        return oldParam;
    }

    public void setOldParam(String oldParam) {
        this.oldParam = oldParam;
    }

    public String getNewParam() {
        return newParam;
    }

    public void setNewParam(String newParam) {
        this.newParam = newParam;
    }
}

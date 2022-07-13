package com.example.learningdemo.reflection_7.reflectionDemo;

import com.example.learningdemo.Enum_4.FruitEnum;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

public enum ChannelEnum {

    defaultChannel(0, "defaultChannel"),
    TikTok(1, "TikTok"),
    BiliBili(2, "BiliBili");

    private Integer code;
    private String msg;




    // 通过Code获取其枚举的信息
    public static String getMsgByCode(Integer code) {
        if (code == null) {
            return null;
        } else {
            for (FruitEnum fruitEnum : FruitEnum.values()) {
                if (fruitEnum.getCode().equals(code)) {
                    return fruitEnum.getMsg();
                }
            }
        }
        return null;
    }

    // 通过msg获取其枚举的值
    public static Integer getCodeByMsg(String msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return null;
        } else {
            for (FruitEnum fruitEnum : FruitEnum.values()) {
                if (fruitEnum.getMsg().equals(msg)) {
                    return fruitEnum.getCode();
                }
            }
        }
        return null;

    }


    ChannelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

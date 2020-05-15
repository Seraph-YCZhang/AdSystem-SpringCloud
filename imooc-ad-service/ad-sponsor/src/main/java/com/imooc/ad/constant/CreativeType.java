package com.imooc.ad.constant;

import lombok.Getter;

@Getter
public enum CreativeType {

    IMAGE(1, "PICTURE"),
    VIDEO(2, "VIDEO"),
    TEXT(3, "TEXT");

    private int type;
    private String desc;

    CreativeType(int type, String desc){
        this.type = type;
        this.desc = desc;
    }
}

package com.imooc.ad.index;

import lombok.Getter;

@Getter
public enum CommonStatus {
    VALID(1,"effective"),
    INVALID(0,"ineffective");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

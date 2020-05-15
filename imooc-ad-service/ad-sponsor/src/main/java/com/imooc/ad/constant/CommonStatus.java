package com.imooc.ad.constant;

import lombok.Getter;

@Getter
public enum CommonStatus {
    VALID(1, "VALID STATUS"),
    INVALID(0, "INVALID STATUS");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;

    }
}

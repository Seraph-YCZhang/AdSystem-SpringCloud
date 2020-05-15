package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private String deviceCode;
    // max address
    private String mac;
    // ip
    private String ip;
    // model
    private String model;
    private String displaySize;
    private String screenSize;
    // device serial name
    private String serialName;
}

package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {

    // app code
    private String appCode;
    // app namey
    private String appName;
    private String packageName;
    // activity name
    private String activityName;

}

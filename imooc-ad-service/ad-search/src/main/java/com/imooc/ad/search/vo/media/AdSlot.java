package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdSlot {
    // ad slot code
    private String adSlotCode;
    // position type
    private Integer positionType;
    private Integer weight;
    private Integer height;
    // video / pic
    private List<Integer> type;

    // lowest price
    private Integer minCpm;
}


package com.imooc.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// represent table in template
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonTable {
    private String tableName;
    private Integer level;


    private List<Column> insert;
    private List<Column> update;
    private List<Column> delete;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Column {
        private String column;
    }
}

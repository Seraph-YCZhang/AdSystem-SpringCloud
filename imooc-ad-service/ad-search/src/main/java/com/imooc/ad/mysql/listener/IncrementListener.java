package com.imooc.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.imooc.ad.mysql.constant.Constant;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.mysql.dto.BinlogRowData;
import com.imooc.ad.mysql.dto.MySqlRowData;
import com.imooc.ad.mysql.dto.TableTemplate;
import com.imooc.ad.mysql.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class IncrementListener implements Ilistener{
    @Resource(name  = "indexSender")
    private ISender sender;

    public IncrementListener(ISender sender) {
        this.sender = sender;
    }

    @Autowired
    private AggregationListener aggregationListener;


    @Override
    @PostConstruct
    public void register() {
        log.info("IncrementListener register db and table info");
        Constant.table2Db.forEach((k,v) ->
                aggregationListener.register(v,k,this));
    }

    @Override
    public void onEvent(BinlogRowData eventData) {
        TableTemplate table = eventData.getTable();
        EventType eventType = eventData.getEventType();
        // packaged as final transmit data
        MySqlRowData rowData = new MySqlRowData();
        rowData.setTableName(table.getTableName());
        rowData.setLevel(eventData.getTable().getLevel());
        OpType opType  = OpType.to(eventType);
        rowData.setOpType(opType);

        // take this operation corresponding list
        List<String> fieldList = table.getOpTypeFieldSetMap().get(opType);
        if(null == fieldList) {
            log.warn("{} not support for {}", opType, table.getTableName());
            return;
        }

        for(Map<String, String> afterMap : eventData.getAfter()) {
            Map<String, String> _afterMap = new HashMap<>();
            for(Map.Entry<String, String> entry : afterMap.entrySet()) {
                String colName = entry.getKey();
                String colVal = entry.getValue();
                _afterMap.put(colName, colVal);
            }

            rowData.getFieldValueMap().add(_afterMap);
        }
        sender.sender(rowData);

    }
}

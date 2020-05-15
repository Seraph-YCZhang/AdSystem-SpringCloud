package com.imooc.ad.mysql.sender;

import com.imooc.ad.mysql.dto.MySqlRowData;

public interface ISender {
    void  sender(MySqlRowData rowData);
}

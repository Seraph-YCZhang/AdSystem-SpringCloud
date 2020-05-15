package com.imooc.ad.runner;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Slf4j
public class BinlogRunner implements CommandLineRunner {
    @Autowired
    private BinaryLogClient client;

    public BinlogRunner(BinaryLogClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Coming in BinlogRunner...");
        client.connect();
    }
}

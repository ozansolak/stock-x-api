package com.ozan.hub.stockxapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@EnableJpaRepositories({"com.ozan.hub.stockxapi.repository"})
@EntityScan({"com.ozan.hub.stockxapi.model.data"})
@SpringBootTest
@RunWith(SpringRunner.class)
class StockXApiApplicationTests {

    @Test
    void contextLoads() {
    }

}

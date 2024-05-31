package com.ozan.hub.stockxapi.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozan.hub.stockxapi.model.data.Stock;
import com.ozan.hub.stockxapi.model.data.StockExchange;
import com.ozan.hub.stockxapi.model.dto.IAddStock;
import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import com.ozan.hub.stockxapi.repository.StockExchangeRepository;
import com.ozan.hub.stockxapi.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class StockExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listStockExchange() throws Exception {
        //Success
        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("testList");
        stockExchange.setDescription("Description");
        stockExchange.setLiveInMarket(false);

        Set<Stock> stocks = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            stocks.add(stockRepository.save(new Stock(ICreateStock.CreateStockReq.builder().name(String.format("stockTest%s", i)).description("stockTest").currentPrice(10.0).build())));
        }
        stockExchange.setStocks(stocks);
        stockExchangeRepository.save(stockExchange);

        mockMvc.perform(get("/api/v1/stock-exchange/testList").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

        //Not Found
        mockMvc.perform(get("/api/v1/stock-exchange/test12223").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());
    }

    @Test
    void addStock() throws Exception {
        //Success
        Stock created = stockRepository.save(new Stock(ICreateStock.CreateStockReq.builder().name(String.format("stockTest Add")).description("stockTest").currentPrice(10.0).build()));
        IAddStock.AddStockReq req = IAddStock.AddStockReq.builder().stockId(created.getId()).build();
        StockExchange stockExchange = new StockExchange();

        stockExchange.setName("testUpdate");
        stockExchange.setDescription("Description");
        stockExchange.setLiveInMarket(false);

        stockExchangeRepository.save(stockExchange);

        mockMvc.perform(put("/api/v1/stock-exchange/testUpdate").content(objectMapper.writeValueAsString(req)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

        //Not Found
        mockMvc.perform(put("/api/v1/stock-exchange/testUpdateFail").content(objectMapper.writeValueAsString(req)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());
    }

    @Test
    void removeStock() throws Exception {
        //Success
        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("testDelete");
        stockExchange.setDescription("Description");
        stockExchange.setLiveInMarket(false);

        Set<Stock> stocks = new HashSet<>();
        Stock created = stockRepository.save(new Stock(ICreateStock.CreateStockReq.builder().name("stockTestDelete").description("stockTest").currentPrice(10.0).build()));
        stocks.add(created);
        stockExchange.setStocks(stocks);
        stockExchangeRepository.save(stockExchange);

        mockMvc.perform(delete("/api/v1/stock-exchange/testDelete").param("stockId", String.valueOf(created.getId())).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

        //Stock exchange not found
        mockMvc.perform(delete("/api/v1/stock-exchange/testDeleteFail").param("stockId", String.valueOf(created.getId())).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());

        //Stock not in list
        mockMvc.perform(delete("/api/v1/stock-exchange/testDeleteFail").param("stockId", String.valueOf(500L)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());
    }
}
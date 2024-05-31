package com.ozan.hub.stockxapi.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozan.hub.stockxapi.model.data.Stock;
import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import com.ozan.hub.stockxapi.model.dto.IUpdatePrice;
import com.ozan.hub.stockxapi.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createStock() throws Exception {
        //Successful
        ICreateStock.CreateStockReq reqBody = ICreateStock.CreateStockReq.builder().name("test").description("test desc").currentPrice(10.0).build();
        mockMvc.perform(post("/api/v1/stock").content(objectMapper.writeValueAsString(reqBody)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());

        //Name required
        ICreateStock.CreateStockReq nameRequiredReq = ICreateStock.CreateStockReq.builder().description("test desc").currentPrice(10.0).build();
        mockMvc.perform(post("/api/v1/stock").content(objectMapper.writeValueAsString(nameRequiredReq)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());

        //Already exists
        mockMvc.perform(post("/api/v1/stock").content(objectMapper.writeValueAsString(reqBody)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is5xxServerError());
    }

    @Test
    void updateStockPrice() throws Exception {
        //Successful
        ICreateStock.CreateStockReq stockReq = ICreateStock.CreateStockReq.builder().name("test update").description("test desc").currentPrice(10.0).build();
        Stock created = stockRepository.save(new Stock(stockReq));
        IUpdatePrice.UpdatePriceReq req = IUpdatePrice.UpdatePriceReq.builder().id(created.getId()).price(20.0).build();
        mockMvc.perform(put("/api/v1/stock").content(objectMapper.writeValueAsString(req)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

        //Stock Not Found
        IUpdatePrice.UpdatePriceReq failReq = IUpdatePrice.UpdatePriceReq.builder().id(500L).price(20.0).build();
        mockMvc.perform(put("/api/v1/stock").content(objectMapper.writeValueAsString(failReq)).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is5xxServerError());
    }

    @Test
    void deleteStock() throws Exception {
        //Successful
        ICreateStock.CreateStockReq stockReq = ICreateStock.CreateStockReq.builder().name("test delete").description("test desc").currentPrice(10.0).build();
        Stock created = stockRepository.save(new Stock(stockReq));
        mockMvc.perform(delete("/api/v1/stock").param("id", String.valueOf(created.getId())).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }
}
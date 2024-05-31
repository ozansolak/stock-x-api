package com.ozan.hub.stockxapi.service.impl;

import com.ozan.hub.stockxapi.exception.*;
import com.ozan.hub.stockxapi.model.data.Stock;
import com.ozan.hub.stockxapi.model.data.StockExchange;
import com.ozan.hub.stockxapi.model.dto.IAddStock;
import com.ozan.hub.stockxapi.model.dto.IListStockExchange;
import com.ozan.hub.stockxapi.repository.StockExchangeRepository;
import com.ozan.hub.stockxapi.repository.StockRepository;
import com.ozan.hub.stockxapi.service.StockExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Optional;

@Slf4j
@Service
public class StockExchangeServiceImpl implements StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;

    private final StockRepository stockRepository;

    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, StockRepository stockRepository) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public IListStockExchange.ListStockExchangeResp getStockExchange(String name) {
        try {
            StockExchange stockExchange = getStockExchangeByName(name);
            return new IListStockExchange.ListStockExchangeResp(stockExchange);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new ServiceUnavailableException(e.getLocalizedMessage());
        }
    }

    @Override
    public Boolean addStockToStockExchange(IAddStock.AddStockReq addStockReq, String name) {
        try {
            StockExchange stockExchange = getStockExchangeByName(name);
            if (CollectionUtils.isEmpty(stockExchange.getStocks())) {
                stockExchange.setStocks(new HashSet<>());
            }
            Optional<Stock> optionalStock = stockRepository.findById(addStockReq.getStockId());
            if (optionalStock.isEmpty()) {
                throw new EntityNotFoundException(String.format("Stock with id %s not found", addStockReq.getStockId()));
            }
            if (stockExchange.getStocks().stream().anyMatch(stock -> stock.getId().equals(addStockReq.getStockId()))) {
                throw new StockAlreadyInStockExchangeException(String.format("%s already exists in Stock Exchange", addStockReq.getStockId()));
            }
            stockExchange.getStocks().add(optionalStock.get());
            stockExchange.setLiveInMarket(stockExchange.getStocks().size() >= 5);
            stockExchangeRepository.save(stockExchange);
            return true;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new ServiceUnavailableException(e.getLocalizedMessage());
        }
    }

    @Override
    public Boolean removeStockFromStockExchange(Long stockId, String name) {
        try {
            StockExchange stockExchange = getStockExchangeByName(name);
            Optional<Stock> optionalStock = stockRepository.findById(stockId);
            if (optionalStock.isEmpty()) {
                throw new EntityNotFoundException(String.format("Stock with id %s not found", stockId));
            }
            if (CollectionUtils.isEmpty(stockExchange.getStocks())) {
                throw new StockNotInStockExchangeException(String.format("Stock with id %s not found in Stock Exchange", stockId));
            }
            stockExchange.getStocks().removeIf(stock -> stockId.equals(stock.getId()));
            stockExchange.setLiveInMarket(stockExchange.getStocks().size() >= 5);
            stockExchangeRepository.save(stockExchange);
            return true;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new ServiceUnavailableException(e.getLocalizedMessage());
        }
    }

    private StockExchange getStockExchangeByName(String name) {
        StockExchange stockExchange = stockExchangeRepository.findByName(name);
        if (stockExchange == null) {
            throw new EntityNotFoundException(String.format("Stock Exchange : %s not found", name));
        }
        return stockExchange;
    }
}

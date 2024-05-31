package com.ozan.hub.stockxapi.service.impl;

import com.ozan.hub.stockxapi.exception.ServiceUnavailableException;
import com.ozan.hub.stockxapi.exception.StockAlreadyExistsException;
import com.ozan.hub.stockxapi.exception.EntityNotFoundException;
import com.ozan.hub.stockxapi.model.data.Stock;
import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import com.ozan.hub.stockxapi.model.dto.IUpdatePrice;
import com.ozan.hub.stockxapi.repository.StockRepository;
import com.ozan.hub.stockxapi.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public ICreateStock.CreateStockResp createStock(ICreateStock.CreateStockReq createStockReq) {
        try {
            Stock stock = stockRepository.findStockByNameAndDeleted(createStockReq.getName(), false);
            if(stock != null){
                throw new StockAlreadyExistsException(String.format("%s already exists", createStockReq.getName()));
            }
            return new ICreateStock.CreateStockResp(stockRepository.saveAndFlush(new Stock(createStockReq)));
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new ServiceUnavailableException(e.getLocalizedMessage());
        }
    }

    @Override
    public IUpdatePrice.UpdatePriceResp updatePrice(IUpdatePrice.UpdatePriceReq updatePriceReq) {
        try{
            Optional<Stock> found = stockRepository.findById(updatePriceReq.getId());
            if(found.isEmpty()){
                throw new EntityNotFoundException(String.format("Id : %s not found", updatePriceReq.getId()));
            }
            Stock stock = found.get();
            stock.setCurrentPrice(updatePriceReq.getPrice());
            stock.setLastUpdate(new Date().getTime());
            return new IUpdatePrice.UpdatePriceResp(stockRepository.saveAndFlush(stock));
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new ServiceUnavailableException(e.getLocalizedMessage());
        }
    }

    @Override
    public Boolean deleteStock(Long id) {
        try {
            stockRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new ServiceUnavailableException(e.getLocalizedMessage());
        }
    }
}

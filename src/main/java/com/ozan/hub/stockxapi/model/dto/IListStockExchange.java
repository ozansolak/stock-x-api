package com.ozan.hub.stockxapi.model.dto;

import com.ozan.hub.stockxapi.model.data.Stock;
import com.ozan.hub.stockxapi.model.data.StockExchange;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

public interface IListStockExchange {

    @NoArgsConstructor
    @Getter
    class ListStockExchangeResp {
        private Long id;
        private String name;
        private String description;
        private Boolean liveInMarket;
        private List<LSEStock> stocks;

        public ListStockExchangeResp(StockExchange stockExchange){
            this.id = stockExchange.getId();
            this.name = stockExchange.getName();
            this.description = stockExchange.getDescription();
            if(!CollectionUtils.isEmpty(stockExchange.getStocks())){
                stocks = new LinkedList<>();
                stockExchange.getStocks().forEach(stock -> stocks.add(new LSEStock(stock)));
            }
            if(!CollectionUtils.isEmpty(this.stocks) && this.stocks.size() >= 5){
                this.liveInMarket = true;
            }
        }
    }

    @NoArgsConstructor
    @Getter
    class LSEStock{
        private Long id;
        private String name;
        private String description;
        private Double currentPrice;
        private Long lastUpdate;

        public LSEStock(Stock stock) {
            this.id = stock.getId();
            this.name = stock.getName();
            this.description = stock.getDescription();
            this.currentPrice = stock.getCurrentPrice();
            this.lastUpdate = stock.getLastUpdate();
        }
    }
}

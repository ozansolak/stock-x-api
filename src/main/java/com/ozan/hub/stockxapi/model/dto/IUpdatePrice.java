package com.ozan.hub.stockxapi.model.dto;

import com.ozan.hub.stockxapi.model.data.Stock;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public interface IUpdatePrice {

    @NoArgsConstructor
    @Getter
    @SuperBuilder
    class UpdatePriceReq{
        @NotNull(message = "Id is required")
        private Long id;
        @NotNull(message = "Price is required")
        private Double price;
    }

    @NoArgsConstructor
    @Getter
    class UpdatePriceResp{
        private Long id;
        private Double currentPrice;
        private Long lastUpdate;

        public UpdatePriceResp(Stock stock){
            this.id = stock.getId();
            this.currentPrice = stock.getCurrentPrice();
            this.lastUpdate = stock.getLastUpdate();
        }
    }
}

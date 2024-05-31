package com.ozan.hub.stockxapi.model.dto;

import com.ozan.hub.stockxapi.model.data.Stock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public interface ICreateStock {

    @NoArgsConstructor
    @Getter
    @SuperBuilder
    class CreateStockReq{
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Description is required")
        private String description;
        @NotNull(message = "Current Price is required")
        private Double currentPrice;
    }

    @NoArgsConstructor
    @Getter
    class CreateStockResp{
        private Long id;
        private String name;
        private String description;
        private Double currentPrice;
        private Long lastUpdate;

        public CreateStockResp(Stock stock) {
            this.id = stock.getId();
            this.name = stock.getName();
            this.description = stock.getDescription();
            this.currentPrice = stock.getCurrentPrice();
            this.lastUpdate = stock.getLastUpdate();
        }
    }
}

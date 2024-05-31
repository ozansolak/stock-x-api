package com.ozan.hub.stockxapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public interface IAddStock {

    @NoArgsConstructor
    @Getter
    @SuperBuilder
    class AddStockReq{
        @NotNull(message = "Stock Id is required")
        private Long stockId;
    }
}

package com.ozan.hub.stockxapi.model.data;

import com.ozan.hub.stockxapi.model.data.base.BaseEntity;
import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "stock")
@SQLDelete(sql = "UPDATE stock SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double currentPrice;

    private Long lastUpdate;

    public Stock() {
        super();
    }

    public Stock(ICreateStock.CreateStockReq createStock){
        super();
        this.name = createStock.getName();
        this.description = createStock.getDescription();
        this.currentPrice = createStock.getCurrentPrice();
        this.lastUpdate = System.currentTimeMillis();
    }
}

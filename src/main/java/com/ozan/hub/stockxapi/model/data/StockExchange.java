package com.ozan.hub.stockxapi.model.data;

import com.ozan.hub.stockxapi.model.data.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "stock_exchange")
@SQLDelete(sql = "UPDATE stock_exchange SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class StockExchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Boolean liveInMarket;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "s_exchange_stock", joinColumns = {@JoinColumn(name = "stock_exchange_id")}, inverseJoinColumns = {@JoinColumn(name = "stock_id")})
    Set<Stock> stocks = new HashSet<>();

    public StockExchange() {
        super();
    }
}

create table if not exists stock
(
    id            bigint auto_increment
        primary key,
    created_at    datetime(6)  null,
    created_by    varchar(255) null,
    deleted       bit          null,
    updated_at    datetime(6)  null,
    updated_by    varchar(255) null,
    current_price double       null,
    description   varchar(255) null,
    last_update   bigint       null,
    name          varchar(255) null
);
create table if not exists stock_exchange
(
    id             bigint auto_increment
        primary key,
    created_at     datetime(6)  null,
    created_by     varchar(255) null,
    deleted        bit          null,
    updated_at     datetime(6)  null,
    updated_by     varchar(255) null,
    description    varchar(255) null,
    live_in_market bit          null,
    name           varchar(255) null
);

create table if not exists s_exchange_stock
(
    stock_exchange_id bigint not null,
    stock_id          bigint not null,
    primary key (stock_exchange_id, stock_id),
    constraint FK2g4occog967kwamkah1gyr98d
        foreign key (stock_exchange_id) references stock_exchange (id),
    constraint FKapcjn7pbgeddjytuuc06yp9vi
        foreign key (stock_id) references stock (id)
);

INSERT INTO stock_exchange (name, description, live_in_market, deleted) VALUES ( 'BIST100' , 'Borsa Istanbul' , FALSE, FALSE);




package com.example.market.controller;

import com.example.market.model.Model;

public interface TableController<M extends Model<M>> {

    void create(M model);

    void delete(long index);

    void update(long index, M model);

    M newOne();

}

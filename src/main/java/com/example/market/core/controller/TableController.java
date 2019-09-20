package com.example.market.core.controller;

import com.example.market.core.data.Storage;
import com.example.market.core.model.Model;

import java.util.function.Supplier;

public interface TableController<M extends Model<M>> {

    void create(M model);

    void delete(long id);

    void update(M model);

    M newOne();

    void setStorage(Storage<M> storage);

    static <M extends Model<M>> TableController<M> newInstance(Supplier<M> newModelFactory) {
        return new BaseTableController<>() {
            @Override
            public M newOne() {
                return newModelFactory.get();
            }
        };
    }

}

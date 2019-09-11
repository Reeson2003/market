package com.example.market.data;

import com.example.market.model.Model;

import java.util.Map;

public interface DataSupplier<M extends Model<M>> {

    Map<Long, M> getAll();
}

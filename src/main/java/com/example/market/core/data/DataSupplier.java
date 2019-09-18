package com.example.market.core.data;

import com.example.market.core.model.Model;

import java.util.Map;

public interface DataSupplier<M extends Model<M>> {

    Map<Long, M> getAll();
}

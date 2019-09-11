package com.example.market.model;

import java.util.Map;

public interface DataSupplier<M extends Model<M>> {

    Map<Long, M> getAll();
}

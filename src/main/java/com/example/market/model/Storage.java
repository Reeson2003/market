package com.example.market.model;

import java.util.Map;

public interface Storage<M extends Model<M>> {

    Map<Long, M> getAll();

    void add(M model);

    void delete(long index);

    void update(long index, M model);
}

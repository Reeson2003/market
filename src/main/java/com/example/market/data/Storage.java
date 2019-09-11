package com.example.market.data;

import com.example.market.model.Model;

public interface Storage<M extends Model<M>> {

    void add(M model);

    void delete(long index);

    void update(long index, M model);
}

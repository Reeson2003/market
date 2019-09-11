package com.example.market.consoleview.model;

import com.example.market.data.DataSupplier;
import com.example.market.data.Storage;
import com.example.market.model.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<M extends Model<M>>
        implements Storage<M>, DataSupplier<M> {

    private final Map<Long, M> data = new HashMap<>();

    private long idGenerator = 0L;

    @Override
    public Map<Long, M> getAll() {
        return Collections.unmodifiableMap(data);
    }

    @Override
    public void add(M model) {
        data.put(generateId(), model);
    }

    @Override
    public void delete(long index) {
        data.remove(index);
    }

    @Override
    public void update(long index, M model) {
        data.put(index, model);
    }

    private long generateId() {
        return idGenerator++;
    }
}

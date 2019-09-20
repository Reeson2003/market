package com.example.market.consoleview.model;

import com.example.market.core.data.Repository;
import com.example.market.core.model.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryStorage<M extends Model<M>>
        implements Repository<M> {

    protected final Map<Long, M> data = new ConcurrentHashMap<>();

    private AtomicLong idGenerator = new AtomicLong(0L);

    @Override
    public Collection<M> getAll() {
        return Collections.unmodifiableCollection(data.values());
    }

    @Override
    public void add(M model) {
        final long id = generateId();
        model.setId(id);
        data.put(id, model);
    }

    @Override
    public void delete(long index) {
        data.remove(index);
    }

    @Override
    public void update(M model) {
        data.put(model.getId(), model);
    }

    private long generateId() {
        return idGenerator.incrementAndGet();
    }

}

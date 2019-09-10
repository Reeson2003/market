package com.example.market.consoleview;

import com.example.market.controller.TableControllerImpl;
import com.example.market.model.Model;
import com.example.market.model.Storage;

import java.util.Map;
import java.util.function.Supplier;

public class Application<M extends Model<M>> {

    private final Supplier<M> newModelFactory;

    public Application(Supplier<M> newModelFactory) {
        this.newModelFactory = newModelFactory;
    }

    public static void main(String[] args) {
        // new Application<>(Car::new).run();
        new Application<>(Product::new).run();
    }

    private void run() {
        final InMemoryStorage<M> storage = new InMemoryStorage<>();
        final TableControllerImpl<M> controller = listController(storage);
        final ConsoleTableView<M> view = new ConsoleTableView<>(controller);
        while (true) {
            final Map<Long, M> all = storage.getAll();
            view.show(all);
        }
    }

    private TableControllerImpl<M> listController(Storage<M> storage) {
        return new TableControllerImpl<>(storage) {
            @Override
            public M newOne() {
                return newModelFactory.get();
            }
        };
    }
}

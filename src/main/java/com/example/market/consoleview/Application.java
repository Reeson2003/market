package com.example.market.consoleview;

import com.example.market.controller.TableControllerImpl;
import com.example.market.model.Storage;

import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final InMemoryStorage<Product> storage = new InMemoryStorage<>();
        final TableControllerImpl<Product> controller = listController(storage);
        final ConsoleTableView<Product> view = new ConsoleTableView<>(controller);
        while (true) {
            final Map<Long, Product> all = storage.getAll();
            view.show(all);
        }
    }

    private static TableControllerImpl<Product> listController(Storage<Product> storage) {
        return new TableControllerImpl<>(storage) {
            @Override
            public Product newOne() {
                return new Product("", "", 0);
            }
        };
    }
}

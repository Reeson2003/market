package com.example.market;

import com.example.market.consoleview.model.InMemoryStorage;
import com.example.market.consoleview.model.Product;
import com.example.market.consoleview.view.ConsoleTableView;
import com.example.market.controller.TableControllerImpl;
import com.example.market.model.Model;
import com.example.market.module.TableModule;

import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        final InMemoryStorage<Product> mInMemoryStorage = new InMemoryStorage<>();
        new TableModule<>(mInMemoryStorage, mInMemoryStorage, tableController(Product::new), new ConsoleTableView<>()).run();
    }

    private static <M extends Model<M>> TableControllerImpl<M> tableController(Supplier<M> newModelFactory) {
        return new TableControllerImpl<>() {
            @Override
            public M newOne() {
                return newModelFactory.get();
            }
        };
    }
}

package com.example.market.consoleview.view;

import com.example.market.model.Model;
import com.example.market.view.EditView;

import java.util.List;

public class ConsoleEditView<M extends Model<M>>
        extends ConsoleView
        implements EditView<M> {

    private static final int SIZE = 20;

    @Override
    public M edit(M model) {
        final List<String> propertyNames = model.getPropertyNames();
        while (true) {
            clearScreen();
            for (int i = 0; i < propertyNames.size(); i++) {
                final String propertyName = propertyNames.get(i);
                final String propertyValue = model.getPropertyValue(propertyName);
                System.out.println(toSize(String.valueOf(i), 5, ' ') + "| " + toSize(propertyName, SIZE, ' ') + "|" + toSize(propertyValue, SIZE, ' '));
            }
            if (askForConfirm()) {
                break;
            }
            final int number = askForNumber(propertyNames.size());
            final String value = getValue();
            model.setPropertyValue(propertyNames.get(number), value);
        }
        return model;
    }


}

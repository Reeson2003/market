package com.example.market.consoleview.view;

import com.example.market.model.Model;
import com.example.market.model.PropertyDefinition;
import com.example.market.view.EditView;

import java.util.List;

public class ConsoleEditView<M extends Model<M>>
        extends ConsoleView
        implements EditView<M> {

    private static final int SIZE = 20;

    @Override
    public M edit(M model) {
        final List<PropertyDefinition> propertyDefinitions = model.getPropertyDefinitions();
        while (true) {
            clearScreen();
            for (int i = 0; i < propertyDefinitions.size(); i++) {
                final String propertyName = propertyDefinitions.get(i).getPropertyName();
                final String propertyDisplayedName = propertyDefinitions.get(i).getPropertyDisplayedName();
                final String propertyValue = model.getPropertyValue(propertyName);
                System.out.println(toSize(String.valueOf(i), 5, ' ') + "| " + toSize(propertyDisplayedName, SIZE, ' ') + "|" + toSize(propertyValue, SIZE, ' '));
            }
            if (askForConfirm()) {
                break;
            }
            final int number = askForNumber(propertyDefinitions.size());
            final String value = getValue();
            model.setPropertyValue(propertyDefinitions.get(number).getPropertyName(), value);
        }
        return model;
    }


}

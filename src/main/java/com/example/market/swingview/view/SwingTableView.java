package com.example.market.swingview.view;

import com.example.market.core.controller.TableController;
import com.example.market.core.data.DataSupplier;
import com.example.market.core.model.Model;
import com.example.market.core.model.PropDef;
import com.example.market.core.view.TableView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class SwingTableView<M extends Model<M>>
        extends JPanel
        implements TableView<M> {

    private List<String> propertyDisplayNames;

    private List<String> propertyNames;

    private TableController<M> controller;

    private DataSupplier<M> dataSupplier;

    private JButton createButton;

    private JButton deleteButton;

    private JButton closeButton;

    private Box contents;

    private AtomicBoolean close;

    private DefaultTableModel tableModel;

    public SwingTableView() {
        setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        createButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        closeButton = new JButton("close");
        toolBar.add(createButton);
        toolBar.add(deleteButton);
        toolBar.add(closeButton);
        contents = new Box(BoxLayout.Y_AXIS);
        add(toolBar, BorderLayout.NORTH);
        add(contents, BorderLayout.CENTER);
        close = new AtomicBoolean(false);
        closeButton.addActionListener(e -> close.set(true));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void show() {
        propertyDisplayNames = controller.newOne()
                                         .getPropDefs()
                                         .stream()
                                         .map(PropDef::getPropertyDisplayedName)
                                         .collect(Collectors.toList());
        propertyNames = controller.newOne()
                                  .getPropDefs()
                                  .stream()
                                  .map(PropDef::getPropertyName)
                                  .collect(Collectors.toList());
        tableModel = new DefaultTableModel(propertyDisplayNames.toArray(), 0);
        final JTable table = new JTable(tableModel);
        deleteButton.addActionListener(event -> {
            final int[] selectedRows = table.getSelectedRows();
            SwingUtilities.invokeLater(() -> Arrays.stream(selectedRows)
                                                   .forEach(index -> tableModel.removeRow(index)));
        });
        createButton.addActionListener(event -> {
            final M model = controller.newOne();
            SwingUtilities.invokeLater(() -> addNewRow(model));
        });
        contents.add(new JScrollPane(table));
        contents.setVisible(true);
        final Map<Long, M> data = dataSupplier.getAll();
        createTable(data);
    }

    @Override
    public void setController(TableController<M> controller) {
        this.controller = controller;
    }

    @Override
    public void setDataSupplier(DataSupplier<M> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    private void createTable(Map<Long, M> data) {
        data.values()
            .forEach(this::addNewRow);
    }

    private void addNewRow(M model) {
        final Object[] values = propertyNames.stream()
                                             .map(model::getPropertyValue)
                                             .toArray();
        tableModel.addRow(values);
    }

}

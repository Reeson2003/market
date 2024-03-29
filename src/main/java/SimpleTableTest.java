import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.TimeUnit;

public class SimpleTableTest
        extends JFrame {

    // Данные для таблиц
    private Object[][] array = new String[][]{{"Сахар", "кг", "1.5"},
                                              {"Мука", "кг", "4.0"},
                                              {"Молоко", "л", "2.2"}};

    // Заголовки столбцов
    private Object[] columnsHeader = new String[]{"Наименование", "Ед.измерения",
                                                  "Количество"};

    public SimpleTableTest()
            throws InterruptedException {
        super("Простой пример с JTable");
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        // // Простая таблица
        // JTable table1 = new JTable(array, columnsHeader);
        // // Таблица с настройками
        // JTable table2 = new JTable(3, 5);
        // // Настройка таблицы
        // table2.setRowHeight(30);
        // table2.setRowHeight(1, 20);
        // table2.setIntercellSpacing(new Dimension(10, 10));
        // table2.setGridColor(Color.blue);
        // table2.setShowVerticalLines(false);
        // // Данные для таблицы на основе Vector
        // Vector<Vector<String>> data = new Vector<Vector<String>>();
        // // Вектор с заголовками столбцов
        // Vector<String> header = new Vector<String>();
        // // Формирование в цикле массива данных
        // for (int j = 0; j < array.length; j++) {
        //     header.add((String) columnsHeader[j]);
        //     Vector<String> row = new Vector<String>();
        //     for (int i = 0; i < array[j].length; i++) {
        //         row.add((String) array[j][i]);
        //     }
        //     data.add(row);
        // }
        // // Таблица на основе вектора
        // JTable table3 = new JTable(data, header);
        // // Размещение таблиц в панели с блочным расположением
        // Box contents = new Box(BoxLayout.Y_AXIS);
        // contents.add(new JScrollPane(table1));
        // contents.add(new JScrollPane(table2));
        // // Настройка таблицы table3 - цвет фона, цвет выделения
        // table3.setForeground(Color.red);
        // table3.setSelectionForeground(Color.yellow);
        // table3.setSelectionBackground(Color.blue);
        // // Скрытие сетки таблицы
        // table3.setShowGrid(false);
        // // contents.add(new JScrollPane(table3));
        // contents.add(table3);
        // // Вывод окна на экран
        final DefaultTableModel model = new DefaultTableModel(columnsHeader, 0);
        model.addRow(array[0]);
        model.addRow(array[1]);
        final JTable table = new JTable(model);
        setContentPane(table);
        setSize(500, 400);
        setVisible(true);
        TimeUnit.SECONDS.sleep(10);
        model.addRow(array[2]);
    }

    public static void main(String[] args)
            throws InterruptedException {
        new SimpleTableTest();
    }

}
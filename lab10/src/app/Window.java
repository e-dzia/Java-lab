package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa okienka - tworzy i przetwarza informacje wymagane do utworzenia okienka i poprawnego jego wyswietlenia
 */
public class Window {
    private JPanel mainContentPanel;
    private JComboBox comboBoxLanguages;
    private JTable table;
    private JTextField textField1;
    private JButton usunButton;
    private JComboBox comboBox1;

    private DefaultTableModel tableModel;

    public Window() {
        comboBoxLanguages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanguageChoice.languageChoice = comboBoxLanguages.getSelectedItem().toString();
                LanguageChoice.setLocale();
                DataToShow.prepareData();
                textField1.setText(DataToShow.textNumOfObj);
                usunButton.setText(LanguageChoice.messages.getString("remove"));
                addColumns();
            }
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 5) {
                    ShowImage.normal((Icon) DataToShow.data[row][col]);
                }
            }
        });
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = Integer.parseInt(comboBox1.getSelectedItem().toString());
                Main.collection.books.remove(choice);
                DataToShow.prepareData();
                textField1.setText(DataToShow.textNumOfObj);
                addColumns();
            }
        });
        textField1.setEditable(false);
        for (int i = 0; i < LanguageChoice.languages.length; i++){
            comboBoxLanguages.addItem(LanguageChoice.languages[i]);
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Books");
        frame.setSize(1500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Window window = new Window();
        
        ClassLoader cl = window.getClass().getClassLoader();
        ImageIcon img = new ImageIcon(cl.getResource("resources/book.png"));
        frame.setIconImage(img.getImage());
        
        frame.setContentPane(window.mainContentPanel);
        frame.pack();
        frame.setVisible(true);

    }

    private void addColumns() {
        tableModel.setColumnCount(0);
        for (int i = 0; i < DataToShow.columnNames.length; i++){
            tableModel.addColumn(DataToShow.columnNames[i]);
        }

        comboBox1.removeAllItems();
        for (int i = 0; i < DataToShow.data.length; i++){
            comboBox1.addItem(DataToShow.data[i][0]);
        }
    }

    private void createUIComponents() {
        tableModel = new DefaultTableModel() {
            public int getColumnCount() {
                return DataToShow.columnNames.length;
            }
            public int getRowCount() {
                return DataToShow.data.length;
            }
            public Object getValueAt(int row, int col) {
                return DataToShow.data[row][col];
            }
            public String getColumnName(int column) {
                return DataToShow.columnNames[column];
            }
            public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(tableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
    }
}

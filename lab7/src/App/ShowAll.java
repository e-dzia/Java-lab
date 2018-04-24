package App;

import Database.Entities.Client;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class ShowAll  extends JFrame{
    Vector<String> list;
    private JPanel panel1;
    private JList list1;

    ShowAll(Vector<String> list){
        this.list = list;
        list1.setListData(list);
        setTitle("Lista:");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();
    }

    private void createUIComponents() {
        list1 = new JList();
    }
}

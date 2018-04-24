package App;

import Database.DAO.EmployeeDAO;
import Database.Employees;
import Database.Entities.Employee;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class EmployeesMenu extends JFrame {
    private JButton przegladajButton;
    private JButton eksportujButton;
    private JButton importujButton;
    private JPanel panel1;

    public EmployeesMenu() {
        przegladajButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            try {
                Vector<String> employees = new Vector<>();
                for (Employee employee : new EmployeeDAO().getEntities()){
                    employees.add(employee.toString());
                }
                ShowAll showAll = new ShowAll(employees);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }));
        eksportujButton.addActionListener(e -> {
            Date timestamp = new Date();
            String filename = "Emp_"+timestamp+".xml";
            filename = filename.replace(' ','_');
            filename = filename.replace(':','_');
            Employees employees = new Employees();
            try {
                employees.setEmployees(new EmployeeDAO().getEntities());
                File file = new File(filename);
                file.createNewFile();

                JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(employees, file);
                JOptionPane.showMessageDialog(null, "Wyeksportowano do " + filename, "Success!", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | JAXBException | IOException e1) {
                JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        importujButton.addActionListener(e -> {
            File folder = new File(".");
            File[] possibleValues = folder.listFiles(pathname -> pathname.isFile() && pathname.getName().matches("[a-zA-Z0-9_]+.xml"));

            if (possibleValues == null || possibleValues.length < 1){
                JOptionPane.showMessageDialog(null, "Brak plikow w folderze.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Object selectedValue = JOptionPane.showInputDialog(null,
                    "Wybierz plik", "Wybierz plik",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]);
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Employees employees = (Employees) jaxbUnmarshaller.unmarshal((File) selectedValue);

                EmployeeDAO employeeDAO = new EmployeeDAO();
                employeeDAO.replaceAllEntities(employees.getEmployees());
                JOptionPane.showMessageDialog(null, "Zaimportowano.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            } catch (/*JAXBException | SQLException |*/ Exception e1) {
                JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });
        setTitle("Pracownicy");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();
    }
}

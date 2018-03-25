package notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.*;

public class Notes extends JPanel implements Serializable {
    private static final long serialVersionUID = 1;
    private JTextArea textArea1;
    private JButton dodajButton;
    private JComboBox comboBox1;
    private JPanel panel1;
    private JLabel labelTitle;
    private JButton usunButton;
    private JScrollPane scrollPane1;

    private Map<Date, String> notes = new TreeMap<>();

    private String title = "Notes"; //prosta
    private int sizeOfText = 15; //wiazana - jak zmienia sie rozmiar tekstu, to zmienia sie tez kolor
    private int maxNumberOfNotes = 20; //ograniczona - jak ktoś da mniej niż 10 to veto

    public Notes() {
        $$$setupUI$$$();

        textArea1.setEditable(false);
        labelTitle.setText(title);

        scrollPane1.revalidate();

        notes.put(new Date(90, 10, 31), "Najstarsza notatka.");
        notes.put(new Date(96, 2, 24), "Stara notatka.");
        notes.put(new Date(106, 2, 24), "Nowsza notatka.");

        sizeOfText = 15;
        Font oldFont = textArea1.getFont();
        textArea1.setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), sizeOfText));
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        updateGUI();

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (notes.size() >= maxNumberOfNotes) {
                    JOptionPane.showMessageDialog(null, "Nie mozna wprowadzic wiecej notatek!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Date now = new Date();
                String note = JOptionPane.showInputDialog("Nowa notatka:");
                notes.put(now, note);
                updateGUI();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) comboBox1.getSelectedItem();
                try {
                    textArea1.setText(notes.get(date));
                } catch (NullPointerException e1) {
                    textArea1.setText("");
                }
            }
        });
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) comboBox1.getSelectedItem();
                notes.remove(date);
                updateGUI();
            }
        });

        this.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                int v = ((Integer) evt.getNewValue()).intValue();
                if (v < 10)
                    throw new PropertyVetoException("number too small",
                            evt);
            }
        });
        this.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String name = evt.getPropertyName();
                if (name.equals("sizeOfText")) {
                    int sizeOfText = (Integer) evt.getNewValue();
                    Font oldFont = textArea1.getFont();
                    textArea1.setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), sizeOfText));
                    textArea1.setLineWrap(true);

                    if (sizeOfText >= 10) {
                        textArea1.setForeground(new Color(255, 0, 0));
                    }
                    if (sizeOfText >= 50) {
                        textArea1.setForeground(new Color(0, 0, 255));
                    }
                    if (sizeOfText >= 70) {
                        textArea1.setForeground(new Color(0, 255, 0));
                    }
                  /*  textArea1.setPreferredSize(new Dimension(0, (textArea1.getLineCount() + 1) * sizeOfText + 50));
                    System.out.println(textArea1.getLineCount() * sizeOfText + 50);
                    scrollPane1.setPreferredSize(textArea1.getPreferredSize());
                    scrollPane1.revalidate();*/
                }
            }
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        labelTitle.setText(this.title);
    }

    public int getSizeOfText() {
        return sizeOfText;
    }

    public void setSizeOfText(int sizeOfText) {
        Integer old = new Integer(this.sizeOfText);
        this.sizeOfText = sizeOfText;
        firePropertyChange("sizeOfText", old, new Integer(this.sizeOfText));
    }

    public int getMaxNumberOfNotes() {
        return maxNumberOfNotes;
    }

    public void setMaxNumberOfNotes(int maxNumberOfNotes) throws PropertyVetoException {
        Integer old = new Integer(this.maxNumberOfNotes);
        fireVetoableChange("maxNumberOfNotes", old, new Integer(maxNumberOfNotes));
        this.maxNumberOfNotes = maxNumberOfNotes;
        firePropertyChange("maxNumberOfNotes", old, new Integer(maxNumberOfNotes));
    }

    private void updateGUI() {
        comboBox1.removeAllItems();
        for (Date date : notes.keySet()) {
            comboBox1.addItem(date);
        }
    }

    private void createUIComponents() {
        panel1 = this;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1.setLayout(new GridBagLayout());
        comboBox1 = new JComboBox();
        comboBox1.setMinimumSize(new Dimension(300, 26));
        comboBox1.setPreferredSize(new Dimension(300, 26));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(comboBox1, gbc);
        scrollPane1 = new JScrollPane();
        scrollPane1.setEnabled(true);
        scrollPane1.setMinimumSize(new Dimension(300, 19));
        scrollPane1.setVerticalScrollBarPolicy(22);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setEnabled(true);
        textArea1.setLineWrap(true);
        textArea1.setPreferredSize(new Dimension(300, 100));
        textArea1.setWrapStyleWord(true);
        scrollPane1.setViewportView(textArea1);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer6, gbc);
        usunButton = new JButton();
        usunButton.setHideActionText(false);
        usunButton.setText("Usun");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(usunButton, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer8, gbc);
        labelTitle = new JLabel();
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), labelTitle.getFont().getStyle(), 48));
        labelTitle.setMaximumSize(new Dimension(300, 64));
        labelTitle.setMinimumSize(new Dimension(300, 64));
        labelTitle.setPreferredSize(new Dimension(300, 64));
        labelTitle.setText("Notatki");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(labelTitle, gbc);
        dodajButton = new JButton();
        dodajButton.setText("Dodaj");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(dodajButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

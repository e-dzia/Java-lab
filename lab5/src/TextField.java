import javax.swing.*;

public class TextField extends JTextArea {
    private int customPreferredWidth = 300; //wiązana - szerokość muszą zmieniać wszystkie elementy po lewej

    public TextField(){

    }

    public int getCustomPreferredWidth() {
        return customPreferredWidth;
    }

    public void setCustomPreferredWidth(int customPreferredWidth) {
        Integer old = this.customPreferredWidth;
        this.customPreferredWidth = customPreferredWidth;
        firePropertyChange("customPreferredWidth", old, new Integer(this.customPreferredWidth));
    }

}

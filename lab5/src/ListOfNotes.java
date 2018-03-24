import javax.swing.*;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ListOfNotes extends JComboBox {
    private Map<Date, String> notes = new TreeMap<>();
    private int maxNumberOfNotes = 20; //ograniczona - jak ktoś da mniej niż 10 to veto

    public ListOfNotes(){
        notes.put(new Date(90, 10, 31), "Najstarsza notatka.");
        notes.put(new Date(96, 2, 24), "Stara notatka.");
        notes.put(new Date(106, 2, 24), "Nowsza notatka.");
    }

    public int getMaxNumberOfNotes() {
        return maxNumberOfNotes;
    }
    public void setMaxNumberOfNotes(int maxNumberOfNotes) throws PropertyVetoException {
        Integer old = this.maxNumberOfNotes;
        fireVetoableChange("maxNumberOfNotes",old,maxNumberOfNotes);
        this.maxNumberOfNotes = maxNumberOfNotes;
        firePropertyChange("maxNumberOfNotes",old,new Integer(maxNumberOfNotes));
    }

    public Map<Date, String> getNotes() {
        return notes;
    }
    public void setNotes(Map<Date, String> notes) {
        this.notes = notes;
    }


}

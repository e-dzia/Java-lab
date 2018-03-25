package notes;

import java.beans.Customizer;
import java.beans.PropertyChangeListener;

public class NotesCustomizer implements Customizer{
    NotesCustomizer(){};
    Notes notes;

    @Override
    public void setObject(Object bean) {
        notes = (Notes) bean;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}

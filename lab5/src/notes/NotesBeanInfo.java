package notes;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class NotesBeanInfo extends SimpleBeanInfo {
    private PropertyDescriptor[] propertyDescriptors = null;

    public NotesBeanInfo(){
        try {
            propertyDescriptors = new PropertyDescriptor[]{
                    new PropertyDescriptor("sizeOfText", notes.Notes.class, "getSizeOfText","setSizeOfText"),
                    new PropertyDescriptor("title", notes.Notes.class, "getTitle", "setTitle"),
                    new PropertyDescriptor("maxNumberOfNotes", notes.Notes.class, "getMaxNumberOfNotes","setMaxNumberOfNotes"),
                    };
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        return  propertyDescriptors;
    }

    public String getJavaInitializationString(){
        return "new Notes()";
    }
}
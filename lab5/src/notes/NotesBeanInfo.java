package notes;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class NotesBeanInfo extends SimpleBeanInfo {
    private PropertyDescriptor[] propertyDescriptors = null;

    public NotesBeanInfo(){
        try {
            propertyDescriptors = new PropertyDescriptor[]{
                    new PropertyDescriptor("title", Notes.class),
                    new PropertyDescriptor("sizeOfText", Notes.class),
                    new PropertyDescriptor("maxNumberOfNotes", Notes.class)
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
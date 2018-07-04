package beans;

import app.Main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppConfig implements AppConfigMBean {
    private int numberOfThreads;
    private int cacheSize;
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public AppConfig(int numberOfThreads, int cacheSize){
        this.numberOfThreads = numberOfThreads;
        this.cacheSize = cacheSize;
        this.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String name = evt.getPropertyName();
                if (name.equals("numberOfThreads")){
                    Main.changeNumberOfThreads((Integer) evt.getNewValue());
                }
                else if (name.equals("cacheSize")){
                    Main.changeCacheSize((Integer) evt.getNewValue());
                }
            }
        });
    }
    
    @Override
    public void setNumberOfThreads(int numberOfThreads) {
        if (numberOfThreads >= 0){
            int oldValue = this.numberOfThreads;
            this.numberOfThreads = numberOfThreads;
            this.propertyChangeSupport.firePropertyChange("numberOfThreads", oldValue, numberOfThreads);
        }
    }
    
    @Override
    public int getNumberOfThreads() {
        return numberOfThreads;
    }
    
    @Override
    public void setCacheSize(int cacheSize) {
        if (cacheSize >= 1){
            int oldValue = this.cacheSize;
            this.cacheSize = cacheSize;
            this.propertyChangeSupport.firePropertyChange("cacheSize", oldValue, cacheSize);
        }
    }
    
    @Override
    public int getCacheSize() {
        return cacheSize;
    }
    
    @Override
    public String getMessage() {
        return Main.getStats();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
}

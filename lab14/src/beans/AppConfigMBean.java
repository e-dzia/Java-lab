package beans;

public interface AppConfigMBean {
    public void setNumberOfThreads(int numberOfThreads);
    public int getNumberOfThreads();
    
    public void setCacheSize(int cacheSize);
    public int getCacheSize();
    
    public String getMessage();
}

package beans;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class AppConfigManagement {
   /* private static final int DEFAULT_NO_THREADS=3;
    private static final int DEFAULT_CACHE_SIZE=5;
    
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        //Get the MBean server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //register the MBean
        AppConfig mBean = new AppConfig(DEFAULT_NO_THREADS, DEFAULT_CACHE_SIZE);
        ObjectName name = new ObjectName("com.journaldev.jmx:type=AppConfig");
        mbs.registerMBean(mBean, name);
        do{
            Thread.sleep(3000);
            System.out.println("Thread Count="+mBean.getNumberOfThreads()+":::Cache="+mBean.getCacheSize());
        }while(mBean.getNumberOfThreads() !=0);
    
    }*/
}

import java.io.*;
import java.util.concurrent.Executor;

public class CustomClassLoader extends ClassLoader {

    protected CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    private Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;

        try {
            b = loadClassData(file);
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class aClass = null;
        try{
            aClass = findClass(name);
        }catch (ClassNotFoundException ignored){

        }
        if (aClass == null) {
            try {
                aClass = getClass(name);
            }catch (NullPointerException ignored){

            }

        }
        if(aClass == null) {
            aClass = super.loadClass(name);
        }
        return aClass;
    }

    private byte[] loadClassData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);

        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);

        in.readFully(buff);
        in.close();
        return buff;
    }
}
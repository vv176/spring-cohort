package exceptionhandling.classroom.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataManager {

    public void manage(Object dataProcessor) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Class c = dataProcessor.getClass();
        Method m = c.getMethod("process");
        m.invoke(dataProcessor);
    }

}

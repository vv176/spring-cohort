package exceptionhandling.classroom.reflection.deserializebasics;

import springbootcamp.reflection.reflectserialization.ReportCard;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
// DI : dependency injection
// Bean : Object managed by framework
// deserialise(String text, Class c)
// DBAccessor => Logger
// autowiring
public class Tester {
    // load classes at runtime
    // compile time classpath
    // runtime classpath
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> c =
                Class.forName("springbootcamp.reflection.reflectserialization.ReportCard");
        Constructor constructor = c.getConstructor();
         Object o = constructor.newInstance();
        for (Field f : c.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType().isPrimitive())
              f.set(o, 90);
        }
        ReportCard r = (ReportCard) o;
        System.out.println(r.getId());
        /**ReportCard reportCard =
                new ReportCard(101, new ScienceMarks(50,50,50, 50.0),
                        new ArtsMarks(100, 100, 100.0), 70.0);
        System.out.println(new Serializer().serialize(reportCard));**/
    }

}

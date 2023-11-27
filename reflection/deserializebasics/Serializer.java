package exceptionhandling.classroom.reflection.deserializebasics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
// {"numbers" : [12, 1, 2]}
public class Serializer {

    public String serialize(Object object) throws IllegalAccessException {
        String serialisedData = "";
        Class c = object.getClass();
        serialisedData += "{";
        List<Field> allFields = getAllFields(c);
        for (Field f :allFields) {
            f.setAccessible(true);
            String fieldName = f.getName();
            serialisedData += fieldName;
            serialisedData += ":";
            Object val = f.get(object);
            if (val.getClass().equals(Integer.class) || val.getClass().equals(Double.class)) {
                serialisedData += val;
            } else {
                serialisedData += serialize(val);
            }
            serialisedData += ",";
        }
        serialisedData = serialisedData.substring(0, serialisedData.length()-1);
        serialisedData += "}";
        return serialisedData;
    }

    public List<Field> getAllFields(Class c) {
        List<Field> fields =
                new ArrayList<>();
        while (c != null) {
            for (Field f : c.getDeclaredFields()) {
                fields.add(f);
            }
            c = c.getSuperclass();
        }
        return fields;
    }

}

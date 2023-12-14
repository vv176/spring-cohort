package exceptionhandling.classroom.reflectserialization;

import org.jdom2.Document;
import org.jdom2.Element;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;


public class Deserializer {

    public static void main(String[] args) throws Exception {
        // serialization
        Document document = new Document(new Element("serializableObject"));
        ReportCard reportCard =
                new ReportCard(101, new ScienceMarks(100,100,100,100.0),
                        new ArtsMarks(90, 60, 75.0), 90.0);
        Document doc = Serializer.serializeHelper(reportCard, document, new IdentityHashMap());
        // de-serialization
        deserializeObject(doc);
    }


    public static Object deserializeObject(Document source) throws Exception
    {
        List objList = source.getRootElement().getChildren();
        Map table = new HashMap();
        createInstances( table, objList );
        assignFieldValues( table, objList );
        return table.get("0");
    }

    private static void createInstances(Map table, List objList) throws Exception
    {
        for (int i = 0; i < objList.size(); i++) {
            Element oElt = (Element) objList.get(i);
            Class cls = Class.forName(oElt.getAttributeValue("class")); Object instance = null;
            if (!cls.isArray()) {
                Constructor c = cls.getDeclaredConstructor(null);
                if (!Modifier.isPublic(c.getModifiers())) {
                }
                instance = c.newInstance(null); }
            else { instance =
                    Array.newInstance(
                            cls.getComponentType(), Integer.parseInt(oElt.getAttributeValue("length")));
            }
            table.put(oElt.getAttributeValue("id"), instance);
        }
    }

    private static void assignFieldValues(Map table, List objList) throws Exception
    {
        for (int i = 0; i < objList.size(); i++) {
            Element oElt = (Element) objList.get(i);
            Object instance = table.get( oElt.getAttributeValue("id") );
            List fElts = oElt.getChildren();
            if (!instance.getClass().isArray()) {
                for (int j=0; j<fElts.size(); j++) {
                    Element fElt = (Element) fElts.get(j);
                    String className
                        = fElt.getAttributeValue("declaringclass");
                    Class fieldDC = Class.forName(className);
                    String fieldName = fElt.getAttributeValue("name");
                    Field f = fieldDC.getDeclaredField(fieldName);
                    if (!Modifier.isPublic(f.getModifiers())) {
                        f.setAccessible(true);
                    }
                    Element vElt = (Element) fElt.getChildren().get(0); f.set( instance,
                            deserializeValue( vElt, f.getType(), table ) );
                }
            } else {
                Class comptype =
                        instance.getClass().getComponentType();
                for ( int j = 0; j < fElts.size(); j++) {
                    Array.set( instance, j,
                            deserializeValue( (Element)fElts.get(j),
                                    comptype, table ));
                }
            }
        }
    }


    private static Object deserializeValue(Element vElt, Class fieldType, Map table)
            throws ClassNotFoundException {
        String valtype = vElt.getName();
        if (valtype.equals("null")) {
            return null;
        } else if (valtype.equals("reference")) {
            return table.get(vElt.getText());
        } else {
            if (fieldType.equals(boolean.class)) {
                if (vElt.getText().equals("true")) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } else if (fieldType.equals(byte.class)) {
                return Byte.valueOf(vElt.getText());
            } else if (fieldType.equals(short.class)) {
                return Short.valueOf(vElt.getText());
            } else if (fieldType.equals(int.class)) {
                return Integer.valueOf(vElt.getText());
            } else if (fieldType.equals(long.class)) {
                return Long.valueOf(vElt.getText());
            } else if (fieldType.equals(float.class)) {
                return Float.valueOf(vElt.getText());
            } else if (fieldType.equals(double.class)) {
                return Double.valueOf(vElt.getText());
            } else if (fieldType.equals(char.class)) {
                return Character.valueOf(vElt.getText().charAt(0));
            } else {
                return vElt.getText();
            }
        }
    }


}

package exceptionhandling.classroom.reflectserialization;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Serializer {

    public static void main(String[] args) throws Exception {
        serialize();
    }

    public static void serialize() throws Exception {
        Document document = new Document(new Element("serializableObject"));
        ReportCard reportCard =
                new ReportCard(101, new ScienceMarks(100,100,100,100.0),
                    new ArtsMarks(90, 60, 75.0), 90.0);
        Document doc = serializeHelper(reportCard, document, new IdentityHashMap());
        new XMLOutputter(Format.getPrettyFormat()).output(doc, System.out);
    }

    public static Document serializeHelper(Object source, Document target, Map table)
            throws Exception {
        String id = Integer.toString(table.size());
        table.put( source, id );
        Class sourceclass = source.getClass();
        Element oElt = new Element("object");
        oElt.setAttribute( "class", sourceclass.getName() );
        oElt.setAttribute( "id", id );
        target.getRootElement().addContent(oElt);
        if ( !sourceclass.isArray() ) {
            Field[] fields = getInstanceVariables(sourceclass);
            for (int i=0; i<fields.length; i++) {
                if ( !Modifier.isPublic(fields[i].getModifiers()) )
                    fields[i].setAccessible(true);
                Element fElt = new Element("field"); fElt.setAttribute( "name", fields[i].getName() ); Class declClass = fields[i].getDeclaringClass();
                fElt.setAttribute( "declaringclass", declClass.getName());
                Class fieldtype = fields[i].getType();
                Object child = fields[i].get(source);
                if (Modifier.isTransient(fields[i].getModifiers())){
                    child = null;
                }
                fElt.addContent(serializeVariable(fieldtype, child, target, table));
                oElt.addContent(fElt);
            }
        } else {
            Class componentType = sourceclass.getComponentType();
            int length = Array.getLength(source);
            oElt.setAttribute( "length", Integer.toString(length) );
            for (int i=0; i<length; i++) {
                oElt.addContent(serializeVariable(componentType, Array.get(source,i),
                        target,table));
            }
        }
        return target;
    }

    private static Element serializeVariable(Class fieldtype, Object child, Document target,
                                            Map table) throws Exception {
        if (child == null) {
            return new Element("null");
        } else if (!fieldtype.isPrimitive()) {
            Element reference = new Element("reference"); if (table.containsKey(child)) {
                reference.setText(table.get(child).toString()); }
            else {
                reference.setText( Integer.toString(table.size()) ); serializeHelper(child, target, table);
            }
            return reference;
        } else {
            Element value = new Element("value"); value.setText(child.toString());
            return value;
        }
    }

    public static Field[] getInstanceVariables(Class cls) {
        List<Field> accum = new LinkedList<Field>();
        while (cls != null) {
            Field[] fields = cls.getDeclaredFields();
            for (int i=0; i<fields.length; i++) {
                if (!Modifier.isStatic(fields[i].getModifiers())) {
                    accum.add(fields[i]);
                }
            }
            cls = cls.getSuperclass();
        }
        Field[] retvalue = new Field[accum.size()];
        return (Field[]) accum.toArray(retvalue);
    }

}

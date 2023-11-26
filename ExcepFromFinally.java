package exceptionhandling.classroom;

import java.io.EOFException;
import java.io.FileNotFoundException;

public class ExcepFromFinally {

    public static void tryCatchFin() throws EOFException {
        Exception e1 = null;
        try {
            System.out.println("inside try...");
            e1 = new FileNotFoundException("msg1");
            throw e1;
        } catch (Exception e) {
            System.out.println("inside catch...");
            throw e;
        } finally {
            System.out.println("inside finally...");
            EOFException e2 = new EOFException("msg2");
            e2.initCause(e1);
            throw e2;
        }
    }

    public static void main(String[] args) throws EOFException {
        tryCatchFin();
        try {
            throw new FileNotFoundException();
        } catch (Exception e) {
            throw e;
        } finally {
            throw new EOFException();
        }
    }

}

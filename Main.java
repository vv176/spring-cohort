package exceptionhandling.classroom;

import exceptionhandling.userdefinedexcep.IllegalFinancialMetricException;
import exceptions.resource.MyResource;

import javax.naming.directory.InvalidAttributesException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void printUserNames() throws IOException {
        FileReader fileReader = null;
        try { // regular logic
             fileReader =
                    new FileReader("/Users/vivekanandvivek/Desktop/IdeaProjects/MyFirstProject/src/exceptionhandling/intro/usr.txt");
            int i;
            while((i = fileReader.read()) != -1) {
                System.out.print((char)i);
            }
        } catch (IOException e) { // error handling logic
            throw new RuntimeException(e);
        }
        fileReader.close();
    }

    public static void main(String[] args)  {
        try {
        } finally {

        }


    }
    // Reflection
    // throw early, catch later
// C1 -> C2 -> C3 -> C4 -> C5 -> DB
// try (Res1 r1 = new Res1(); Res2 r2 = new Res2())
// {
// .......
// }
    public static void read() throws Exception {
        try(MyResource myResource =
                    new MyResource()) {
            // some code
            throw new FileNotFoundException();
        }
        /**MyResource myResource =
                new MyResource();
        Exception var = null;
        try {
            // reading
            throw new FileNotFoundException("file not detected");
        } catch (Exception e) {
            // handle
            var = e;
            throw var;
        } finally {
            System.out.println("inside finally");
            try {
                myResource.close();
            } catch (Exception e1) {
                if (var == null) {
                    var = e1;
                    throw var;
                }

            }
        }**/
         // normal

    }
    // try-with-resources

    public static void standardise(String userName) throws InvalidAttributesException {
        if (userName == null)
            throw new InvalidAttributesException("attr userName is NULL");
        userName = userName.toLowerCase();
    }

    public static void foo() {

        // scenarios of bugs that are preventable
    }


    // 2 types : Checked, Unchecked

    public static void publish(int revenue, int expenses,
                               int savings)
            throws IllegalFinancialMetricException, FileNotFoundException {
      try {
          thirdParty(2);
      } catch (FileNotFoundException|EOFException e) {
          System.out.println("here");
      }
      catch (IOException e) {
          System.out.println("here");
          throw new IllegalFinancialMetricException("3rd party error", e);
      }

    }

    public static void thirdParty(int i) throws IOException {
        //
        if (i == 1)
            throw new IOException();
        else if (i > 10)
            throw new EOFException();
        throw new FileNotFoundException();
    }

    public static void intellAnalysis(int revenue, int expenses,
                                      int savings)
            throws FileNotFoundException {
        // codes
        throw new FileNotFoundException();
    }

    public static void connectToDB(String userName, String password)
            throws IOException
    {
         throw new IOException();
    }

    /**
     * try {
     *
     * }
     * catch(IOException e) {
     *
     * }
     *
     */

    public static void func(String userName) throws InvalidAttributesException {
        if (userName == null) {
            throw new InvalidAttributesException("userName is NULL");
        }
        userName = userName.toUpperCase();
        // persists in DB
    }


    public static void start() throws EOFException {
        setUp();
    }

    public static void setUp() throws EOFException {
        try {
            load();
        } catch (FileNotFoundException e) {
            throw new EOFException(e.getMessage() + " my msg");
        }
    }

    public static void load() throws FileNotFoundException {
       exec();
    }

    public static void exec() throws FileNotFoundException {
       //
        //
        throw new FileNotFoundException("msg1");
        //
    }

}

/**
 * try {
 *   open the file
 *   read the contents
 *   close the file
 * } catch(FOException foe) {
 *     log it...
 * } catch(ReadException re) {
 *
 * } catch(CloseException ce) {
 *
 * }
 *
 */

/**
 * open the file
 * errCode = 0
 * if (file could open) :
 *   read from the file
 *   if (file couldn't be read)
 *     errCode = 2
 *   close the file
 *     if (file couldn't be closed)
 *       errCode = errCode==0 ? 3 : errCode&3
 * else
 *  errCode = 1;
 *
 */
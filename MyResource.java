package exceptionhandling.classroom;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;

public class MyResource implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("closing up...");
        throw new EOFException("err during closure");
    }
}

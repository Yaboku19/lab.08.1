package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File courrentFile;

    /**
     * Constructor with 0 paramether.
     */
    public Controller() {
        final String path = System.getProperty("user.home") + File.separator + "output.txt";
        this.courrentFile = new File(path);
    }

    /**
     * getter of file.
     * @return the courrent file
     */
    public File getFile() {
        Objects.requireNonNull(this.courrentFile);
        return this.courrentFile;
    }

    /**
     * setter of File.
     * @param file to be set
     */
    public void setFIle(final File file) {
        this.courrentFile = file;
    }

    /**
     * getter of the path of the file.
     * @return the path in String
     */
    public String getString() {
        return this.courrentFile.getPath();
    }

    /**
     * write a string on the courrent file.
     * @param  row String to be writed
     */
    public void writeString(final String row) throws IOException {
        Objects.requireNonNull(row);
        try (PrintStream ps = new PrintStream(this.getString(), StandardCharsets.UTF_8)) {
            ps.print(row);
        }
    }
}

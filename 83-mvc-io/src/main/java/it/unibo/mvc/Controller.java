package it.unibo.mvc;

import java.util.List;

/**
 * a simple controller responsible of I/O access.
 */
public interface Controller {

    /**
     * Set the next string to be written.
     * @param next the string to be setted
     */
    void setNextString(String next);

    /**
     * get the next string to be written.
     * @return the string that will be written
     */
    String getCourrentString();

    /**
     * get the history of all the string printed.
     * @return a list of String
     */
    List<String> getHistory();

    /**
     * print the courrent String.
     * @throws IllegalStateException throw an IllegalStateException if the String is unset
     */
    void printCourrentFile();
}

package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * an implementation of controller.
 */
public final class SimpleController implements Controller {
    private String courrentString;
    private final List<String> history;

    /**
     * constructor with 0 arguments.
     */
    public SimpleController() {
        this.courrentString = null;
        this.history = new ArrayList<>();
    }

    @Override
    public void setNextString(final String next) {
        Objects.requireNonNull(next);
        this.courrentString = next;
    }

    @Override
    public String getCourrentString() {
        return this.isSet() 
                ? this.courrentString
                : "";
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    @Override
    public void printCourrentFile() {
        if (this.isSet()) {
            System.out.println(getCourrentString()); // NOPMD: i have to print on the terminal
            history.add(getCourrentString());
            this.courrentString = null;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * control to see if the courrent string is setted.
     * @return True if is setted, False if is unsetted
     */
    private boolean isSet() {
        return this.courrentString != null;
    }

}

package it.unibo.mvc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final String NAME_FILE = "config.yml";
    private final DrawNumber model;
    private final List<DrawNumberView> views;
    private static final String MIN_key = "minimum";
    private static final String MAX_key = "maximum";
    private static final String ATTEMPS_key = "attempts";

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        Yaml yaml = new Yaml();
        InputStream file = ClassLoader.getSystemClassLoader().getResourceAsStream(NAME_FILE);
        Map<String, Integer> allFile =(Map<String, Integer>) yaml.load(file);
        //BufferedReader reader = new BufferedReader(new FileReader());
        this.model = new DrawNumberImpl(allFile.get(MIN_key), allFile.get(MAX_key), allFile.get(ATTEMPS_key));
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(), new DrawNumberViewImpl());
    }

}

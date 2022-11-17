package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.BorderLayout;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private static final String NAME = "SympleGUI";
    private static final int COLUMNS = 25;
    private static final int ROWS = 5;
    private final Controller controller;

    /**
     * Constructor with zero arguments.
     */
    public SimpleGUI() {
        // all components
        final var canvas = new JPanel(new BorderLayout());
        final var aTextField = new JTextField();
        aTextField.setBackground(Color.GRAY);
        aTextField.setColumns(COLUMNS);
        final var aTextArea = new JTextArea();
        aTextArea.setColumns(COLUMNS);
        aTextArea.setEditable(false);
        aTextArea.setRows(ROWS);
        final var printButton = new JButton("Print");
        final var historyButton = new JButton("Show history");
        final var northPanel = new JPanel(new BorderLayout());
        final var southPanel = new JPanel(new BorderLayout());
        // assembling
        northPanel.add(aTextField, BorderLayout.NORTH);
        northPanel.add(aTextArea, BorderLayout.SOUTH);
        canvas.add(northPanel, BorderLayout.NORTH);
        southPanel.add(printButton, BorderLayout.EAST);
        southPanel.add(historyButton, BorderLayout.WEST);
        canvas.add(southPanel, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(NAME);
        // action listeners
        this.controller = new SimpleController();
        printButton.addActionListener(e -> {
            controller.setNextString(aTextField.getText());
            try {
                controller.printCourrentFile();
            } catch (IllegalStateException event) {
                System.out.println("Impossibile printare la stringa" // NOPMD: i have to catch the error
                        + event.getStackTrace());
            }
        });
        historyButton.addActionListener(e -> aTextArea.setText(controller.getHistory().toString()));
    }

    /**
     * let the frame visible.
     */
    private void display() {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * main method let the program starts.
     * @param args useless paramether
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }
}

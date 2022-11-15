package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.FlowLayout;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private static final String NAME = "SimpleGUIWindow";
    private static final int PROPORTION = 5;

    /**
     * Creates a new SimpleGUI.
     * @param ctrl controller
     */
    public SimpleGUI(final Controller ctrl) {
        // file di testo
        final var textArea = new JTextArea();
        textArea.setRows(4);
        // main panel
        final var cansas = new JPanel(new BorderLayout());
        // button save
        final var saveButton = new JButton("Save");
        // panel for the souther configuration
        final var southPanel = new JPanel(new BorderLayout());
        // panel for the configuration of the souther panel
        final var souther = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // add parts
        souther.add(saveButton);
        southPanel.add(textArea, BorderLayout.NORTH);
        southPanel.add(souther, BorderLayout.SOUTH);
        cansas.add(southPanel, BorderLayout.SOUTH);
        // frame configuration
        this.frame.setContentPane(cansas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(NAME);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    ctrl.writeString(textArea.getText());
                } catch (IOException e) {
                    e.printStackTrace();    // NOPMD:
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String[] args) {
        new SimpleGUI(new Controller()).display();
    }

}

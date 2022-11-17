package it.unibo.mvc;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();
    private static final String NAME = "SimpleGUIWithFileChooserWindow";
    private static final int PROPORTION = 5;

    /**
     * Creates a new SimpleGUIWithFileChooser.
     * @param ctrl Controller
     */
    public SimpleGUIWithFileChooser(final Controller ctrl) {
        // main panel
        final var cansas = new JPanel(new BorderLayout());
        // button save
        final var saveButton = new JButton("Save");
        // panel for the souther configuration
        final var southPanel = new JPanel(new BorderLayout());
        // panel for the configuration of the souther panel
        final var souther = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // file di testo
        final var textArea = new JTextArea();
        textArea.setRows(4);
        // add parts as SimpleGUI
        souther.add(saveButton);
        southPanel.add(textArea, BorderLayout.NORTH);
        southPanel.add(souther, BorderLayout.SOUTH);
        cansas.add(southPanel, BorderLayout.SOUTH);
        // save button action
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
        /*
         * fino a qui uguale a SimpleGUI
         */
        // text with path pf the file chosen
        final var fileChosen = new JTextArea(ctrl.getString());
        fileChosen.setEditable(false);
        // browse button
        final var browse = new JButton("Browse..");
        // panel for the norther configuration
        final var northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // add part
        northPanel.add(fileChosen, BorderLayout.CENTER);
        northPanel.add(browse, BorderLayout.EAST);
        cansas.add(northPanel, BorderLayout.NORTH);
        // browse button action
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFileChooser windowSaver = new JFileChooser("Choose where to save");
                windowSaver.setSelectedFile(ctrl.getFile());
                final int result = windowSaver.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newFile = windowSaver.getSelectedFile();
                        ctrl.setFIle(newFile);
                        fileChosen.setText(newFile.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });
        // frame configuration
        this.frame.setContentPane(cansas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(NAME);
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
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}

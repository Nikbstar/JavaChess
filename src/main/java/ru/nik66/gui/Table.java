package ru.nik66.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nkotkin on 4/4/17.
 * Table for chess.
 */
public class Table {

    /**
     * Dimension or frame.
     */
    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    /**
     * Game frame.
     */
    private final JFrame gameFrame;

    /**
     * Table constructor.
     */
    public Table() {
        this.gameFrame = new JFrame("JChess");
        final JMenuBar tableMenuBar = new JMenuBar();
        populateMenuBar(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
    }

    /**
     * populate menu bar.
     * @param tableMenuBarArg - Table menu bar.
     */
    private void populateMenuBar(JMenuBar tableMenuBarArg) {
        tableMenuBarArg.add(createFileMenu());
    }

    /**
     * Menu File.
     * @return fileMenu.
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("open up that pgn file!");
            }
        });
        fileMenu.add(openPGN);
        return fileMenu;
    }

}

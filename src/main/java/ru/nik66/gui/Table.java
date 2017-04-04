package ru.nik66.gui;

import ru.nik66.engine.border.BoardUtils;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkotkin on 4/4/17.
 * Table for chess.
 */
public class Table {

    /**
     * Dimension for frame.
     */
    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    /**
     * Dimension for board.
     */
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    /**
     * Dimension for tile.
     */
    private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
    /**
     * Game frame.
     */
    private final JFrame gameFrame;
    /**
     * board panel.
     */
    private final BoardPanel boardPanel;

    /**
     * Table constructor.
     */
    public Table() {
        this.gameFrame = new JFrame("JChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    /**
     * Create menu bar.
     * @return table Menu Bar.
     */
    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
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

    /**
     * Border panel class.
     */
    private class BoardPanel extends JPanel {

        /**
         * Board height.
         */
        private static final int BOARD_HEIGHT = 8;
        /**
         * Board width.
         */
        private static final int BOARD_WIDTH = 8;
        /**
         * Board Tiles list.
         */
        private final List<TilePanel> boardTiles;

        /**
         * Board panel constructor.
         */
        BoardPanel() {
            super(new GridLayout(BOARD_HEIGHT, BOARD_WIDTH));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < BoardUtils.BOARD_SIZE; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

    }

    /**
     * Tile panel class.
     */
    private class TilePanel extends JPanel {

        /**
         * Tile id.
         */
        private final int tileId;

        /**
         * Tile constructor.
         * @param boardPanelArg - Border.
         * @param tileIdArg - Tile id.
         */
        TilePanel(final BoardPanel boardPanelArg, final int tileIdArg) {
            super(new GridBagLayout());
            this.tileId = tileIdArg;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            validate();
        }

        /**
         * Tile color.
         */
        private void assignTileColor() {

        }

    }

}
